package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Prestamo;
import modelos.PrestamosDao;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/MoraController"})
public class MoraController extends HttpServlet {

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
            out.println("<title>Servlet MoraController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MoraController at " + request.getContextPath() + "</h1>");
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
        // Obtener los valores enviados desde el formulario
        String prestamoIdStr = request.getParameter("prestamoId");
        String usuarioIdStr = request.getParameter("usuarioId");
        // Convertir los valores a tipo entero
        int prestamoId = Integer.parseInt(prestamoIdStr);
        int usuarioId = Integer.parseInt(usuarioIdStr);

        // Instancia del DAO
        PrestamosDao prestamosDAO = new PrestamosDao();

        // Aplicar la mora
        int success = prestamosDAO.aplicarMora(prestamoId, usuarioId);

        // Actualizar la lista de préstamos y reenviar al JSP
        List<Prestamo> lista = prestamosDAO.listarPrestamos();
        request.setAttribute("prestamos", lista);

        // Preparar un mensaje de éxito o error
        if (success == 1) {
            request.setAttribute("message", "Usuario no encontrado o sin configuración.");
            request.setAttribute("alertType", "danger");
        } else if (success == 2) {
            request.setAttribute("message", "Préstamo no encontrado.");
            request.setAttribute("alertType", "danger");
        }else if (success == 3) {
            request.setAttribute("message", "No hay mora porque el material fue devuelto a tiempo o antes de la fecha prevista o no hay retraso.");
            request.setAttribute("alertType", "danger");
        }else if (success == 4) {
            request.setAttribute("message", "No hay retraso.");
            request.setAttribute("alertType", "danger");
        }else if (success == 5) {
            request.setAttribute("message", "Mora aplicada correctamente.");
            request.setAttribute("alertType", "success");
        }else {
            request.setAttribute("message", "No se pudo aplicar la mora.");
            request.setAttribute("alertType", "danger");
        }

        // Redirigir a la página de préstamos
        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
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
