package controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.CDDAO;
import modelos.CD;

@WebServlet(name = "DetalleCDServlet", urlPatterns = {"/DetalleCDServlet"})
public class DetalleCDServlet extends HttpServlet {

    // Método para procesar la solicitud y obtener los detalles del CD
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el parámetro "id" de la solicitud, que representa el materialId del CD
        String materialIdStr = request.getParameter("id");
        if (materialIdStr == null || materialIdStr.isEmpty()) {
            // Si no se proporciona "id", mostrar error
            request.setAttribute("mensajeError", "No se proporcionó el ID del CD.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        int materialId;
        try {
            materialId = Integer.parseInt(materialIdStr);
        } catch (NumberFormatException e) {
            // Si el id no es un número válido
            request.setAttribute("mensajeError", "El ID del CD es inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        CDDAO cdDAO = new CDDAO();
        try {
            // Intentar obtener los detalles del CD usando el DAO
            CD cd = cdDAO.obtenerCDPorId(materialId);
            if (cd != null) {
                // Si se encuentra el CD, agregarlo como atributo y reenviar al JSP de detalle
                request.setAttribute("cd", cd);
                request.getRequestDispatcher("detalleCD.jsp").forward(request, response);
            } else {
                // Si no se encuentra, mostrar mensaje de error
                request.setAttribute("mensajeError", "El CD no fue encontrado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Si ocurre un error al obtener el CD desde la base de datos
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al obtener el CD: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Manejar solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Manejar solicitudes POST (si fuera necesario)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite obtener los detalles de un CD.";
    }
}
