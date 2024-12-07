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
import modelos.ConfiguracionDao;
import modelos.Configuracion;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/ConfiguracionController"})
public class ConfiguracionController extends HttpServlet {

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
            out.println("<title>Servlet ConfiguracionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfiguracionController at " + request.getContextPath() + "</h1>");
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
        ConfiguracionDao configuracionDAO = new ConfiguracionDao();

        List<Configuracion> lista = configuracionDAO.listarConfiguraciones();
        // Enviar la lista como atributo a la página
        request.setAttribute("configuraciones", lista);

        // Redirigir a la página JSP
        request.getRequestDispatcher("configuracion.jsp").forward(request, response);
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
        String configuracionId = request.getParameter("configuracionId");
        String limitePrestamos = request.getParameter("limitePrestamos");
        String moraDiaria = request.getParameter("moraDiaria");

        if (configuracionId != null && limitePrestamos != null && moraDiaria != null) {
            ConfiguracionDao configuracionDAO = new ConfiguracionDao();
            boolean success = configuracionDAO.actualizarConfiguracion(
                    Integer.parseInt(configuracionId),
                    Integer.parseInt(limitePrestamos),
                    Double.parseDouble(moraDiaria)
            );
            if (success) {
                request.setAttribute("mensaje", "Configuracion realizada con éxito.");
                request.setAttribute("tipoMensaje", "success");
            } else {
                request.setAttribute("mensaje", "No se pudo completar la configuracion.");
                request.setAttribute("tipoMensaje", "danger");
            }

        }
        ConfiguracionDao configuracionDAO = new ConfiguracionDao();

        List<Configuracion> lista = configuracionDAO.listarConfiguraciones();
        // Enviar la lista como atributo a la página
        request.setAttribute("configuraciones", lista);
        request.getRequestDispatcher("configuracion.jsp").forward(request, response);

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
