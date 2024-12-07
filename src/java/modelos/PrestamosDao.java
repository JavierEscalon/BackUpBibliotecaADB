/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import config.Conexion;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import modelos.Consulta;
import modelos.Usuario;

/**
 *
 * @author HP
 */
public class PrestamosDao {

    public boolean devolverMaterial(int prestamoId, int materialId) {
        Connection conn = null;
        PreparedStatement stmtPrestamo = null;
        PreparedStatement stmtMaterialCantidad = null;

        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false); // Inicia la transacción

            // Actualizar la tabla `Prestamo`
            String sqlUpdatePrestamo = "UPDATE Prestamo SET fecha_devolucion_real = ?, estado = 'Finalizado' WHERE prestamo_id = ?";
            stmtPrestamo = conn.prepareStatement(sqlUpdatePrestamo);
            stmtPrestamo.setDate(1, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
            stmtPrestamo.setInt(2, prestamoId);
            stmtPrestamo.executeUpdate();

            // Actualizar la cantidad disponible en `Material`
            String sqlUpdateCantidad = "UPDATE Material SET cantidad_disponible = cantidad_disponible + 1 , estado = 'Disponible' WHERE material_id = ?";
            stmtMaterialCantidad = conn.prepareStatement(sqlUpdateCantidad);
            stmtMaterialCantidad.setInt(1, materialId);
            stmtMaterialCantidad.executeUpdate();

            conn.commit(); // Confirma la transacción
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmtPrestamo != null) {
                    stmtPrestamo.close();
                }
                if (stmtMaterialCantidad != null) {
                    stmtMaterialCantidad.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int aplicarMora(int prestamoId, int usuarioId) {
        String sqlUsuario = "SELECT u.rol_id, c.mora_diaria "
                + "FROM Usuario u "
                + "JOIN Configuracion c ON u.rol_id = c.rol_id "
                + "WHERE u.usuario_id = ?";
        String sqlPrestamo = "SELECT fecha_devolucion_prevista, fecha_devolucion_real "
                + "FROM Prestamo "
                + "WHERE prestamo_id = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
                PreparedStatement stmtPrestamo = conn.prepareStatement(sqlPrestamo)) {

            // Obtener información del usuario y configuración
            stmtUsuario.setInt(1, usuarioId);
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if (!rsUsuario.next()) {
                System.out.println("Usuario no encontrado o sin configuración.");
                return 1;
            }
            int rolId = rsUsuario.getInt("rol_id");
            double moraDiaria = rsUsuario.getDouble("mora_diaria");

            // Obtener información del préstamo
            stmtPrestamo.setInt(1, prestamoId);
            ResultSet rsPrestamo = stmtPrestamo.executeQuery();
            if (!rsPrestamo.next()) {
                System.out.println("Préstamo no encontrado.");
                return 2;
            }
            java.sql.Date fechaPrevista = rsPrestamo.getDate("fecha_devolucion_prevista");
            java.sql.Date fechaReal = rsPrestamo.getDate("fecha_devolucion_real");
            LocalDate fechaPrevistaLocal = fechaPrevista.toLocalDate();
            LocalDate fechaReferencia = (fechaReal != null) ? fechaReal.toLocalDate() : LocalDate.now();

// Validar si hay retraso
            if (fechaReferencia.isBefore(fechaPrevistaLocal)) {
                System.out.println("No hay mora porque el material fue devuelto a tiempo o antes de la fecha prevista ,o no hay retraso.");
                return 3;
            }
            System.out.println(fechaPrevistaLocal + " " + fechaReferencia);
// Calcular días de retraso
            long diasRetraso = ChronoUnit.DAYS.between(fechaPrevistaLocal, fechaReferencia);

// Si no hay días de retraso, no se aplica mora
            if (diasRetraso <= 0) {
                System.out.println("No hay retraso.");
                return 4;
            }

            // Calcular mora
            double moraTotal = diasRetraso * moraDiaria;
            System.out.println("Días de retraso: " + diasRetraso + ", Mora total: $" + moraTotal);

            // Insertar mora en la tabla
            String sqlInsertMora = "INSERT INTO Mora (prestamo_id, monto, fecha_generacion, estado) "
                    + "VALUES (?, ?, ?, 'Pendiente')";
            try (PreparedStatement stmtInsertMora = conn.prepareStatement(sqlInsertMora)) {
                stmtInsertMora.setInt(1, prestamoId);
                stmtInsertMora.setDouble(2, moraTotal);
                stmtInsertMora.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                stmtInsertMora.executeUpdate();
            }

            System.out.println("Mora aplicada correctamente.");
            return 5;

        } catch (SQLException e) {
            e.printStackTrace();
            return 6;
        }

    }

