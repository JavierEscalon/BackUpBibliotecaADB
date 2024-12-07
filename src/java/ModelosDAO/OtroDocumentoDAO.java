package ModelosDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import modelos.OtrosDocumentos;

public class OtroDocumentoDAO {

    private Connection conn;

    public OtroDocumentoDAO() {
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo otro documento usando procedimiento almacenado con manejo de errores
    public boolean insertarOtroDocumento(OtrosDocumentos documento) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_insertar_otros_documentos(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);

            // Establecer parámetros de entrada
            cs.setInt(1, documento.getTipoMaterialId());
            cs.setString(2, documento.getTitulo());
            if (documento.getAñoPublicacion() != null) {
                cs.setInt(3, documento.getAñoPublicacion());
            } else {
                cs.setNull(3, Types.INTEGER);
            }
            cs.setString(4, documento.getEditorial());
            cs.setString(5, documento.getUbicacionFisica());
            cs.setString(6, documento.getEstado());
            cs.setInt(7, documento.getCantidadTotal());
            cs.setInt(8, documento.getCantidadDisponible());
            cs.setString(9, documento.getDescripcion());
            if (documento.getFechaAdquisicion() != null) {
                cs.setDate(10, new java.sql.Date(documento.getFechaAdquisicion().getTime()));
            } else {
                cs.setNull(10, Types.DATE);
            }
            cs.setString(11, documento.getCodigoBarras());
            cs.setString(12, documento.getIdioma());

            cs.setString(13, documento.getTipoDocumento());
            cs.setString(14, documento.getOrganizacionEntidad());
            cs.setString(15, documento.getNumeroSerie());
            cs.setString(16, documento.getFormato());
            cs.setString(17, documento.getDimensiones());
            cs.setString(18, documento.getRequisitosEspeciales());

            // Parámetros de salida
            cs.registerOutParameter(19, Types.INTEGER); // p_material_id
            cs.registerOutParameter(20, Types.INTEGER); // p_error
            cs.registerOutParameter(21, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(20);
            String p_mensaje_error = cs.getString(21);

            if (p_error == 0) {
                int materialId = cs.getInt(19);
                documento.setMaterialId(materialId);
                resultado = true;
            } else {
                throw new SQLException("Error al insertar otro documento: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción para que pueda ser manejada en niveles superiores
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para actualizar un otro documento usando procedimiento almacenado con manejo de errores
    public boolean actualizarOtroDocumento(OtrosDocumentos documento) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_actualizar_otros_documentos(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = null;

        try {
            cs = conn.prepareCall(sql);

            // Establecer parámetros de entrada
            cs.setInt(1, documento.getMaterialId());
            cs.setInt(2, documento.getTipoMaterialId());
            cs.setString(3, documento.getTitulo());
            if (documento.getAñoPublicacion() != null) {
                cs.setInt(4, documento.getAñoPublicacion());
            } else {
                cs.setNull(4, Types.INTEGER);
            }
            cs.setString(5, documento.getEditorial());
            cs.setString(6, documento.getUbicacionFisica());
            cs.setString(7, documento.getEstado());
            cs.setInt(8, documento.getCantidadTotal());
            cs.setInt(9, documento.getCantidadDisponible());
            cs.setString(10, documento.getDescripcion());
            if (documento.getFechaAdquisicion() != null) {
                cs.setDate(11, new java.sql.Date(documento.getFechaAdquisicion().getTime()));
            } else {
                cs.setNull(11, Types.DATE);
            }
            cs.setString(12, documento.getCodigoBarras());
            cs.setString(13, documento.getIdioma());

            cs.setString(14, documento.getTipoDocumento());
            cs.setString(15, documento.getOrganizacionEntidad());
            cs.setString(16, documento.getNumeroSerie());
            cs.setString(17, documento.getFormato());
            cs.setString(18, documento.getDimensiones());
            cs.setString(19, documento.getRequisitosEspeciales());

            // Parámetros de salida
            cs.registerOutParameter(20, Types.INTEGER); // p_error
            cs.registerOutParameter(21, Types.VARCHAR); // p_mensaje_error

            cs.execute();

            int p_error = cs.getInt(20);
            String p_mensaje_error = cs.getString(21);

            if (p_error == 0) {
                resultado = true;
            } else {
                throw new SQLException("Error al actualizar otro documento: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para eliminar un otro documento usando procedimiento almacenado con manejo de errores
    public boolean eliminarOtroDocumento(int materialId) throws SQLException {
        boolean resultado = false;
        String sql = "{CALL sp_eliminar_otros_documentos(?, ?, ?)}";
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
                throw new SQLException("Error al eliminar otro documento: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción
        } finally {
            if (cs != null) {
                cs.close();
            }
        }

        return resultado;
    }

    // Método para obtener un otro documento por ID usando procedimiento almacenado con manejo de errores
    public OtrosDocumentos obtenerOtroDocumentoPorId(int materialId) throws SQLException {
        OtrosDocumentos documento = null;
        String sql = "{CALL sp_obtener_otros_documentos_por_id(?, ?, ?)}";
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, materialId);

            // Registrar los parámetros de salida
            cs.registerOutParameter(2, Types.INTEGER); // p_error
            cs.registerOutParameter(3, Types.VARCHAR); // p_mensaje_error

            // Ejecutar el procedimiento almacenado
            boolean hasResultSet = cs.execute();

            // Obtener los parámetros de salida
            int p_error = cs.getInt(2);
            String p_mensaje_error = cs.getString(3);

            if (p_error == 0) {
                if (hasResultSet) {
                    rs = cs.getResultSet();
                    if (rs.next()) {
                        documento = new OtrosDocumentos();
                        // Mapear los datos del ResultSet al objeto OtrosDocumentos
                        documento.setMaterialId(rs.getInt("material_id"));
                        documento.setTipoMaterialId(rs.getInt("tipo_material_id"));
                        documento.setTitulo(rs.getString("titulo"));
                        documento.setAñoPublicacion(rs.getInt("año_publicacion"));
                        documento.setEditorial(rs.getString("editorial"));
                        documento.setUbicacionFisica(rs.getString("ubicacion_fisica"));
                        documento.setEstado(rs.getString("estado"));
                        documento.setCantidadTotal(rs.getInt("cantidad_total"));
                        documento.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                        documento.setDescripcion(rs.getString("descripcion"));
                        documento.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));
                        documento.setCodigoBarras(rs.getString("codigo_barras"));
                        documento.setIdioma(rs.getString("idioma"));

                        // Datos específicos de OtrosDocumentos
                        documento.setTipoDocumento(rs.getString("tipo_documento"));
                        documento.setOrganizacionEntidad(rs.getString("organizacion_entidad"));
                        documento.setNumeroSerie(rs.getString("numero_serie"));
                        documento.setFormato(rs.getString("formato"));
                        documento.setDimensiones(rs.getString("dimensiones"));
                        documento.setRequisitosEspeciales(rs.getString("requisitos_especiales"));
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
            throw e; // Re-lanzar la excepción para que pueda ser manejada en niveles superiores
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // No cerramos la conexión aquí, ya que puede ser reutilizada
        }
        return documento;
    }

    // Método para listar todos los otros documentos usando procedimiento almacenado con manejo de errores
    public List<OtrosDocumentos> listarOtrosDocumentos() throws SQLException {
        List<OtrosDocumentos> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_otros_documentos(?, ?)}";
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
                    OtrosDocumentos documento = new OtrosDocumentos();
                    // Datos de material
                    documento.setMaterialId(rs.getInt("material_id"));
                    documento.setTipoMaterialId(rs.getInt("tipo_material_id"));
                    documento.setTitulo(rs.getString("titulo"));
                    documento.setAñoPublicacion(rs.getInt("año_publicacion"));
                    documento.setEditorial(rs.getString("editorial"));
                    documento.setUbicacionFisica(rs.getString("ubicacion_fisica"));
                    documento.setEstado(rs.getString("estado"));
                    documento.setCantidadTotal(rs.getInt("cantidad_total"));
                    documento.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                    documento.setDescripcion(rs.getString("descripcion"));
                    documento.setFechaAdquisicion(rs.getDate("fecha_adquisicion"));
                    documento.setCodigoBarras(rs.getString("codigo_barras"));
                    documento.setIdioma(rs.getString("idioma"));

                    // Datos específicos de OtrosDocumentos
                    documento.setTipoDocumento(rs.getString("tipo_documento"));
                    documento.setOrganizacionEntidad(rs.getString("organizacion_entidad"));
                    documento.setNumeroSerie(rs.getString("numero_serie"));
                    documento.setFormato(rs.getString("formato"));
                    documento.setDimensiones(rs.getString("dimensiones"));
                    documento.setRequisitosEspeciales(rs.getString("requisitos_especiales"));

                    lista.add(documento);
                }
            } else {
                throw new SQLException("Error al listar otros documentos: " + p_mensaje_error);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-lanzar la excepción
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
