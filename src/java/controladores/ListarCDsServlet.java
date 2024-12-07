package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.CDDAO;
import modelos.CD;

@WebServlet(name = "ListarCDsServlet", urlPatterns = {"/ListarCDsServlet"})
public class ListarCDsServlet extends HttpServlet {

    // Este método se encargará de manejar la lógica para listar los CDs
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CDDAO cdDAO = new CDDAO();
        try {
            // Obtener la lista de CDs usando el DAO
            List<CD> listaCDs = cdDAO.listarCDs();

            // Agregar la lista de CDs al request
            request.setAttribute("listaCDs", listaCDs);

            // Redirigir al JSP que muestra el listado de CDs
            request.getRequestDispatcher("listaCDs.jsp").forward(request, response);

        } catch (SQLException e) {
            // Si ocurre un error, se envía un mensaje al JSP de error
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al listar los CDs: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Utilizar el método doGet() para manejar la solicitud de listar todos los CDs
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la solicitud de listar CDs
        processRequest(request, response);
    }

    // Si deseas utilizar POST, puedes implementar doPost(), pero generalmente para listar se utiliza GET
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar la solicitud de listar CDs
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite listar todos los CDs.";
    }
}