    public List<Prestamo> listarPrestamos() {
        List<Prestamo> resultados = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM prestamo p "
                + "LEFT JOIN mora m ON p.prestamo_id = m.prestamo_id "
                + "LEFT JOIN usuario u ON p.usuario_id = u.usuario_id "
                + "LEFT JOIN material ma ON p.material_id = ma.material_id "
                + "WHERE p.estado = 'Activo'";

        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_QUERY)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamo_id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("material_id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion_prevista"),
                        rs.getDate("fecha_devolucion_real"),
                        rs.getString("estado"),
                        rs.getInt("monto"),
                        rs.getString("nombre_usuario"),
                        rs.getString("titulo")
                );
                resultados.add(prestamo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores en la consulta, si los hubiera
        }

        return resultados;
    }

    public int prestarEjemplar(int materialId, int usuarioId, int rolId) {

        try {
            // int usuarioId = 4; // ID del usuario

            boolean res = verificarEjemplarDisponible(materialId);
            if (validarPrestamoDuplicado(usuarioId, materialId)) {
                System.out.println("El usuario ya tiene un préstamo activo para este material.");
                return 4;
            }
            if (!verificarEjemplarDisponible(materialId)) {
                return 3;
            }
            // Validar que el usuario no exceda el límite de préstamos activos
            if (!validarLimitePrestamos(usuarioId, rolId)) {
                System.out.println("El usuario ha alcanzado el límite de préstamos activos permitido para su rol.");
                return 5;
            }

            // Determinar la cantidad de días del préstamo según el rol
            int diasPrestamo = (rolId == 2) ? 30 : (rolId == 3) ? 15 : 30;

            if (tieneMoraPendiente(usuarioId)) {
                return 2;
            } else {
                // Lógica para realizar el préstamo
                // validar si el material esta disponible
                // restar la cantidad de materiales disponibles
                Connection conn = null;
                PreparedStatement stmtMaterial = null;
                conn = Conexion.getConnection();

                String sqlPrestamo = "INSERT INTO prestamo (usuario_id, material_id, fecha_prestamo, fecha_devolucion_prevista, estado) VALUES (?, ?, ?, ?, ?)";
                stmtMaterial = conn.prepareStatement(sqlPrestamo); //id de la bd

                stmtMaterial.setInt(1, usuarioId);
                stmtMaterial.setInt(2, materialId);
                // Fecha actual para `fecha_prestamo`
                java.sql.Date fechaPrestamo = new java.sql.Date(System.currentTimeMillis());
                stmtMaterial.setDate(3, fechaPrestamo);

                stmtMaterial.setDate(4, calcularFechaDevolucionPrevista(diasPrestamo));  // Fecha según el rol
                // Estado del préstamo
                stmtMaterial.setString(5, "Activo");

                // Ejecutar la consulta
                int rowsAffected = stmtMaterial.executeUpdate();
                System.out.println("Filas afectadas: " + rowsAffected);

                // Cerrar recursos
                stmtMaterial.close();
                conn.close();

                // return rowsAffected > 0 ? 1 : 0;
                if (rowsAffected > 0) {
                    // Actualiza la disponibilidad del material
                    boolean disponibilidadActualizada = actualizarDisponibilidadMaterial(materialId);
                    if (!disponibilidadActualizada) {
                        System.out.println("Error al actualizar la disponibilidad del material.");
                        return 0;
                    }
                    System.out.println("Préstamo realizado y disponibilidad actualizada correctamente.");
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public boolean validarLimitePrestamos(int usuarioId, int rolId) {
        String sql = "SELECT COUNT(*) AS prestamos_activos "
                + "FROM Prestamo "
                + "WHERE usuario_id = ? AND estado = 'Activo'";

        // Nueva consulta para obtener el límite de préstamos del rol desde la tabla Configuracion
        String limiteSql = "SELECT limite_prestamos "
                + "FROM Configuracion "
                + "WHERE rol_id = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmtLimite = conn.prepareStatement(limiteSql)) {

            // Obtener el número de préstamos activos
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int prestamosActivos = rs.getInt("prestamos_activos");

                // Obtener el límite de préstamos para el rol
                stmtLimite.setInt(1, rolId);
                ResultSet rsLimite = stmtLimite.executeQuery();

                if (rsLimite.next()) {
                    int limite = rsLimite.getInt("limite_prestamos");
                    return prestamosActivos < limite; // Retorna true si no ha alcanzado el límite
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private java.sql.Date calcularFechaDevolucionPrevista(int dias) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDevolucion = fechaActual.plusDays(dias);
        return java.sql.Date.valueOf(fechaDevolucion);
    }

    public boolean validarPrestamoDuplicado(int usuarioId, int materialId) {
        String sql = "SELECT COUNT(*) AS cantidad "
                + "FROM Prestamo "
                + "WHERE usuario_id = ? AND material_id = ? AND estado = 'Activo'";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, materialId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                return cantidad > 0; // Devuelve true si ya existe un préstamo activo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizarDisponibilidadMaterial(int materialId) {
        String sqlActualizarCantidad = "UPDATE Material "
                + "SET cantidad_disponible = cantidad_disponible - 1 "
                + "WHERE material_id = ? AND cantidad_disponible > 0";

        String sqlActualizarEstado = "UPDATE Material "
                + "SET estado = 'No Disponible' "
                + "WHERE material_id = ? AND cantidad_disponible = 0";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmtCantidad = conn.prepareStatement(sqlActualizarCantidad);
                PreparedStatement stmtEstado = conn.prepareStatement(sqlActualizarEstado)) {

            // Actualizar la cantidad disponible
            stmtCantidad.setInt(1, materialId);
            int rowsUpdated = stmtCantidad.executeUpdate();

            if (rowsUpdated > 0) {
                // Actualizar el estado solo si la cantidad disponible llega a 0
                stmtEstado.setInt(1, materialId);
                stmtEstado.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, regresa false
        }
        return false;
    }

    public boolean tieneMoraPendiente(int usuarioId) {
        String sql = "SELECT COUNT(*) AS mora_pendiente "
                + "FROM Mora m "
                + "JOIN Prestamo p ON m.prestamo_id = p.prestamo_id "
                + "WHERE p.usuario_id = ? AND m.estado = 'Pendiente'";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int moraPendiente = rs.getInt("mora_pendiente");
                return moraPendiente > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // En caso de error, asume que no tiene mora
    }

    public boolean verificarEjemplarDisponible(int materialId) {
        String sql = "SELECT COUNT(*) as disponibles "
                + "FROM Material m "
                + "WHERE m.material_id = ? AND m.estado = 'Disponible'";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, materialId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int disponible = rs.getInt("disponibles");
                return disponible > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Devuelve falso si no se encontró disponibilidad
    }
}
