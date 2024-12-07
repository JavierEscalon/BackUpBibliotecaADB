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

@WebServlet(name = "ActualizarCDServlet", urlPatterns = {"/ActualizarCDServlet"})
public class ActualizarCDServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer la codificación de caracteres
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Mapa para almacenar mensajes de error
        Map<String, String> errores = new HashMap<>();

        // Crear instancia de CD
        CD cd = new CD();

        try {
            // materialId
            String materialIdStr = request.getParameter("materialId");
            int materialId = 0;
            if (materialIdStr == null || materialIdStr.isEmpty()) {
                errores.put("materialId", "El ID de material es obligatorio.");
            } else {
                try {
                    materialId = Integer.parseInt(materialIdStr);
                    cd.setMaterialId(materialId);
                } catch (NumberFormatException e) {
                    errores.put("materialId", "El ID de material debe ser un número entero.");
                }
            }

            // titulo
            String titulo = request.getParameter("titulo");
            if (titulo == null || titulo.trim().isEmpty()) {
                errores.put("titulo", "El título es obligatorio.");
            } else {
                if (titulo.length() > 255) {
                    errores.put("titulo", "El título no puede superar los 255 caracteres.");
                }
                cd.setTitulo(titulo);
            }

            // editorial
            String editorial = request.getParameter("editorial");
            if (editorial == null || editorial.trim().isEmpty()) {
                errores.put("editorial", "La editorial es obligatoria.");
            } else {
                if (editorial.length() > 255) {
                    errores.put("editorial", "La editorial no puede superar los 255 caracteres.");
                }
                cd.setEditorial(editorial);
            }

            // autor
            String autor = request.getParameter("autor");
            if (autor == null || autor.trim().isEmpty()) {
                errores.put("autor", "El autor es obligatorio.");
            } else {
                if (autor.length() > 100) {
                    errores.put("autor", "El autor no puede superar los 100 caracteres.");
                }
                cd.setAutor(autor);
            }

            // ubicacionFisica
            String ubicacionFisica = request.getParameter("ubicacionFisica");
            if (ubicacionFisica == null || ubicacionFisica.trim().isEmpty()) {
                errores.put("ubicacionFisica", "La ubicación física es obligatoria.");
            } else {
                if (ubicacionFisica.length() > 255) {
                    errores.put("ubicacionFisica", "La ubicación física no puede superar los 255 caracteres.");
                }
                cd.setUbicacionFisica(ubicacionFisica);
            }

            // estado
            String estado = request.getParameter("estado");
            if (estado == null || estado.trim().isEmpty()) {
                errores.put("estado", "El estado es obligatorio.");
            } else {
                if (estado.length() > 100) {
                    errores.put("estado", "El estado no puede superar los 100 caracteres.");
                }
                cd.setEstado(estado);
            }

            // descripcion
            String descripcion = request.getParameter("descripcion");
            if (descripcion == null || descripcion.trim().isEmpty()) {
                errores.put("descripcion", "La descripción es obligatoria.");
            } else {
                cd.setDescripcion(descripcion);
            }

            // codigoBarras
            String codigoBarras = request.getParameter("codigoBarras");
            if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
                errores.put("codigoBarras", "El código de barras es obligatorio.");
            } else {
                if (codigoBarras.length() > 100) {
                    errores.put("codigoBarras", "El código de barras no puede superar los 100 caracteres.");
                }
                cd.setCodigoBarras(codigoBarras);
            }

            // idioma
            String idioma = request.getParameter("idioma");
            if (idioma == null || idioma.trim().isEmpty()) {
                errores.put("idioma", "El idioma es obligatorio.");
            } else {
                if (idioma.length() > 50) {
                    errores.put("idioma", "El idioma no puede superar los 50 caracteres.");
                }
                cd.setIdioma(idioma);
            }

            // añoPublicacion
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
                            errores.put("anioPublicacion", "El año de publicación no puede ser mayor al año actual.");
                        }
                    }
                    cd.setAñoPublicacion(anioPublicacion);
                } catch (NumberFormatException e) {
                    errores.put("anioPublicacion", "El año de publicación debe ser un número entero.");
                }
            }

            // cantidadTotal
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

            // cantidadDisponible
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

            // Validar que cantidadTotal >= cantidadDisponible
            if (cantidadTotal < cantidadDisponible) {
                errores.put("cantidadTotal", "La cantidad total no puede ser menor que la cantidad disponible.");
            }

            // fechaAdquisicion
            String fechaAdquisicionStr = request.getParameter("fechaAdquisicion");
            Date fechaAdquisicion = null;
            if (fechaAdquisicionStr == null || fechaAdquisicionStr.isEmpty()) {
                errores.put("fechaAdquisicion", "La fecha de adquisición es obligatoria.");
            } else {
                try {
                    fechaAdquisicion = Date.valueOf(fechaAdquisicionStr);
                    cd.setFechaAdquisicion(fechaAdquisicion);
                } catch (IllegalArgumentException e) {
                    errores.put("fechaAdquisicion", "La fecha de adquisición tiene un formato inválido (yyyy-MM-dd).");
                }
            }

            // formato
            String formato = request.getParameter("formato");
            if (formato == null || formato.trim().isEmpty()) {
                errores.put("formato", "El formato es obligatorio.");
            } else {
                if (formato.length() > 20) {
                    errores.put("formato", "El formato no puede superar los 20 caracteres.");
                }
                cd.setFormato(formato);
            }

            // duracion
            String duracion = request.getParameter("duracion");
            if (duracion == null || duracion.trim().isEmpty()) {
                errores.put("duracion", "La duración es obligatoria.");
            } else {
                if (duracion.length() > 20) {
                    errores.put("duracion", "La duración no puede superar los 20 caracteres.");
                }
                cd.setDuracion(duracion);
            }

            // contenido
            String contenido = request.getParameter("contenido");
            if (contenido == null || contenido.trim().isEmpty()) {
                errores.put("contenido", "El contenido es obligatorio.");
            } else {
                if (contenido.length() > 50) {
                    errores.put("contenido", "El contenido no puede superar los 50 caracteres.");
                }
                cd.setContenido(contenido);
            }

            // numero_pistas
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

            // requisitosMinimos
            String requisitosMinimos = request.getParameter("requisitosMinimos");
            if (requisitosMinimos == null || requisitosMinimos.trim().isEmpty()) {
                errores.put("requisitosMinimos", "Los requisitos mínimos son obligatorios.");
            } else {
                if (requisitosMinimos.length() > 100) {
                    errores.put("requisitosMinimos", "Los requisitos mínimos no pueden superar los 100 caracteres.");
                }
                cd.setRequisitosMinimos(requisitosMinimos);
            }

            // Si hay errores, reenviar al formulario
            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("cd", cd);
                request.getRequestDispatcher("editarCD.jsp").forward(request, response);
                return;
            }

            // Obtener tipoMaterialId desde la BD
            CDDAO cdDAO = new CDDAO();
            int tipoMaterialId = cdDAO.obtenerTipoMaterialIdPorMaterialId(materialId);
            cd.setTipoMaterialId(tipoMaterialId);

            // Actualizar CD
            if (cdDAO.actualizarCD(cd)) {
                // Actualización exitosa
                response.sendRedirect("ListarCDsServlet");
            } else {
                // Error en actualización
                request.setAttribute("mensajeError", "No se pudo actualizar el CD.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al actualizar el CD: " + e.getMessage());
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new NumberFormatException("El parámetro id es nulo o vacío.");
            }
            int id = Integer.parseInt(idStr);

            CDDAO cdDAO = new CDDAO();
            CD cd = cdDAO.obtenerCDPorId(id);

            if (cd != null) {
                request.setAttribute("cd", cd);
                request.getRequestDispatcher("editarCD.jsp").forward(request, response);
            } else {
                request.setAttribute("mensajeError", "No se encontró el CD con el ID proporcionado.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "ID de CD inválido: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al obtener el CD: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
        return "Servlet que permite actualizar un CD con validaciones.";
    }
}
