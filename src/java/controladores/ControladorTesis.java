package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Tesis;
import modelos.TesisDAO;

@WebServlet(urlPatterns = {"/ControladorTesis"})
public class ControladorTesis extends HttpServlet {
    
    String listarTesis = "tesis.jsp";
    String insertarTesis = "tesisInsertar.jsp";
    String editarTesis = "tesisEditar.jsp"; 
    Tesis tesis = new Tesis();
    TesisDAO dao = new TesisDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorTesis</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorTesis at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("listarTesis")) {
            acceso = listarTesis;
        } else if (action.equalsIgnoreCase("insertarTesis")) {
            acceso = insertarTesis;
        } else if (action.equalsIgnoreCase("Agregar")) {
                       
            String grado = request.getParameter("grado");
            String institucion = request.getParameter("institucion");
            String director = request.getParameter("director");
            String area = request.getParameter("area");
            String defensa = request.getParameter("defensa");
            int paginas = Integer.parseInt(request.getParameter("paginas"));
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            int publicacion = Integer.parseInt(request.getParameter("publicacion"));
            String editorial = request.getParameter("editorial");            
            String ubicacion = request.getParameter("ubicacion");
            String codigo = request.getParameter("codigo");
            String idioma = request.getParameter("idioma");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String autor = request.getParameter("autor");
            dao.ingresarTesis(new Tesis(grado, institucion, director, area, defensa, paginas, titulo, descripcion, publicacion, editorial, autor, ubicacion, codigo, idioma, cantidad));
            acceso = listarTesis;
       } else if (action.equalsIgnoreCase("EditarTesis")) {
            request.setAttribute("tes", request.getParameter("codigoIn"));
            acceso = editarTesis;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            
            String codigoEdit = request.getParameter("hiddenCodigo");
            String gradoEdit = request.getParameter("textGrado");
            String institucionEdit = request.getParameter("textInstitucion");
            String directorEdit = request.getParameter("textDirector");
            String areaEdit = request.getParameter("textArea");
            String defensaEdit = request.getParameter("textDefensa");
            int paginasEdit = Integer.parseInt(request.getParameter("textPaginas"));
            String tituloEdit = request.getParameter("textTitulo");
            String descripcionEdit = request.getParameter("textDescripcion");
            int publicacionEdit = Integer.parseInt(request.getParameter("textPublicacion"));
            String editorialEdit = request.getParameter("textEditorial");
            String ubicacionEdit = request.getParameter("textUbicacion");
            String idiomaEdit = request.getParameter("textIdioma");            
            int cantidadEdit = Integer.parseInt(request.getParameter("textCantidad"));
            String autorEdit = request.getParameter("textAutor");
            
            tesis.setCodigo(codigoEdit);
            tesis.setGrado(gradoEdit);
            tesis.setInstitucion(institucionEdit);
            tesis.setDirector(directorEdit);
            tesis.setArea(areaEdit);
            tesis.setDefensa(defensaEdit);
            tesis.setPaginas(paginasEdit);
            tesis.setTitulo(tituloEdit);
            tesis.setDescripcion(descripcionEdit);
            tesis.setPublicacion(publicacionEdit);
            tesis.setEditorial(editorialEdit);
            tesis.setUbicacion(ubicacionEdit);
            tesis.setIdioma(idiomaEdit);
            tesis.setCantidad(cantidadEdit);
            tesis.setAutor(autorEdit);
            dao.editarTesis(tesis);
            acceso = listarTesis;            
        } else if (action.equalsIgnoreCase("EliminarTesis")) {
            String codigo = request.getParameter("codigoIn");
            tesis.setCodigo(codigo);
            dao.eliminarTesis(codigo);
            acceso = listarTesis;
            
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
