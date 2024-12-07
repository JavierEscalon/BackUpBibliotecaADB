package controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.OtroDocumentoDAO;

@WebServlet(name = "EliminarOtroDocumentoServlet", urlPatterns = {"/EliminarOtroDocumentoServlet"})
public class EliminarOtroDocumentoServlet extends HttpServlet {

    // Método para procesar las solicitudes y eliminar un otro documento
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parámetro "id" enviado desde la vista (JSP) para identificar el documento
        String idStr = request.getParameter("id");
        int materialId = 0;

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NumberFormatException("El parámetro 'id' es nulo o vacío.");
            }
            materialId = Integer.parseInt(idStr);

            OtroDocumentoDAO documentoDAO = new OtroDocumentoDAO();

            // Intentar eliminar el documento usando el DAO
            if (documentoDAO.eliminarOtroDocumento(materialId)) {
                // Si la eliminación es exitosa, redirigir al listado de otros documentos
                response.sendRedirect("ListarOtrosDocumentosServlet");
            } else {
                // Si ocurre un error en la eliminación
                request.setAttribute("mensajeError", "No se pudo eliminar el documento.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "ID de documento inválido: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException e) {
            // Si ocurre un error, se envía un mensaje al JSP de error
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al eliminar el documento: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Utilizar el método doGet() para manejar la solicitud de eliminación
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la eliminación del documento
        processRequest(request, response);
    }

    // Si decides que la eliminación se maneje con POST, puedes modificar el método doPost()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar los datos de eliminación
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite eliminar un otro documento.";
    }
}
