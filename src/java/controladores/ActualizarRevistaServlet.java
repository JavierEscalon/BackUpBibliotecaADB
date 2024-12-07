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

import ModelosDAO.RevistaDAO;
import modelos.Revista;

@WebServlet(name = "ActualizarRevistaServlet", urlPatterns = {"/ActualizarRevistaServlet"})
public class ActualizarRevistaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer la codificación de caracteres para la solicitud y la respuesta
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Crear un mapa para almacenar los mensajes de error
        Map<String, String> errores = new HashMap<>();

        // Crear una instancia de Revista para mantener los datos ingresados
        Revista revista = new Revista();

        try {
            // Validar y obtener 'materialId'
            String materialIdStr = request.getParameter("materialId");
            int materialId = 0;
            if (materialIdStr == null || materialIdStr.isEmpty()) {
                errores.put("materialId", "El ID de material es obligatorio.");
            } else {
                try {
                    materialId = Integer.parseInt(materialIdStr);
                    revista.setMaterialId(materialId);
                } catch (NumberFormatException e) {
                    errores.put("materialId", "El ID de material debe ser un número entero.");
                }
            }

            // Validar y obtener 'titulo'
            String titulo = request.getParameter("titulo");
            if (titulo == null || titulo.trim().isEmpty()) {
                errores.put("titulo", "El título es obligatorio.");
            } else {
                if (titulo.length() > 255) {
                    errores.put("titulo", "El título no puede tener más de 255 caracteres.");
                }
                revista.setTitulo(titulo);
            }

            // Validar y obtener 'editorial'
            String editorial = request.getParameter("editorial");
            if (editorial == null || editorial.trim().isEmpty()) {
                errores.put("editorial", "La editorial es obligatoria.");
            } else {
                if (editorial.length() > 255) {
                    errores.put("editorial", "La editorial no puede tener más de 255 caracteres.");
                }
                revista.setEditorial(editorial);
            }

            // Validar y obtener 'ubicacionFisica'
            String ubicacionFisica = request.getParameter("ubicacionFisica");
            if (ubicacionFisica == null || ubicacionFisica.trim().isEmpty()) {
                errores.put("ubicacionFisica", "La ubicación física es obligatoria.");
            } else {
                if (ubicacionFisica.length() > 255) {
                    errores.put("ubicacionFisica", "La ubicación física no puede tener más de 255 caracteres.");
                }
                revista.setUbicacionFisica(ubicacionFisica);
            }

            // Validar y obtener 'estado'
            String estado = request.getParameter("estado");
            if (estado == null || estado.trim().isEmpty()) {
                errores.put("estado", "El estado es obligatorio.");
            } else {
                if (estado.length() > 100) {
                    errores.put("estado", "El estado no puede tener más de 100 caracteres.");
                }
                revista.setEstado(estado);
            }

            // Validar y obtener 'descripcion'
            String descripcion = request.getParameter("descripcion");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                errores.put("descripcion", "La descripción es obligatoria.");
            } else {
                revista.setDescripcion(descripcion);
            }

            // Validar y obtener 'codigoBarras'
            String codigoBarras = request.getParameter("codigoBarras");
            if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
                errores.put("codigoBarras", "El código de barras es obligatorio.");
            } else {
                if (codigoBarras.length() > 100) {
                    errores.put("codigoBarras", "El código de barras no puede tener más de 100 caracteres.");
                }
                revista.setCodigoBarras(codigoBarras);
            }

            // Validar y obtener 'idioma'
            String idioma = request.getParameter("idioma");
            if (idioma == null || idioma.trim().isEmpty()) {
                errores.put("idioma", "El idioma es obligatorio.");
            } else {
                if (idioma.length() > 50) {
                    errores.put("idioma", "El idioma no puede tener más de 50 caracteres.");
                }
                revista.setIdioma(idioma);
            }

            // Validar y obtener 'ISSN'
            String ISSN = request.getParameter("ISSN");
            if (ISSN == null || ISSN.trim().isEmpty()) {
                errores.put("ISSN", "El ISSN es obligatorio.");
            } else {
                String regexISSN = "^\\d{4}-\\d{3}[\\dX]$";
                if (!ISSN.matches(regexISSN)) {
                    errores.put("ISSN", "El ISSN debe tener el formato XXXX-XXXX.");
                } else {
                    revista.setISSN(ISSN);
                }
            }

            // Validar y obtener 'volumen'
            String volumen = request.getParameter("volumen");
            if (volumen == null || volumen.trim().isEmpty()) {
                errores.put("volumen", "El volumen es obligatorio.");
            } else {
                if (volumen.length() > 50) {
                    errores.put("volumen", "El volumen no puede tener más de 50 caracteres.");
                }
                revista.setVolumen(volumen);
            }

            // Validar y obtener 'numero'
            String numero = request.getParameter("numero");
            if (numero == null || numero.trim().isEmpty()) {
                errores.put("numero", "El número es obligatorio.");
            } else {
                if (numero.length() > 50) {
                    errores.put("numero", "El número no puede tener más de 50 caracteres.");
                }
                revista.setNumero(numero);
            }

            // Validar y obtener 'periodicidad'
            String periodicidad = request.getParameter("periodicidad");
            if (periodicidad == null || periodicidad.trim().isEmpty()) {
                errores.put("periodicidad", "La periodicidad es obligatoria.");
            } else {
                if (periodicidad.length() > 100) {
                    errores.put("periodicidad", "La periodicidad no puede tener más de 100 caracteres.");
                }
                revista.setPeriodicidad(periodicidad);
            }

            // Validar y obtener 'temaPrincipal'
            String temaPrincipal = request.getParameter("temaPrincipal");
            if (temaPrincipal == null || temaPrincipal.trim().isEmpty()) {
                errores.put("temaPrincipal", "El tema principal es obligatorio.");
            } else {
                if (temaPrincipal.length() > 255) {
                    errores.put("temaPrincipal", "El tema principal no puede tener más de 255 caracteres.");
                }
                revista.setTemaPrincipal(temaPrincipal);
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
                    revista.setAñoPublicacion(anioPublicacion);
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
                    revista.setCantidadTotal(cantidadTotal);
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
                    revista.setCantidadDisponible(cantidadDisponible);
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
                    revista.setFechaAdquisicion(fechaAdquisicion);
                } catch (IllegalArgumentException e) {
                    errores.put("fechaAdquisicion", "La fecha de adquisición tiene un formato inválido.");
                }
            }

            // Validar y convertir 'fechaPublicacion'
            String fechaPublicacionStr = request.getParameter("fechaPublicacion");
            Date fechaPublicacion = null;
            if (fechaPublicacionStr == null || fechaPublicacionStr.isEmpty()) {
                errores.put("fechaPublicacion", "La fecha de publicación es obligatoria.");
            } else {
                try {
                    fechaPublicacion = Date.valueOf(fechaPublicacionStr);
                    revista.setFechaPublicacion(fechaPublicacion);
                } catch (IllegalArgumentException e) {
                    errores.put("fechaPublicacion", "La fecha de publicación tiene un formato inválido.");
                }
            }

            // Validar que 'fechaPublicacion' no sea posterior a 'fechaAdquisicion'
            if (fechaPublicacion != null && fechaAdquisicion != null) {
                if (fechaPublicacion.after(fechaAdquisicion)) {
                    errores.put("fechaPublicacion", "La fecha de publicación no puede ser posterior a la fecha de adquisición.");
                }
            }

            // Validar si hay errores
            if (!errores.isEmpty()) {
                // Si hay errores, reenviar al formulario con los mensajes de error y los datos ingresados
                request.setAttribute("errores", errores);
                request.setAttribute("revista", revista);
                request.getRequestDispatcher("editarRevista.jsp").forward(request, response);
                return;
            }

            // Obtener 'tipoMaterialId' desde la base de datos (si no se incluye en el formulario)
            RevistaDAO revistaDAO = new RevistaDAO();
            int tipoMaterialId = revistaDAO.obtenerTipoMaterialIdPorMaterialId(materialId);
            revista.setTipoMaterialId(tipoMaterialId);

            // Actualizar la revista en la base de datos
            if (revistaDAO.actualizarRevista(revista)) {
                // Si la actualización es exitosa, redirigir al listado de revistas
                response.sendRedirect("ListarRevistasServlet");
            } else {
                // Si ocurre un error en la actualización
                request.setAttribute("mensajeError", "No se pudo actualizar la revista.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al actualizar la revista: " + e.getMessage());
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

        String idStr = request.getParameter("id");
        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NumberFormatException("El parámetro id es nulo o vacío.");
            }
            int id = Integer.parseInt(idStr);

            // Crear instancia de RevistaDAO
            RevistaDAO revistaDAO = new RevistaDAO();

            // Obtener la revista por ID
            Revista revista = revistaDAO.obtenerRevistaPorId(id);

            if (revista != null) {
                // Colocar la revista en el atributo de solicitud
                request.setAttribute("revista", revista);
                // Reenviar al JSP de edición
                request.getRequestDispatcher("editarRevista.jsp").forward(request, response);
            } else {
                // Si no se encuentra la revista, mostrar mensaje de error
                request.setAttribute("mensajeError", "No se encontró la revista con el ID proporcionado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "ID de revista inválido: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al obtener la revista: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Método doPost para manejar solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a processRequest() para procesar los datos de actualización
        processRequest(request, response);
    }

    // Información del servlet (opcional)
    @Override
    public String getServletInfo() {
        return "Servlet que permite actualizar una revista con validaciones.";
    }
}
