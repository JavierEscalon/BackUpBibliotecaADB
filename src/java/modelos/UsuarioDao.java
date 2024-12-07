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
public class UsuarioDao {
    public boolean editarUsuario(int usuarioId, String password) {
        String sql = "UPDATE Usuario SET contraseña = ? where usuario_id = ?";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, password);
            stmt.setInt(2, usuarioId);

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, apellido, correo_electronico, nombre_usuario, contraseña, rol_id) VALUES(?,?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getApellidoUsuario());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getUsuario());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getRolId());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Usuario> listarUsuarios() {
        String SQL_QUERY = "SELECT u.usuario_id, u.nombre, u.apellido, u.nombre_usuario, u.correo_electronico, r.nombre AS rol, r.rol_id "
                + "FROM Usuario u "
                + "JOIN Rol r ON u.rol_id = r.rol_id";

        List<Usuario> resultados = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getInt("rol_id"),
                        rs.getString("rol"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo_electronico"),
                        rs.getString("nombre_usuario"),
                        null // Password no está disponible en la consulta
                );
                resultados.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public Usuario login(String usuarioNombre, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        String sql = "SELECT u.usuario_id, u.rol_id, r.nombre AS rol, u.nombre, u.apellido, u.nombre_usuario,u.correo_electronico "
                + "FROM usuario u "
                + "JOIN rol r ON u.rol_id = r.rol_id "
                + "WHERE u.nombre_usuario = ? AND u.contraseña = ?";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);

            // Usar parámetros para evitar SQL Injection
            stmt.setString(1, usuarioNombre);
            stmt.setString(2, password);
             
            rs = stmt.executeQuery();
            
            // Si se encuentra un usuario, devolverlo
            if (rs.next()) {
                System.out.println(rs.getInt("usuario_id"));
                usuario = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getInt("rol_id"),
                        rs.getString("rol"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo_electronico"), // El correo no está en la consulta
                        rs.getString("nombre_usuario"),
                        null // No devolveremos el password por seguridad
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
