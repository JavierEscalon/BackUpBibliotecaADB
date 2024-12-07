package controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.OtroDocumentoDAO;
import modelos.OtrosDocumentos;

@WebServlet(name = "DetalleOtroDocumentoServlet", urlPatterns = {"/DetalleOtroDocumentoServlet"})
public class DetalleOtroDocumentoServlet extends HttpServlet {

    // Método para procesar las solicitudes y obtener los detalles de un otro documento
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

            // Intentar obtener los detalles del documento usando el DAO
            OtrosDocumentos documento = documentoDAO.obtenerOtroDocumentoPorId(materialId);
            if (documento != null) {
                // Si se encuentra el documento, se agrega como atributo al request y se redirige al JSP de detalle
                request.setAttribute("documento", documento);
                request.getRequestDispatcher("detalleOtroDocumento.jsp").forward(request, response);
            } else {
                // Si no se encuentra, mostrar un mensaje de error
                request.setAttribute("mensajeError", "El documento no fue encontrado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "ID de documento inválido: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException e) {
            // Si ocurre un error, se envía un mensaje al JSP de error
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al obtener el documento: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Utilizar el método doGet() para manejar la solicitud de obtener detalles
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la obtención de detalles del documento
        processRequest(request, response);
    }

    // Si decides que la obtención de detalles se maneje con POST (aunque generalmente se usa GET), puedes modificar el método doPost()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la solicitud
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite obtener los detalles de un otro documento.";
    }
}
