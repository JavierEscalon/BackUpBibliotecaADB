package modelos;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import interfaces.LibroCRUD;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibroDAO implements LibroCRUD {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Libro libro = new Libro();
    
    public LibroDAO(){
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Libro> listarLibros() {
        List<Libro> listaLibros = new ArrayList<>();
        ResultSet rs = null;
        
        try (Connection connection = Conexion.getConnection();
            CallableStatement statement = connection.prepareCall("{call SP_VerLibros()}")) {
                      
            String isbn = "";
            String edicion = "";
            String titulo = "";
            String descripcion = "";
            int publicacion = 0;
            int paginas = 0;
            String editorial = "";
            String autor = "";
            String formato = "";
            String dimensiones = "";
            String ubicacion = "";
            String codigo = "";
            String idioma = "";
            int cantidad = 0;
            
            rs = statement.executeQuery();
            while (rs.next()) {
            Libro libro = new Libro(isbn, edicion, titulo, descripcion, publicacion, paginas, editorial, autor, formato, dimensiones, ubicacion, codigo, idioma, cantidad);
                libro.setIsbn(rs.getString("ISBN"));
                libro.setEdicion(rs.getString("numero_edicion"));
                libro.setTitulo(rs.getString(("titulo")));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setPublicacion(rs.getInt("año_publicacion"));
                libro.setPaginas(rs.getInt("numero_paginas"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAutor(rs.getString("autor"));
                libro.setFormato(rs.getString("formato"));
                libro.setDimensiones(rs.getString("dimensiones"));
                libro.setUbicacion(rs.getString("ubicacion_fisica"));
                libro.setCodigo(rs.getString("codigo_barras"));
                libro.setIdioma(rs.getString("idioma"));
                libro.setCantidad(rs.getInt("cantidad_disponible"));
                
                listaLibros.add(libro);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return listaLibros;
    }      

    @Override
    public Libro lista(String isbn) {
        String query="SELECT libro.ISBN, libro.numero_edicion, material.titulo, material.descripcion, material.año_publicacion, libro.numero_paginas, material.editorial, material.autor, libro.formato, libro.dimensiones, material.ubicacion_fisica, material.codigo_barras, material.idioma, material.cantidad_disponible FROM libro, material WHERE (libro.ISBN = " + "'" + isbn + "'" + ") AND (libro.material_id = material.material_id) ";
        
        try {
            
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                libro.setIsbn(rs.getString("ISBN"));
                libro.setEdicion(rs.getString("numero_edicion"));
                libro.setTitulo(rs.getString(("titulo")));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setPublicacion(rs.getInt("año_publicacion"));
                libro.setPaginas(rs.getInt("numero_paginas"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAutor(rs.getString("autor"));
                libro.setFormato(rs.getString("formato"));
                libro.setDimensiones(rs.getString("dimensiones"));
                libro.setUbicacion(rs.getString("ubicacion_fisica"));
                libro.setCodigo(rs.getString("codigo_barras"));
                libro.setIdioma(rs.getString("idioma"));
                libro.setCantidad(rs.getInt("cantidad_disponible"));

                return libro;
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return libro;
    }      

    @Override
    public boolean ingresarLibro(Libro libro) {
        
        try (Connection conn = Conexion.getConnection();
            CallableStatement statement = conn.prepareCall("{call SP_IngresarLibro(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            
            statement.setString(1, libro.getIsbn());
            statement.setString(2, libro.getEdicion());
            statement.setString(3, libro.getTitulo());
            statement.setString(4, libro.getDescripcion());
            statement.setInt(5, libro.getPublicacion());
            statement.setInt(6, libro.getPaginas());
            statement.setString(7, libro.getEditorial());
            statement.setString(8, libro.getAutor());
            statement.setString(9, libro.getFormato());
            statement.setString(10, libro.getDimensiones());
            statement.setString(11, libro.getUbicacion());
            statement.setString(12, libro.getCodigo());
            statement.setString(13, libro.getIdioma());
            statement.setInt(14, libro.getCantidad());
            
            statement.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }    

    @Override
    public boolean editarLibro(Libro libro) {
              
        String queryLibro = "UPDATE Libro SET ISBN = '" + libro.getIsbn() + "'," + "  numero_edicion = '" + libro.getEdicion() + "'," + " numero_paginas = " + libro.getPaginas() + "," + " formato = '" + libro.getFormato() + "'," + " dimensiones = '" + libro.getDimensiones() + "'" + " WHERE libro.ISBN = '" + libro.getIsbn() + "';";
        String queryMaterial = "UPDATE Material SET titulo = '" + libro.getTitulo() + "'," + "  año_publicacion = " + libro.getPublicacion() + "," + " editorial = '" + libro.getEditorial() + "'," + " autor = '" + libro.getAutor() + "'," + " ubicacion_fisica = '" + libro.getUbicacion() + "'," + " cantidad_disponible = " + libro.getCantidad() + ", codigo_barras = '" + libro.getCodigo() + "', idioma = '" + libro.getIdioma() + "' WHERE material.codigo_barras = '" + libro.getCodigo() + "';";
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(queryLibro);
            ps.executeUpdate();
            ps = conn.prepareStatement(queryMaterial);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public boolean eliminarLibro(String codigo) {
        String deleteFromLibros = "DELETE FROM Libro WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');";
        String deleteFromMaterial = "DELETE FROM Material WHERE material_id = (SELECT material_id FROM Material WHERE codigo_barras = '" + codigo + "');"; 
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(deleteFromLibros);
            System.out.println(deleteFromLibros);            
            ps.executeUpdate();
            ps = conn.prepareStatement(deleteFromMaterial);
            System.out.println(deleteFromMaterial);             
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
        return false;
    }
    
}

