package controladores;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModelosDAO.CDDAO;
import modelos.CD;

@WebServlet(name = "RegistrarCDServlet", urlPatterns = {"/RegistrarCDServlet"})
public class RegistrarCDServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer codificación de caracteres
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Mapa para mensajes de error
        Map<String, String> errores = new HashMap<>();

        // Instancia de CD
        CD cd = new CD();

        try {
            // Establecer 'tipoMaterialId' en 3 para CD (asumiendo que 3 es el ID para CD)
            cd.setTipoMaterialId(3);

            // Validar y obtener 'titulo'
            String titulo = request.getParameter("titulo");
            if (titulo == null || titulo.trim().isEmpty()) {
                errores.put("titulo", "El título es obligatorio.");
            } else if (titulo.length() > 255) {
                errores.put("titulo", "El título no puede tener más de 255 caracteres.");
            } else {
                cd.setTitulo(titulo);
            }

            // Validar y obtener 'editorial'
            String editorial = request.getParameter("editorial");
            if (editorial == null || editorial.trim().isEmpty()) {
                errores.put("editorial", "La editorial es obligatoria.");
            } else if (editorial.length() > 100) {
                errores.put("editorial", "La editorial no puede tener más de 100 caracteres.");
            } else {
                cd.setEditorial(editorial);
            }

            // Validar y obtener 'autor'
            String autor = request.getParameter("autor");
            if (autor == null || autor.trim().isEmpty()) {
                errores.put("autor", "El autor es obligatorio.");
            } else if (autor.length() > 100) {
                errores.put("autor", "El autor no puede tener más de 100 caracteres.");
            } else {
                cd.setAutor(autor);
            }

            // Validar y obtener 'ubicacionFisica'
            String ubicacionFisica = request.getParameter("ubicacionFisica");
            if (ubicacionFisica == null || ubicacionFisica.trim().isEmpty()) {
                errores.put("ubicacionFisica", "La ubicación física es obligatoria.");
            } else if (ubicacionFisica.length() > 50) {
                errores.put("ubicacionFisica", "La ubicación física no puede tener más de 50 caracteres.");
            } else {
                cd.setUbicacionFisica(ubicacionFisica);
            }

            // Validar y obtener 'estado'
            String estado = request.getParameter("estado");
            if (estado == null || estado.trim().isEmpty()) {
                errores.put("estado", "El estado es obligatorio.");
            } else if (estado.length() > 15) {
                errores.put("estado", "El estado no puede tener más de 15 caracteres.");
            } else {
                cd.setEstado(estado);
            }

            // Validar y obtener 'descripcion'
            String descripcion = request.getParameter("descripcion");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                errores.put("descripcion", "La descripción es obligatoria.");
            } else {
                cd.setDescripcion(descripcion);
            }

            // Validar y obtener 'codigoBarras'
            String codigoBarras = request.getParameter("codigoBarras");
            if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
                errores.put("codigoBarras", "El código de barras es obligatorio.");
            } else if (codigoBarras.length() > 50) {
                errores.put("codigoBarras", "El código de barras no puede tener más de 50 caracteres.");
            } else {
                cd.setCodigoBarras(codigoBarras);
            }

            // Validar y obtener 'idioma'
            String idioma = request.getParameter("idioma");
            if (idioma == null || idioma.trim().isEmpty()) {
                errores.put("idioma", "El idioma es obligatorio.");
            } else if (idioma.length() > 30) {
                errores.put("idioma", "El idioma no puede tener más de 30 caracteres.");
            } else {
                cd.setIdioma(idioma);
            }

            // Validar y obtener 'anioPublicacion'
            String anioPublicacionStr = request.getParameter("anioPublicacion");
            int anioPublicacion = 0;
            if (anioPublicacionStr == null || anioPublicacionStr.isEmpty()) {
                errores.put("anioPublicacion", "El año de publicación es obligatorio.");
            } else {
                try {
                    anioPublicacion = Integer.parseInt(anioPublicacionStr);
                    if (anioPublicacion < 0) {
                        errores.put("anioPublicacion", "El año de publicación no puede ser negativo.");
                    } else {
                        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
                        if (anioPublicacion > anioActual) {
                            errores.put("anioPublicacion", "El año de publicación no puede ser mayor que el año actual.");
                        }
                    }
                    cd.setAñoPublicacion(anioPublicacion);
                } catch (NumberFormatException e) {
                    errores.put("anioPublicacion", "El año de publicación debe ser un número entero.");
                }
            }

            // Validar y obtener 'cantidadTotal'
            String cantidadTotalStr = request.getParameter("cantidadTotal");
            int cantidadTotal = 0;
            if (cantidadTotalStr == null || cantidadTotalStr.isEmpty()) {
                errores.put("cantidadTotal", "La cantidad total es obligatoria.");
            } else {
                try {
                    cantidadTotal = Integer.parseInt(cantidadTotalStr);
                    if (cantidadTotal < 0) {
                        errores.put("cantidadTotal", "La cantidad total no puede ser negativa.");
                    }
                    cd.setCantidadTotal(cantidadTotal);
                } catch (NumberFormatException e) {
                    errores.put("cantidadTotal", "La cantidad total debe ser un número entero.");
                }
            }

            // Validar y obtener 'cantidadDisponible'
            String cantidadDisponibleStr = request.getParameter("cantidadDisponible");
            int cantidadDisponible = 0;
            if (cantidadDisponibleStr == null || cantidadDisponibleStr.isEmpty()) {
                errores.put("cantidadDisponible", "La cantidad disponible es obligatoria.");
            } else {
                try {
                    cantidadDisponible = Integer.parseInt(cantidadDisponibleStr);
                    if (cantidadDisponible < 0) {
                        errores.put("cantidadDisponible", "La cantidad disponible no puede ser negativa.");
                    }
                    cd.setCantidadDisponible(cantidadDisponible);
                } catch (NumberFormatException e) {
                    errores.put("cantidadDisponible", "La cantidad disponible debe ser un número entero.");
                }
            }

            // Validar que 'cantidadTotal' >= 'cantidadDisponible'
            if (cd.getCantidadTotal() < cd.getCantidadDisponible()) {
                errores.put("cantidadTotal", "La cantidad total no puede ser menor que la cantidad disponible.");
            }

            // Validar y obtener 'fechaAdquisicion'
            String fechaAdquisicionStr = request.getParameter("fechaAdquisicion");
            if (fechaAdquisicionStr == null || fechaAdquisicionStr.isEmpty()) {
                errores.put("fechaAdquisicion", "La fecha de adquisición es obligatoria.");
            } else {
                try {
                    Date fechaAdquisicion = Date.valueOf(fechaAdquisicionStr);
                    cd.setFechaAdquisicion(fechaAdquisicion);
                } catch (IllegalArgumentException e) {
                    errores.put("fechaAdquisicion", "La fecha de adquisición tiene un formato inválido (yyyy-MM-dd).");
                }
            }

            // Validar y obtener 'formato'
            String formato = request.getParameter("formato");
            if (formato == null || formato.trim().isEmpty()) {
                errores.put("formato", "El formato es obligatorio.");
            } else if (formato.length() > 20) {
                errores.put("formato", "El formato no puede tener más de 20 caracteres.");
            } else {
                cd.setFormato(formato);
            }

            // Validar y obtener 'duracion'
            String duracion = request.getParameter("duracion");
            if (duracion == null || duracion.trim().isEmpty()) {
                errores.put("duracion", "La duración es obligatoria.");
            } else if (duracion.length() > 20) {
                errores.put("duracion", "La duración no puede tener más de 20 caracteres.");
            } else {
                cd.setDuracion(duracion);
            }

            // Validar y obtener 'contenido'
            String contenido = request.getParameter("contenido");
            if (contenido == null || contenido.trim().isEmpty()) {
                errores.put("contenido", "El contenido es obligatorio.");
            } else if (contenido.length() > 50) {
                errores.put("contenido", "El contenido no puede tener más de 50 caracteres.");
            } else {
                cd.setContenido(contenido);
            }

            // Validar y obtener 'numeroPistas'
            String numeroPistasStr = request.getParameter("numeroPistas");
            int numeroPistas = 0;
            if (numeroPistasStr == null || numeroPistasStr.isEmpty()) {
                errores.put("numeroPistas", "El número de pistas es obligatorio.");
            } else {
                try {
                    numeroPistas = Integer.parseInt(numeroPistasStr);
                    if (numeroPistas < 0) {
                        errores.put("numeroPistas", "El número de pistas no puede ser negativo.");
                    }
                    cd.setNumeroPistas(numeroPistas);
                } catch (NumberFormatException e) {
                    errores.put("numeroPistas", "El número de pistas debe ser un número entero.");
                }
            }

            // Validar y obtener 'requisitosMinimos'
            String requisitosMinimos = request.getParameter("requisitosMinimos");
            if (requisitosMinimos == null || requisitosMinimos.trim().isEmpty()) {
                errores.put("requisitosMinimos", "Los requisitos mínimos son obligatorios.");
            } else if (requisitosMinimos.length() > 100) {
                errores.put("requisitosMinimos", "Los requisitos mínimos no pueden superar los 100 caracteres.");
            } else {
                cd.setRequisitosMinimos(requisitosMinimos);
            }

            // Si hay errores, reenviar al formulario
            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("cd", cd);
                request.getRequestDispatcher("registrarCD.jsp").forward(request, response);
                return;
            }

            // Insertar CD en la base de datos
            CDDAO cdDAO = new CDDAO();
            if (cdDAO.insertarCD(cd)) {
                // Éxito en la inserción
                response.sendRedirect("ListarCDsServlet");
            } else {
                // Error en la inserción
                request.setAttribute("mensajeError", "No se pudo registrar el CD.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al insertar el CD: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Manejo de solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer codificación
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Redirigir al JSP de registro de CD
        request.getRequestDispatcher("registrarCD.jsp").forward(request, response);
    }

    // Manejo de solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite registrar un nuevo CD con validaciones.";
    }
}
