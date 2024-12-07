package modelos;

import config.Conexion;
import interfaces.TesisCRUD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesisDAO implements TesisCRUD {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Tesis tesis = new Tesis();
    
    public TesisDAO(){
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    @Override
    public List listarTesis() {
        List<Tesis> listaTesis = new ArrayList<>();
        ResultSet rs = null;
        
        try (Connection connection = Conexion.getConnection();
            CallableStatement statement = connection.prepareCall("{call SP_VerTesis()}")) {
                      
            String grado = "";
            String institucion = "";
            String director = "";
            String area = "";
            String defensa = "";
            int paginas = 0;
            String titulo = "";
            String descripcion = "";
            int publicacion = 0;
            String editorial = "";
            String autor = "";
            String ubicacion = "";
            String codigo = "";
            String idioma = "";
            int cantidad = 0;
          
            
            rs = statement.executeQuery();
            while (rs.next()) {
            Tesis tesis = new Tesis(grado, institucion, director, area, defensa, paginas, titulo, descripcion, publicacion, editorial, autor, ubicacion, codigo, idioma, cantidad);
                tesis.setGrado(rs.getString("grado_academico"));
                tesis.setInstitucion(rs.getString("institucion"));
                tesis.setDirector(rs.getString("director"));     
                tesis.setArea(rs.getString("area_investigacion"));
                tesis.setDefensa(rs.getString("fecha_defensa"));
                tesis.setPaginas(rs.getInt("numero_paginas"));
                tesis.setTitulo(rs.getString(("titulo")));
                tesis.setDescripcion(rs.getString("descripcion"));
                tesis.setPublicacion(rs.getInt("año_publicacion"));
                tesis.setEditorial(rs.getString("editorial"));
                 tesis.setAutor(rs.getString("autor"));
                tesis.setUbicacion(rs.getString("ubicacion_fisica"));
                tesis.setCodigo(rs.getString("codigo_barras"));
                tesis.setIdioma(rs.getString("idioma"));
                tesis.setCantidad(rs.getInt("cantidad_disponible"));
                
                listaTesis.add(tesis);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return listaTesis;
    }      

    @Override
    public Tesis lista(String codigoBarra) {
        String query="SELECT tesis.grado_academico, tesis.institucion, tesis.director, tesis.area_investigacion, tesis.fecha_defensa, tesis.numero_paginas, material.titulo, material.año_publicacion, material.editorial, material.autor, material.ubicacion_fisica, material.estado, material.cantidad_total, material.cantidad_disponible, material.descripcion, material.fecha_adquisicion, material.codigo_barras, material.idioma FROM tesis, material WHERE (material.codigo_barras = '" + codigoBarra + "') AND (tesis.material_id = material.material_id) ;";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                tesis.setGrado(rs.getString("grado_academico"));
                tesis.setInstitucion(rs.getString("institucion"));
                tesis.setDirector(rs.getString("director"));     
                tesis.setArea(rs.getString("area_investigacion"));
                tesis.setDefensa(rs.getString("fecha_defensa"));
                tesis.setPaginas(rs.getInt("numero_paginas"));
                tesis.setTitulo(rs.getString(("titulo")));
                tesis.setDescripcion(rs.getString("descripcion"));
                tesis.setPublicacion(rs.getInt("año_publicacion"));
                tesis.setEditorial(rs.getString("editorial"));
                tesis.setAutor(rs.getString("autor"));
                tesis.setUbicacion(rs.getString("ubicacion_fisica"));
                tesis.setCodigo(rs.getString("codigo_barras"));
                tesis.setIdioma(rs.getString("idioma"));
                tesis.setCantidad(rs.getInt("cantidad_disponible"));
                return tesis;
            }            
        } catch (SQLException e) {
            e.printStackTrace();            
        }
    return tesis;
    }      

    @Override
    public boolean ingresarTesis(Tesis tesis){
        
        try (Connection conn = Conexion.getConnection();
            CallableStatement statement = conn.prepareCall("{call SP_IngresarTesis(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            
            statement.setString(1, tesis.getGrado());
            statement.setString(2, tesis.getInstitucion());
            statement.setString(3, tesis.getDirector());
            statement.setString(4, tesis.getArea());
            statement.setString(5, tesis.getDefensa());
            statement.setInt(6, tesis.getPaginas());
            statement.setString(7, tesis.getTitulo());
            statement.setString(8, tesis.getDescripcion());
            statement.setInt(9, tesis.getPublicacion());
            statement.setString(10, tesis.getEditorial());
            statement.setString(11, tesis.getAutor());
            statement.setString(12, tesis.getUbicacion());            
            statement.setString(13, tesis.getCodigo());
            statement.setString(14, tesis.getIdioma());
            statement.setInt(15, tesis.getCantidad());
            
            
            statement.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }    

    @Override
    public boolean editarTesis(Tesis tesis) {
        
        String queryTesis = "UPDATE Tesis SET grado_academico = '" + tesis.getGrado() + "'," + " institucion = '" + tesis.getInstitucion() + "'," + " director = '" + tesis.getDirector() + "'," + " area_investigacion = '" + tesis.getArea() + "'," + " fecha_defensa = '" + tesis.getDefensa() + "'," + " numero_paginas = " + tesis.getPaginas() + " WHERE tesis.material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + tesis.getCodigo() + "');";
        String queryMaterial = "UPDATE Material SET titulo = '" + tesis.getTitulo() + "'," + "  año_publicacion = " + tesis.getPublicacion() + "," + " editorial = '" + tesis.getEditorial() + "'," + " autor = '" + tesis.getAutor() + "'," + " ubicacion_fisica = '" + tesis.getUbicacion() + "'," + " cantidad_disponible = " + tesis.getCantidad() + ", codigo_barras = '" + tesis.getCodigo() + "', idioma = '" + tesis.getIdioma() + "' WHERE material.codigo_barras = '" + tesis.getCodigo() + "';";
        System.out.println(queryTesis);
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(queryTesis);
            ps.executeUpdate();
            ps = conn.prepareStatement(queryMaterial);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        return false;    
    }

    @Override
    public boolean eliminarTesis(String codigo) {
        String deleteFromTesis = "DELETE FROM Tesis WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');";
        String deleteFromMaterial = "DELETE FROM Material WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');"; 
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteFromTesis);
            System.out.println(deleteFromTesis);            
            ps.executeUpdate();
            ps = conn.prepareStatement(deleteFromMaterial);
            System.out.println(deleteFromMaterial);             
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
        return false;
    }
    
}
