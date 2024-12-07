package modelos;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import interfaces.ObraCRUD;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObraDAO implements ObraCRUD{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Obra obra = new Obra();
    
    public ObraDAO(){
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Obra> listarObras() {
        List<Obra> listaObras = new ArrayList<>();
        ResultSet rs = null;
        
        try (Connection connection = Conexion.getConnection();
            CallableStatement statement = connection.prepareCall("{call SP_VerObras()}")) {
                      
            String tipoObra = "";
            String genero = "";
            String titulo = "";
            String descripcion = "";
            int publicacion = 0;
            String editorial = "";
            String formato = "";
            String dimensiones = "";
            String ubicacion = "";
            String codigo = "";
            String idioma = "";
            int cantidad = 0;
            String autor = "";
            
            rs = statement.executeQuery();
            while (rs.next()) {
            Obra obra = new Obra(tipoObra, genero, formato, dimensiones, titulo, descripcion, publicacion, editorial, ubicacion, codigo, idioma, cantidad, autor);
                obra.setTipo(rs.getString("tipo_obra"));
                obra.setGenero(rs.getString("genero"));
                obra.setFormato(rs.getString("formato"));     
                obra.setDimensiones(rs.getString("dimensiones"));
                obra.setTitulo(rs.getString(("titulo")));
                obra.setDescripcion(rs.getString("descripcion"));
                obra.setPublicacion(rs.getInt("año_publicacion"));
                obra.setEditorial(rs.getString("editorial"));
                obra.setUbicacion(rs.getString("ubicacion_fisica"));
                obra.setCodigo(rs.getString("codigo_barras"));
                obra.setIdioma(rs.getString("idioma"));
                obra.setCantidad(rs.getInt("cantidad_disponible"));
                obra.setAutor(rs.getString("autor"));
                
                listaObras.add(obra);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return listaObras;
    }      
    

    @Override
    public Obra lista(String codigoBarra) {
        
        String query = "SELECT obra.tipo_obra, obra.genero, obra.formato, obra.dimensiones, material.titulo, material.año_publicacion, material.editorial, material.ubicacion_fisica, material.estado, material.cantidad_total, material.cantidad_disponible, material.descripcion, material.fecha_adquisicion, material.codigo_barras, material.idioma, material.autor FROM obra, material WHERE (material.codigo_barras = '" + codigoBarra + "') AND (obra.material_id = material.material_id)";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                obra.setTipo(rs.getString("tipo_obra"));
                obra.setGenero(rs.getString("genero"));
                obra.setFormato(rs.getString("formato"));     
                obra.setDimensiones(rs.getString("dimensiones"));
                obra.setTitulo(rs.getString(("titulo")));
                obra.setDescripcion(rs.getString("descripcion"));
                obra.setPublicacion(rs.getInt("año_publicacion"));
                obra.setEditorial(rs.getString("editorial"));
                obra.setUbicacion(rs.getString("ubicacion_fisica"));
                obra.setCodigo(rs.getString("codigo_barras"));
                obra.setIdioma(rs.getString("idioma"));
                obra.setCantidad(rs.getInt("cantidad_disponible"));
                obra.setAutor(rs.getString("autor"));
                return obra;
            }
        } catch (SQLException e) {
            e.printStackTrace();               
        }
        return obra;
    }      

    @Override
    public boolean ingresarObra(Obra obra) {
        
        try (Connection conn = Conexion.getConnection();
            CallableStatement statement = conn.prepareCall("{call SP_IngresarObra(?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            
            statement.setString(1, obra.getTipo());
            statement.setString(2, obra.getGenero());
            statement.setString(3, obra.getFormato());
            statement.setString(4, obra.getDimensiones());
            statement.setString(5, obra.getTitulo());
            statement.setString(6, obra.getDescripcion());
            statement.setInt(7, obra.getPublicacion());
            statement.setString(8, obra.getEditorial());
            statement.setString(9, obra.getUbicacion());
            statement.setString(10, obra.getCodigo());
            statement.setString(11, obra.getIdioma());
            statement.setInt(12, obra.getCantidad());
            statement.setString(13, obra.getAutor());
            
            statement.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }    

    @Override
    public boolean editarObra(Obra obra) {
        String queryObra = "UPDATE Obra SET tipo_obra = '" + obra.getTipo() + "'," + "  genero = '" + obra.getGenero() + "'," + " formato = '" + obra.getFormato() + "'," + " dimensiones = '" + obra.getDimensiones() + "' WHERE obra.material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + obra.getCodigo() + "');";
        String queryMaterial = "UPDATE Material SET titulo = '" + obra.getTitulo() + "'," + "  año_publicacion = " + obra.getPublicacion() + "," + " editorial = '" + obra.getEditorial() + "'," + " ubicacion_fisica = '" + obra.getUbicacion() + "'," + " cantidad_disponible = " + obra.getCantidad() + ", codigo_barras = '" + obra.getCodigo() + "', idioma = '" + obra.getIdioma() + "', autor = '" + obra.getAutor() + "' WHERE material.codigo_barras = '" + obra.getCodigo() + "';";
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(queryObra);
            System.out.println("queryObra" + queryObra);
            ps.executeUpdate();
            ps = conn.prepareStatement(queryMaterial);    
            System.out.println("queryMaterial" + queryMaterial);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return false;        
    }

    @Override
    public boolean eliminarObra(String codigo) {
        String deleteFromObras = "DELETE FROM Obra WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');";
        String deleteFromMaterial = "DELETE FROM Material WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');"; 
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteFromObras);
            System.out.println(deleteFromObras);            
            ps.executeUpdate();
            ps = conn.prepareStatement(deleteFromMaterial);
            System.out.println(deleteFromMaterial);             
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
        return false;
    }
    
    
    
}
