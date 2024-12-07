package ModelosDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import modelos.CD;

public class CDDAO {

    private Connection conn;

    public CDDAO() {
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo CD usando procedimiento almacenado con manejo de errores
    public boolean insertarCD(CD cd) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_insertar_cd(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);

            // Establecer parámetros de entrada
            cs.setInt(1, cd.getTipoMaterialId());
            cs.setString(2, cd.getTitulo());
            if (cd.getAñoPublicacion() != null) {
                cs.setInt(3, cd.getAñoPublicacion());
            } else {
                cs.setNull(3, Types.INTEGER);
            }
            cs.setString(4, cd.getEditorial());
            cs.setString(5, cd.getAutor() != null ? cd.getAutor() : "");
            cs.setString(6, cd.getUbicacionFisica());
            cs.setString(7, cd.getEstado());
            cs.setInt(8, cd.getCantidadTotal());
            cs.setInt(9, cd.getCantidadDisponible());
            cs.setString(10, cd.getDescripcion());
            if (cd.getFechaAdquisicion() != null) {
                cs.setDate(11, new java.sql.Date(cd.getFechaAdquisicion().getTime()));
            } else {
                cs.setNull(11, Types.DATE);
            }
            cs.setString(12, cd.getCodigoBarras());
            cs.setString(13, cd.getIdioma());

            cs.setString(14, cd.getFormato());
            cs.setString(15, cd.getDuracion());
            cs.setString(16, cd.getContenido());
            if (cd.getNumeroPistas() != null) {
                cs.setInt(17, cd.getNumeroPistas());
            } else {
                cs.setNull(17, Types.INTEGER);
            }
            cs.setString(18, cd.getRequisitosMinimos());

            // Parámetros de salida
            cs.registerOutParameter(19, Types.INTEGER); // p_material_id
            cs.registerOutParameter(20, Types.INTEGER); // p_error
            cs.registerOutParameter(21, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(20);
            String p_mensaje_error = cs.getString(21);

            if (p_error == 0) {
                int materialId = cs.getInt(19);
                cd.setMaterialId(materialId);
                resultado = true;
            } else {
                throw new SQLException("Error al insertar CD: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para actualizar un CD usando procedimiento almacenado con manejo de errores
    public boolean actualizarCD(CD cd) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_actualizar_cd(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);

            // Parámetros de entrada
            cs.setInt(1, cd.getMaterialId());
            cs.setInt(2, cd.getTipoMaterialId());
            cs.setString(3, cd.getTitulo());
            if (cd.getAñoPublicacion() != null) {
                cs.setInt(4, cd.getAñoPublicacion());
            } else {
                cs.setNull(4, Types.INTEGER);
            }
            cs.setString(5, cd.getEditorial());
            cs.setString(6, cd.getAutor() != null ? cd.getAutor() : "");
            cs.setString(7, cd.getUbicacionFisica());
            cs.setString(8, cd.getEstado());
            cs.setInt(9, cd.getCantidadTotal());
            cs.setInt(10, cd.getCantidadDisponible());
            cs.setString(11, cd.getDescripcion());
            if (cd.getFechaAdquisicion() != null) {
                cs.setDate(12, new java.sql.Date(cd.getFechaAdquisicion().getTime()));
            } else {
                cs.setNull(12, Types.DATE);
            }
            cs.setString(13, cd.getCodigoBarras());
            cs.setString(14, cd.getIdioma());

            cs.setString(15, cd.getFormato());
            cs.setString(16, cd.getDuracion());
            cs.setString(17, cd.getContenido());
            if (cd.getNumeroPistas() != null) {
                cs.setInt(18, cd.getNumeroPistas());
            } else {
                cs.setNull(18, Types.INTEGER);
            }
            cs.setString(19, cd.getRequisitosMinimos());

            // Parámetros de salida
            cs.registerOutParameter(20, Types.INTEGER); // p_error
            cs.registerOutParameter(21, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(20);
            String p_mensaje_error = cs.getString(21);

            if (p_error == 0) {
                resultado = true;
            } else {
                throw new SQLException("Error al actualizar CD: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para eliminar un CD usando procedimiento almacenado con manejo de errores
    public boolean eliminarCD(int materialId) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_eliminar_cd(?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);

            cs.setInt(1, materialId);

            // Parámetros de salida
            cs.registerOutParameter(2, Types.INTEGER); // p_error
            cs.registerOutParameter(3, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(2);
            String p_mensaje_error = cs.getString(3);

            if (p_error == 0) {
                resultado = true;
            } else {
                throw new SQLException("Error al eliminar CD: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para obtener un CD por ID usando procedimiento almacenado con manejo de errores
    public CD obtenerCDPorId(int materialId) throws SQLException {
        CD cd = null;
        String sql = "{CALL sp_obtener_cd_por_id(?, ?, ?)}";
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, materialId);

            // Registrar los parámetros de salida
            cs.registerOutParameter(2, Types.INTEGER); // p_error
            cs.registerOutParameter(3, Types.VARCHAR); // p_mensaje_error

            boolean hasResultSet = cs.execute();

            int p_error = cs.getInt(2);
            String p_mensaje_error = cs.getString(3);

            if (p_error == 0) {
                if (hasResultSet) {
                    rs = cs.getResultSet();
                    if (rs.next()) {
                        cd = new CD();
                        // Mapear datos de la tabla material
                        cd.setMaterialId(rs.getInt("material_id"));
                        cd.setTipoMaterialId(rs.getInt("tipo_material_id"));
                        cd.setTitulo(rs.getString("titulo"));
                        cd.setAñoPublicacion(rs.getInt("año_publicacion"));
                        cd.setEditorial(rs.getString("editorial"));
                        cd.setAutor(rs.getString("autor")); 
                        cd.setUbicacionFisica(rs.getString("ubicacion_fisica"));
                        cd.setEstado(rs.getString("estado"));
                        cd.setCantidadTotal(rs.getInt("cantidad_total"));
                        cd.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                        cd.setDescripcion(rs.getString("descripcion"));
                        cd.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));
                        cd.setCodigoBarras(rs.getString("codigo_barras"));
                        cd.setIdioma(rs.getString("idioma"));

                        // Datos específicos de CD
                        cd.setFormato(rs.getString("formato"));
                        cd.setDuracion(rs.getString("duracion"));
                        cd.setContenido(rs.getString("contenido"));
                        cd.setNumeroPistas(rs.getInt("numero_pistas"));
                        cd.setRequisitosMinimos(rs.getString("requisitos_minimos"));
                    } else {
                        throw new SQLException("No se encontraron registros con el ID proporcionado.");
                    }
                } else {
                    throw new SQLException("El procedimiento almacenado no devolvió ningún conjunto de resultados.");
                }
            } else {
                throw new SQLException("Error en procedimiento almacenado: " + p_mensaje_error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                cs.close();
            }
        }
        return cd;
    }

    // Método para listar todos los CDs usando procedimiento almacenado con manejo de errores
    public List<CD> listarCDs() throws SQLException {
        List<CD> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_cds(?, ?)}";
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conn.prepareCall(sql);

            // Parámetros de salida
            cs.registerOutParameter(1, Types.INTEGER); // p_error
            cs.registerOutParameter(2, Types.VARCHAR); // p_mensaje_error

            rs = cs.executeQuery();

            int p_error = cs.getInt(1);
            String p_mensaje_error = cs.getString(2);

            if (p_error == 0) {
                while (rs.next()) {
                    CD cd = new CD();
                    // Datos de material
                    cd.setMaterialId(rs.getInt("material_id"));
                    cd.setTipoMaterialId(rs.getInt("tipo_material_id"));
                    cd.setTitulo(rs.getString("titulo"));
                    cd.setAñoPublicacion(rs.getInt("año_publicacion"));
                    cd.setEditorial(rs.getString("editorial"));
                    cd.setAutor(rs.getString("autor")); 
                    cd.setUbicacionFisica(rs.getString("ubicacion_fisica"));
                    cd.setEstado(rs.getString("estado"));
                    cd.setCantidadTotal(rs.getInt("cantidad_total"));
                    cd.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                    cd.setDescripcion(rs.getString("descripcion"));
                    cd.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));
                    cd.setCodigoBarras(rs.getString("codigo_barras"));
                    cd.setIdioma(rs.getString("idioma"));

                    // Datos de CD
                    cd.setFormato(rs.getString("formato"));
                    cd.setDuracion(rs.getString("duracion"));
                    cd.setContenido(rs.getString("contenido"));
                    cd.setNumeroPistas(rs.getInt("numero_pistas"));
                    cd.setRequisitosMinimos(rs.getString("requisitos_minimos"));

                    lista.add(cd);
                }
            } else {
                throw new SQLException("Error al listar CDs: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return lista;
    }

    // Método para obtener 'tipoMaterialId' a partir de 'materialId'
    public int obtenerTipoMaterialIdPorMaterialId(int materialId) throws SQLException {
        int tipoMaterialId = 0;
        String sql = "{CALL sp_obtener_tipo_material_id_por_material_id(?, ?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, materialId);

            // Registrar parámetros de salida
            cs.registerOutParameter(2, Types.INTEGER); // p_tipoMaterialId
            cs.registerOutParameter(3, Types.INTEGER); // p_error
            cs.registerOutParameter(4, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(3);
            String p_mensaje_error = cs.getString(4);

            if (p_error == 0) {
                tipoMaterialId = cs.getInt(2);
            } else {
                throw new SQLException("Error al obtener tipoMaterialId: " + p_mensaje_error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return tipoMaterialId;
    }

}

