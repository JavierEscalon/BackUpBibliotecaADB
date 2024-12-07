package controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.CDDAO;

@WebServlet(name = "EliminarCDServlet", urlPatterns = {"/EliminarCDServlet"})
public class EliminarCDServlet extends HttpServlet {

    // Método para procesar la solicitud de eliminación de un CD
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parámetro "id" enviado desde la vista (JSP) para identificar el CD
        String materialIdStr = request.getParameter("id");
        if (materialIdStr == null || materialIdStr.isEmpty()) {
            // Si no se proporcionó el ID, mostrar un mensaje de error
            request.setAttribute("mensajeError", "No se proporcionó el ID del CD a eliminar.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        int materialId;
        try {
            materialId = Integer.parseInt(materialIdStr);
        } catch (NumberFormatException e) {
            // Si el ID no es un número válido
            request.setAttribute("mensajeError", "El ID del CD es inválido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        CDDAO cdDAO = new CDDAO();

        try {
            // Intentar eliminar el CD usando el DAO
            if (cdDAO.eliminarCD(materialId)) {
                // Si la eliminación es exitosa, redirigir al listado de CDs
                response.sendRedirect("ListarCDsServlet");
            } else {
                // Si por alguna razón eliminarCD devuelve falso (no se eliminó)
                request.setAttribute("mensajeError", "No se pudo eliminar el CD. Puede que no exista.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Si ocurre un error en la operación de eliminación
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al eliminar el CD: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Utilizar el método doGet() para manejar la solicitud de eliminación
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Si decides que la eliminación se maneje con POST, puedes modificar el método doPost()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite eliminar un CD.";
    }
}
