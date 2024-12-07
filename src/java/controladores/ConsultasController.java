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
import modelos.ConsultaDao;
import modelos.Consulta;
import modelos.Usuario;

/**
 *
 * @author HP
 */
@WebServlet(name = "ConsultasController", urlPatterns = {"/ConsultasController"})
public class ConsultasController extends HttpServlet {

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
            out.println("<title>Servlet ConsultasController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultasController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String descripcion = request.getParameter("descripcion");
        String idioma = request.getParameter("idioma");
        String tipoMaterial = request.getParameter("tipo");

        // Llamar al DAO para obtener los resultados
        ConsultaDao consultaDao = new ConsultaDao();
        List<Consulta> resultados = consultaDao.consultarEjemplares(titulo, autor, descripcion, idioma, tipoMaterial);
        // Enviar resultados al JSP
        // Retornar los mismos parámetros al JSP
        request.setAttribute("titulo", titulo);
        request.setAttribute("autor", autor);
        request.setAttribute("descripcion", descripcion);
        request.setAttribute("idioma", idioma);
        request.setAttribute("tipo", tipoMaterial);
        request.setAttribute("resultados", resultados);
        System.out.println(resultados);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActual");
        int rolId = usuario.getRolId();
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
