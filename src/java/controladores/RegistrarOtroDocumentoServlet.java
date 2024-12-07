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

import ModelosDAO.OtroDocumentoDAO;
import modelos.OtrosDocumentos;

@WebServlet(name = "RegistrarOtroDocumentoServlet", urlPatterns = {"/RegistrarOtroDocumentoServlet"})
public class RegistrarOtroDocumentoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer la codificación de caracteres para la solicitud y la respuesta
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Crear un mapa para almacenar los mensajes de error
        Map<String, String> errores = new HashMap<>();

        // Crear una instancia de OtrosDocumentos para mantener los datos ingresados
        OtrosDocumentos documento = new OtrosDocumentos();

        try {
            // Establecer 'tipoMaterialId' en 6 para Otros Documentos
            documento.setTipoMaterialId(6);

            // Validar y obtener 'titulo'
            String titulo = request.getParameter("titulo");
            if (titulo == null || titulo.trim().isEmpty()) {
                errores.put("titulo", "El título es obligatorio.");
            } else {
                if (titulo.length() > 255) {
                    errores.put("titulo", "El título no puede tener más de 255 caracteres.");
                }
                documento.setTitulo(titulo);
            }

            // Validar y obtener 'editorial'
            String editorial = request.getParameter("editorial");
            if (editorial == null || editorial.trim().isEmpty()) {
                errores.put("editorial", "La editorial es obligatoria.");
            } else {
                if (editorial.length() > 255) {
                    errores.put("editorial", "La editorial no puede tener más de 255 caracteres.");
                }
                documento.setEditorial(editorial);
            }

            // Validar y obtener 'ubicacionFisica'
            String ubicacionFisica = request.getParameter("ubicacionFisica");
            if (ubicacionFisica == null || ubicacionFisica.trim().isEmpty()) {
                errores.put("ubicacionFisica", "La ubicación física es obligatoria.");
            } else {
                if (ubicacionFisica.length() > 255) {
                    errores.put("ubicacionFisica", "La ubicación física no puede tener más de 255 caracteres.");
                }
                documento.setUbicacionFisica(ubicacionFisica);
            }

            // Validar y obtener 'estado'
            String estado = request.getParameter("estado");
            if (estado == null || estado.trim().isEmpty()) {
                errores.put("estado", "El estado es obligatorio.");
            } else {
                if (estado.length() > 100) {
                    errores.put("estado", "El estado no puede tener más de 100 caracteres.");
                }
                documento.setEstado(estado);
            }

            // Validar y obtener 'descripcion'
            String descripcion = request.getParameter("descripcion");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                errores.put("descripcion", "La descripción es obligatoria.");
            } else {
                documento.setDescripcion(descripcion);
            }

            // Validar y obtener 'codigoBarras'
            String codigoBarras = request.getParameter("codigoBarras");
            if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
                errores.put("codigoBarras", "El código de barras es obligatorio.");
            } else {
                if (codigoBarras.length() > 100) {
                    errores.put("codigoBarras", "El código de barras no puede tener más de 100 caracteres.");
                }
                documento.setCodigoBarras(codigoBarras);
            }

            // Validar y obtener 'idioma'
            String idioma = request.getParameter("idioma");
            if (idioma == null || idioma.trim().isEmpty()) {
                errores.put("idioma", "El idioma es obligatorio.");
            } else {
                if (idioma.length() > 50) {
                    errores.put("idioma", "El idioma no puede tener más de 50 caracteres.");
                }
                documento.setIdioma(idioma);
            }

            // Validar y obtener 'tipoDocumento'
            String tipoDocumento = request.getParameter("tipoDocumento");
            if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
                errores.put("tipoDocumento", "El tipo de documento es obligatorio.");
            } else {
                if (tipoDocumento.length() > 50) {
                    errores.put("tipoDocumento", "El tipo de documento no puede tener más de 50 caracteres.");
                }
                documento.setTipoDocumento(tipoDocumento);
            }

            // Validar y obtener 'organizacionEntidad'
            String organizacionEntidad = request.getParameter("organizacionEntidad");
            if (organizacionEntidad == null || organizacionEntidad.trim().isEmpty()) {
                errores.put("organizacionEntidad", "La organización o entidad es obligatoria.");
            } else {
                if (organizacionEntidad.length() > 100) {
                    errores.put("organizacionEntidad", "La organización o entidad no puede tener más de 100 caracteres.");
                }
                documento.setOrganizacionEntidad(organizacionEntidad);
            }

            // Validar y obtener 'numeroSerie'
            String numeroSerie = request.getParameter("numeroSerie");
            if (numeroSerie == null || numeroSerie.trim().isEmpty()) {
                errores.put("numeroSerie", "El número de serie es obligatorio.");
            } else {
                if (numeroSerie.length() > 50) {
                    errores.put("numeroSerie", "El número de serie no puede tener más de 50 caracteres.");
                }
                documento.setNumeroSerie(numeroSerie);
            }

            // Validar y obtener 'formato'
            String formato = request.getParameter("formato");
            if (formato == null || formato.trim().isEmpty()) {
                errores.put("formato", "El formato es obligatorio.");
            } else {
                if (formato.length() > 50) {
                    errores.put("formato", "El formato no puede tener más de 50 caracteres.");
                }
                documento.setFormato(formato);
            }

            // Validar y obtener 'dimensiones'
            String dimensiones = request.getParameter("dimensiones");
            if (dimensiones == null || dimensiones.trim().isEmpty()) {
                errores.put("dimensiones", "Las dimensiones son obligatorias.");
            } else {
                if (dimensiones.length() > 50) {
                    errores.put("dimensiones", "Las dimensiones no pueden tener más de 50 caracteres.");
                }
                documento.setDimensiones(dimensiones);
            }

            // Validar y obtener 'requisitosEspeciales'
            String requisitosEspeciales = request.getParameter("requisitosEspeciales");
            if (requisitosEspeciales == null || requisitosEspeciales.trim().isEmpty()) {
                errores.put("requisitosEspeciales", "Los requisitos especiales son obligatorios.");
            } else {
                if (requisitosEspeciales.length() > 100) {
                    errores.put("requisitosEspeciales", "Los requisitos especiales no pueden tener más de 100 caracteres.");
                }
                documento.setRequisitosEspeciales(requisitosEspeciales);
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
                        // Validar que 'anioPublicacion' no sea en el futuro
                        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
                        if (anioPublicacion > anioActual) {
                            errores.put("anioPublicacion", "El año de publicación no puede ser mayor que el año actual.");
                        }
                    }
                    documento.setAñoPublicacion(anioPublicacion);
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
                    documento.setCantidadTotal(cantidadTotal);
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
                    documento.setCantidadDisponible(cantidadDisponible);
                } catch (NumberFormatException e) {
                    errores.put("cantidadDisponible", "La cantidad disponible debe ser un número entero.");
                }
            }

            // Validar que 'cantidadTotal' no sea menor que 'cantidadDisponible'
            if (cantidadTotal < cantidadDisponible) {
                errores.put("cantidadTotal", "La cantidad total no puede ser menor que la cantidad disponible.");
            }

            // Validar y convertir 'fechaAdquisicion'
            String fechaAdquisicionStr = request.getParameter("fechaAdquisicion");
            Date fechaAdquisicion = null;
            if (fechaAdquisicionStr == null || fechaAdquisicionStr.isEmpty()) {
                errores.put("fechaAdquisicion", "La fecha de adquisición es obligatoria.");
            } else {
                try {
                    fechaAdquisicion = Date.valueOf(fechaAdquisicionStr);
                    documento.setFechaAdquisicion(fechaAdquisicion);
                } catch (IllegalArgumentException e) {
                    errores.put("fechaAdquisicion", "La fecha de adquisición tiene un formato inválido.");
                }
            }

            // Validar si hay errores
            if (!errores.isEmpty()) {
                // Si hay errores, reenviar al formulario con los mensajes de error y los datos ingresados
                request.setAttribute("errores", errores);
                request.setAttribute("documento", documento);
                request.getRequestDispatcher("registrarOtroDocumento.jsp").forward(request, response);
                return;
            }

            // Utilizar OtroDocumentoDAO para insertar el documento en la base de datos
            OtroDocumentoDAO documentoDAO = new OtroDocumentoDAO();
            if (documentoDAO.insertarOtroDocumento(documento)) {
                // Si la inserción es exitosa, redirigir al listado de otros documentos
                response.sendRedirect("ListarOtrosDocumentosServlet");
            } else {
                // Si ocurre un error en la inserción
                request.setAttribute("mensajeError", "No se pudo registrar el documento.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            // Si ocurre un error, se envía un mensaje al JSP de error
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al insertar el documento: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Método doGet para manejar solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer la codificación de caracteres
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Redirigir al JSP de registro de otro documento
        request.getRequestDispatcher("registrarOtroDocumento.jsp").forward(request, response);
    }

    // Método doPost para manejar solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar los datos de registro
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite registrar un nuevo otro documento con validaciones.";
    }
}
