/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;
import config.Conexion;
import java.sql.*;
import java.util.*;
import modelos.Consulta;
/**
 *
 * @author HP
 */
public class ConsultaDao {
    
   private static final String SQL_QUERY = "SELECT m.material_id,m.titulo, m.año_publicacion, m.editorial, m.estado, m.descripcion, m.codigo_barras, m.idioma, "
            + "tm.tipo_material_id, tm.nombre as tipo_material, m.autor "
            + "FROM material m "
            + "JOIN tipoMaterial tm ON m.tipo_material_id = tm.tipo_material_id "
            + "WHERE (? IS NULL OR m.titulo LIKE ?) "
            + "AND (? IS NULL OR m.autor LIKE ?) "
            + "AND (? IS NULL OR m.descripcion LIKE ?) "
            + "AND (? = 'Todos' OR m.idioma = ?)"
            + "AND (? = 'Todos' OR tm.nombre = ?)";

    ;

    public List<Consulta> consultarEjemplares(String titulo, String autor, String descripcion, String idioma, String tipoMaterial) {
        List<Consulta> resultados = new ArrayList<>();
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_QUERY)) {
            System.out.println(autor);
            // Configuración de los parámetros de la consulta
            stmt.setString(1, (titulo == null || titulo.isEmpty()) ? null : titulo);
            stmt.setString(2, (titulo == null || titulo.isEmpty()) ? null : "%" + titulo + "%");

            stmt.setString(3, (autor == null || autor.isEmpty()) ? null : autor);
            stmt.setString(4, (autor == null || autor.isEmpty()) ? null : "%" + autor + "%");

            stmt.setString(5, (descripcion == null || descripcion.isEmpty()) ? null : descripcion);
            stmt.setString(6, (descripcion == null || descripcion.isEmpty()) ? null : "%" + descripcion + "%");
            if ("Todos".equals(idioma)) {
                stmt.setString(7, "Todos");  // Esto activa la condición que ignora el filtro
                stmt.setString(8, null);    // No se necesita un valor específico para idioma
            } else {
                stmt.setString(7, idioma);
                stmt.setString(8, idioma);
            }

            if ("Todos".equals(tipoMaterial)) {
                stmt.setString(9, "Todos");  // Esto activa la condición que ignora el filtro
                stmt.setString(10, null);    // No se necesita un valor específico para tipoMaterial
            } else {
                stmt.setString(9, tipoMaterial);
                stmt.setString(10, tipoMaterial);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta(
                        rs.getString("titulo"),
                        rs.getInt("material_id"),
                        rs.getInt("año_publicacion"),
                        rs.getString("editorial"),
                        rs.getString("estado"),
                        rs.getString("descripcion"),
                        rs.getString("codigo_barras"),
                        rs.getString("idioma"),
                        rs.getInt("tipo_material_id"),
                        rs.getString("tipo_material"),
                        rs.getString("autor")
                );
                resultados.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores en la consulta, si los hubiera
        }

        return resultados;
    }
}
