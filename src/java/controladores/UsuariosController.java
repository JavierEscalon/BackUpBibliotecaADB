/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Usuario;
import modelos.UsuarioDao;

/**
 *
 * @author HP
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/UsuariosController"})
public class UsuariosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuariosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuariosController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.listarUsuarios();

        // Enviar la lista como atributo a la página
        request.setAttribute("usuarios", usuarios);

        // Redirigir a la página JSP
        request.getRequestDispatcher("usuariosregistro.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configuración de respuesta
        response.setContentType("text/html;charset=UTF-8");
        if ("restablecer".equals(request.getParameter("accion"))) {
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            String nuevaPassword = "DonBosco123!"; // Contraseña predeterminada

            UsuarioDao usuarioDAO = new UsuarioDao();
            boolean resultado = usuarioDAO.editarUsuario(usuarioId, nuevaPassword);

            if (resultado) {
                request.setAttribute("mensaje", "La contraseña se restableció correctamente.");
                request.setAttribute("tipoMensaje", "success");
            } else {
                request.setAttribute("mensaje", "Hubo un error al restablecer la contraseña.");
                request.setAttribute("tipoMensaje", "danger");
            }
            // Obtener la lista de usuarios actualizada
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();

            // Enviar la lista como atributo a la página
            request.setAttribute("usuarios", usuarios);
            // Redirigir nuevamente a la lista de usuarios
            request.getRequestDispatcher("usuariosregistro.jsp").forward(request, response);
        }
        // Obtener los datos enviados desde el formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String nombreUsuario = request.getParameter("nombre_usuario");
        String password = request.getParameter("password");
        String rolStr = request.getParameter("rol");

        // Validar que los campos obligatorios estén presentes
        if (nombre == null || apellido == null || correo == null
                || nombreUsuario == null || password == null || rolStr == null) {
            response.getWriter().println("Todos los campos son obligatorios.");
            return;
        }

        int rolId;
        try {
            rolId = Integer.parseInt(rolStr); // Convertir el rol a un entero
        } catch (NumberFormatException e) {
            response.getWriter().println("El rol debe ser un número válido.");
            return;
        }

        try {
            // Lógica para guardar el usuario
            // Crear el objeto Usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreUsuario(nombre);
            nuevoUsuario.setApellidoUsuario(apellido);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setUsuario(nombreUsuario);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setRolId(rolId);

            // Llamar al método guardarUsuario del DAO
            UsuarioDao usuarioDao = new UsuarioDao();
            boolean guardado = usuarioDao.guardarUsuario(nuevoUsuario);

            // Obtener la lista de usuarios actualizada
            List<Usuario> usuarios = usuarioDao.listarUsuarios();

            // Enviar la lista como atributo a la página
            request.setAttribute("usuarios", usuarios);

            // Establecer el mensaje de notificación antes de la redirección
            request.setAttribute("mensaje", "Usuario guardado exitosamente.");
            request.setAttribute("tipoMensaje", "success"); // Tipo de notificación (éxito)

            // Redirigir a la misma página de registro
            request.getRequestDispatcher("usuariosregistro.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            // En caso de error, establecer el mensaje de notificación antes de la redirección
            request.setAttribute("mensaje", "Error al guardar el usuario.");
            request.setAttribute("tipoMensaje", "danger"); // Tipo de notificación (error)

            // Redirigir a la misma página de registro
            request.getRequestDispatcher("usuariosregistro.jsp").forward(request, response);
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
