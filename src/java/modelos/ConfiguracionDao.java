/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ConfiguracionDao {

    public List<Configuracion> listarConfiguraciones() {
        List<Configuracion> resultados = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM configuracion c "
                + "LEFT JOIN rol r ON c.rol_id = r.rol_id ";

        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_QUERY)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Configuracion configuracion = new Configuracion(
                        rs.getInt("configuracion_id"),
                        rs.getInt("limite_prestamos"),
                        rs.getDouble("mora_diaria"),
                        rs.getInt("rol_id"),
                        rs.getString("nombre")
                );
                resultados.add(configuracion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores en la consulta, si los hubiera
        }

        return resultados;
    }

    public boolean actualizarConfiguracion(int configuracionId, int limitePrestamos, double moraDiaria) {
        String SQL_UPDATE = "UPDATE configuracion SET limite_prestamos = ?, mora_diaria = ? WHERE configuracion_id = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, limitePrestamos);
            stmt.setDouble(2, moraDiaria);
            stmt.setInt(3, configuracionId);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
            return false;
        }
    }
}
