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
import modelos.Consulta;
import modelos.ConsultaDao;
import modelos.Prestamo;
import modelos.PrestamosDao;
import modelos.Usuario;

/**
 *
 * @author HP
 */
@WebServlet(name = "PrestamosController", urlPatterns = {"/PrestamosController"})
public class PrestamosController extends HttpServlet {

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
            out.println("<title>Servlet PrestamosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrestamosController at " + request.getContextPath() + "</h1>");
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
        PrestamosDao prestamDAO = new PrestamosDao();

        List<Prestamo> lista = prestamDAO.listarPrestamos();
        // Enviar la lista como atributo a la página
        request.setAttribute("prestamos", lista);

        // Redirigir a la página JSP
        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
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
        PrestamosDao prestamosDao = new PrestamosDao();

        int materialId = Integer.parseInt(request.getParameter("materialId"));

        // Obtén el usuario de la sesión o cualquier otra fuente
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActual");
        System.out.println(usuario);
        if (usuario == null) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("message", "Usuario no autenticado.");
            request.getRequestDispatcher("consultas.jsp").forward(request, response);
            return;
        }
        int usuarioId = usuario.getUsuarioId();
        int rolId = usuario.getRolId();
        int resultado = prestamosDao.prestarEjemplar(materialId, usuarioId, rolId);

        System.out.println(usuarioId);

        String alertType;
        String message;

        switch (resultado) {
            case 1:
                alertType = "success";
                message = "Préstamo realizado exitosamente.";
                break;
            case 2:
                alertType = "warning";
                message = "El usuario tiene mora pendiente.";
                break;
            case 3:
                alertType = "danger";
                message = "El material no está disponible.";
                break;
            case 4:
                alertType = "warning";
                message = "El usuario ya tiene un préstamo activo de este material.";
                break;
            case 5:
                alertType = "danger";
                message = "El usuario ha alcanzado el límite de préstamos permitidos.";
                break;
            default:
                alertType = "danger";
                message = "Error al realizar el préstamo.";
        }

        request.setAttribute("alertType", alertType);
        request.setAttribute("message", message);
        // Recuperar los parámetros de búsqueda
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String descripcion = request.getParameter("descripcion");
        String idioma = request.getParameter("idioma");
        String tipoMaterial = request.getParameter("tipo");

        // Reenviar los parámetros al ConsultasController
        request.setAttribute("titulo", titulo);
        request.setAttribute("autor", autor);
        request.setAttribute("descripcion", descripcion);
        request.setAttribute("idioma", idioma);
        request.setAttribute("tipo", tipoMaterial);
        System.out.println(idioma);
        ConsultaDao consultaDao = new ConsultaDao();
        List<Consulta> resultados = consultaDao.consultarEjemplares(titulo, autor, descripcion, idioma, tipoMaterial);
        request.setAttribute("resultados", resultados);
        if (rolId == 1) {
            request.getRequestDispatcher("consultas.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("consultasprofesoralumno.jsp").forward(request, response);
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
