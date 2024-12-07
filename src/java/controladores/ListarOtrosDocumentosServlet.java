package controladores;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.OtroDocumentoDAO;
import modelos.OtrosDocumentos;

@WebServlet(name = "ListarOtrosDocumentosServlet", urlPatterns = {"/ListarOtrosDocumentosServlet"})
public class ListarOtrosDocumentosServlet extends HttpServlet {

    // Método para procesar las solicitudes
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OtroDocumentoDAO documentoDAO = new OtroDocumentoDAO();
        try {
            // Obtener la lista de otros documentos usando el DAO
            List<OtrosDocumentos> listaDocumentos = documentoDAO.listarOtrosDocumentos();
            // Agregar la lista de documentos al request
            request.setAttribute("listaDocumentos", listaDocumentos);
            // Redirigir al JSP que muestra el listado de otros documentos
            request.getRequestDispatcher("listaOtrosDocumentos.jsp").forward(request, response);
        } catch (SQLException e) {
            // Si ocurre un error, se envía un mensaje al JSP de error
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al listar los documentos: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Utilizar el método doGet() para manejar la solicitud de listar todos los otros documentos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la solicitud
        processRequest(request, response);
    }

    // Puedes implementar doPost() si necesitas usar POST, aunque generalmente para listar se utiliza GET
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la solicitud
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite listar todos los otros documentos.";
    }
}
