package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Libro;
import modelos.LibroDAO;

@WebServlet(urlPatterns = {"/ControladorLibro"})
public class ControladorLibro extends HttpServlet {
    
    String listarLibros = "libros.jsp";
    String insertarLibro = "librosInsertar.jsp";
    String editarLibro = "librosEditar.jsp";
    Libro libro = new Libro();
    LibroDAO dao = new LibroDAO();

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorLibro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLibro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Libro libro = new Libro();
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("insertarLibro")) {
            acceso = insertarLibro;
        } else if (action.equalsIgnoreCase("Agregar")) {
                       
            String isbn = request.getParameter("isbn");
            String edicion = request.getParameter("edicion");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            int publicacion = Integer.parseInt(request.getParameter("publicacion"));
            int paginas = Integer.parseInt(request.getParameter("paginas"));
            String editorial = request.getParameter("editorial");
            String autor = request.getParameter("autor");
            String formato = request.getParameter("formato");
            String dimensiones = request.getParameter("dimensiones");
            String ubicacion = request.getParameter("ubicacion");
            String codigo = request.getParameter("codigo");
            String idioma = request.getParameter("idioma");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            dao.ingresarLibro(new Libro(isbn, edicion, titulo, descripcion, publicacion, paginas, editorial, autor, formato, dimensiones, ubicacion, codigo, idioma, cantidad));
            acceso = listarLibros;
        } else if (action.equalsIgnoreCase("EditarLibro")) {
            request.setAttribute("lib", request.getParameter("isbn"));
            acceso = editarLibro;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            
            String isbnEdit = request.getParameter("hiddenIsbn");
            String edicionEdit = request.getParameter("textEdicion");
            String tituloEdit = request.getParameter("textTitulo");
            String descripcionEdit = request.getParameter("textDescripcion");
            int publicacionEdit = Integer.parseInt(request.getParameter("textPublicacion"));
            int paginasEdit = Integer.parseInt(request.getParameter("textPaginas"));
            String editorialEdit = request.getParameter("textEditorial");
            String autorEdit = request.getParameter("textAutor");
            String formatoEdit = request.getParameter("textFormato");
            String dimensionesEdit = request.getParameter("textDimensiones");
            String ubicacionEdit = request.getParameter("textUbicacion");
            String codigoEdit = request.getParameter("textCodigo");
            String idiomaEdit = request.getParameter("textIdioma");
            int cantidadEdit = Integer.parseInt(request.getParameter("textCantidad"));
            
            libro.setIsbn(isbnEdit);
            libro.setEdicion(edicionEdit);
            libro.setTitulo(tituloEdit);
            libro.setDescripcion(descripcionEdit);
            libro.setPublicacion(publicacionEdit);
            libro.setPaginas(paginasEdit);
            libro.setEditorial(editorialEdit);
            libro.setAutor(autorEdit);
            libro.setFormato(formatoEdit);
            libro.setDimensiones(dimensionesEdit);
            libro.setUbicacion(ubicacionEdit);
            libro.setCodigo(codigoEdit);
            libro.setIdioma(idiomaEdit);
            libro.setCantidad(cantidadEdit);
            dao.editarLibro(libro);
            acceso = listarLibros;
        } else if (action.equalsIgnoreCase("EliminarLibro")) {
            String codigo = request.getParameter("codigo");
            libro.setCodigo(codigo);
            dao.eliminarLibro(codigo);
            acceso = listarLibros;
            
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
