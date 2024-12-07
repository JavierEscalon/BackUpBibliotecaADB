package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import modelos.Obra;
import modelos.ObraDAO;

@WebServlet(urlPatterns = {"/ControladorObra"})
public class ControladorObra extends HttpServlet {
    
    String listarObras = "obras.jsp";
    String insertarObra = "obrasInsertar.jsp";
    String editarObra = "obrasEditar.jsp";
    Obra obra = new Obra();
    ObraDAO dao = new ObraDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorObra</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorObra at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("listarObras")) {
            acceso = listarObras;
        } else if (action.equalsIgnoreCase("insertarObra")) {
            acceso = insertarObra;
        } else if (action.equalsIgnoreCase("Agregar")) {
                       
            String tipo = request.getParameter("tipo");
            String genero = request.getParameter("genero");
            String formato = request.getParameter("formato");
            String dimensiones = request.getParameter("dimensiones");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            int publicacion = Integer.parseInt(request.getParameter("publicacion"));
            String editorial = request.getParameter("editorial");
            String ubicacion = request.getParameter("ubicacion");
            String codigo = request.getParameter("codigo");
            String idioma = request.getParameter("idioma");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String autor = request.getParameter("autor");
            dao.ingresarObra(new Obra(tipo, genero, formato, dimensiones, titulo, descripcion, publicacion, editorial, ubicacion, codigo, idioma, cantidad, autor));
            acceso = listarObras;
       } else if (action.equalsIgnoreCase("EditarObra")) {
            request.setAttribute("obr", request.getParameter("codigoIn"));
            acceso = editarObra;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            
            String codigoEdit = request.getParameter("hiddenCodigo");
            String tipoEdit = request.getParameter("textTipo");
            String generoEdit = request.getParameter("textGenero");
            String formatoEdit = request.getParameter("textFormato");
            String dimensionesEdit = request.getParameter("textDimensiones");
            String tituloEdit = request.getParameter("textTitulo");
            String descripcionEdit = request.getParameter("textDescripcion");
            int publicacionEdit = Integer.parseInt(request.getParameter("textPublicacion"));
            String editorialEdit = request.getParameter("textEditorial");
            String ubicacionEdit = request.getParameter("textUbicacion");
            String idiomaEdit = request.getParameter("textIdioma");
            int cantidadEdit = Integer.parseInt(request.getParameter("textCantidad")); 
            String autorEdit = request.getParameter("textAutor");
            
            obra.setCodigo(codigoEdit);
            obra.setTipo(tipoEdit);
            obra.setGenero(generoEdit);
            obra.setFormato(formatoEdit);
            obra.setDimensiones(dimensionesEdit);
            obra.setTitulo(tituloEdit);
            obra.setDescripcion(descripcionEdit);
            obra.setPublicacion(publicacionEdit);
            obra.setEditorial(editorialEdit);
            obra.setUbicacion(ubicacionEdit);
            obra.setIdioma(idiomaEdit);
            obra.setCantidad(cantidadEdit);
            obra.setAutor(autorEdit);
            dao.editarObra(obra);
            acceso = listarObras;            
        } else if (action.equalsIgnoreCase("EliminarObra")) {
            String codigo = request.getParameter("codigoIn");
            obra.setCodigo(codigo);
            dao.eliminarObra(codigo);
            acceso = listarObras;
            
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
