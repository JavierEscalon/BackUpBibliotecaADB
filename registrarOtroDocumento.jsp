<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="modelos.OtrosDocumentos" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Registrar Otros Documentos</title>

        <!-- Custom fonts for this template-->
        <link href="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="/BibliotecaAmigosDeDonBoscoWeb/assets/css/sb-admin-2.min.css" rel="stylesheet">

    </head>
    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Don Bosco Admin <sup>1</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="dashboard.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Usuarios
                </div>



                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">



                    <a class="nav-link" href="UsuariosController">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Registrar Usuarios</span>
                    </a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Catalogo
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                       aria-expanded="true" aria-controls="collapsePages">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>Materiales</span>
                    </a>
                    <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Mantenimiento</h6>
                            <a class="collapse-item" href="ListarLibrosServlet">Libros</a>
                            <a class="collapse-item" href="ListarRevistasServlet">Revista</a>
                            <a class="collapse-item" href="ListarCDsServlet">CD</a>
                            <a class="collapse-item" href="ListarOtrosDocumentosServlet">Tesis</a>
                            <a class="collapse-item" href="ListarOtrosDocumentosServlet">Obras</a>
                            <a class="collapse-item" href="ListarOtrosDocumentosServlet">Otros Documentos</a>     
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="consultas.jsp">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Consultas</span></a>
                </li>


                <li class="nav-item">
                    <a class="nav-link" href="PrestamosController">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Prestamos</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ConfiguracionController">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Configuraciones</span></a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>



            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>


                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">



                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                        <%= session.getAttribute("usuarioActual") != null
                                                ? ((modelos.Usuario) session.getAttribute("usuarioActual")).getNombreUsuario() + " "
                                                + ((modelos.Usuario) session.getAttribute("usuarioActual")).getApellidoUsuario()
                    : "Usuario Anónimo"%>
                                    </span>
                                    <img class="img-profile rounded-circle"
                                         src="/BibliotecaAmigosDeDonBoscoWeb/assets/img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Salir
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="row justify-content-center">
                            <div class="col-12 col-md-12 col-lg-12">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-2 text-gray-800">Registrar Otro Documento</h1>


                                <%
                                    // Obtener el documento y el mapa de errores de la solicitud
                                    OtrosDocumentos documento = (OtrosDocumentos) request.getAttribute("documento");
                                    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");

                                    // Inicializar el documento si es nulo
                                    if (documento == null) {
                                        documento = new OtrosDocumentos();
                                    }

                                    // Formateador de fechas
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String fechaAdquisicionStr = documento.getFechaAdquisicion() != null ? sdf.format(documento.getFechaAdquisicion()) : "";
                                %>

                                <!-- Mostrar mensajes de error generales -->
                                <% if (errores != null && !errores.isEmpty()) { %>
                                    <div class="alert alert-danger">
                                        <p>Por favor, corrige los siguientes errores:</p>
                                        <ul>
                                            <% for (String mensajeError : errores.values()) { %>
                                                <li><%= mensajeError %></li>
                                            <% } %>
                                        </ul>
                                    </div>
                                <% } %>

                                <div class="card">
                                    <div class="card-body">
                                        <form action="RegistrarOtroDocumentoServlet" method="POST" novalidate>
                                            <!-- Campo 'titulo' -->
                                            <div class="mb-3">
                                                <label for="titulo" class="form-label">Título:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("titulo")) { %>is-invalid<% } %>" id="titulo" name="titulo" value="<%= (documento.getTitulo() != null) ? documento.getTitulo() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("titulo")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("titulo") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'editorial' -->
                                            <div class="mb-3">
                                                <label for="editorial" class="form-label">Editorial:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("editorial")) { %>is-invalid<% } %>" id="editorial" name="editorial" value="<%= (documento.getEditorial() != null) ? documento.getEditorial() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("editorial")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("editorial") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'anioPublicacion' -->
                                            <div class="mb-3">
                                                <label for="anioPublicacion" class="form-label">Año de Publicación:</label>
                                                <input type="number" class="form-control <% if (errores != null && errores.containsKey("anioPublicacion")) { %>is-invalid<% } %>" id="anioPublicacion" name="anioPublicacion" value="<%= documento.getAñoPublicacion() != null ? documento.getAñoPublicacion() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("anioPublicacion")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("anioPublicacion") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'ubicacionFisica' -->
                                            <div class="mb-3">
                                                <label for="ubicacionFisica" class="form-label">Ubicación Física:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("ubicacionFisica")) { %>is-invalid<% } %>" id="ubicacionFisica" name="ubicacionFisica" value="<%= (documento.getUbicacionFisica() != null) ? documento.getUbicacionFisica() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("ubicacionFisica")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("ubicacionFisica") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'estado' -->
                                            <div class="mb-3">
                                                <label for="estado" class="form-label">Estado:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("estado")) { %>is-invalid<% } %>" id="estado" name="estado" value="<%= (documento.getEstado() != null) ? documento.getEstado() : "Disponible" %>" required>
                                                <% if (errores != null && errores.containsKey("estado")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("estado") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'cantidadTotal' -->
                                            <div class="mb-3">
                                                <label for="cantidadTotal" class="form-label">Cantidad Total:</label>
                                                <input type="number" class="form-control <% if (errores != null && errores.containsKey("cantidadTotal")) { %>is-invalid<% } %>" id="cantidadTotal" name="cantidadTotal" value="<%= documento.getCantidadTotal() %>" required>
                                                <% if (errores != null && errores.containsKey("cantidadTotal")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("cantidadTotal") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'cantidadDisponible' -->
                                            <div class="mb-3">
                                                <label for="cantidadDisponible" class="form-label">Cantidad Disponible:</label>
                                                <input type="number" class="form-control <% if (errores != null && errores.containsKey("cantidadDisponible")) { %>is-invalid<% } %>" id="cantidadDisponible" name="cantidadDisponible" value="<%= documento.getCantidadDisponible() %>" required>
                                                <% if (errores != null && errores.containsKey("cantidadDisponible")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("cantidadDisponible") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'descripcion' -->
                                            <div class="mb-3">
                                                <label for="descripcion" class="form-label">Descripción:</label>
                                                <textarea class="form-control <% if (errores != null && errores.containsKey("descripcion")) { %>is-invalid<% } %>" id="descripcion" name="descripcion" required><%= (documento.getDescripcion() != null) ? documento.getDescripcion() : "" %></textarea>
                                                <% if (errores != null && errores.containsKey("descripcion")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("descripcion") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'fechaAdquisicion' -->
                                            <div class="mb-3">
                                                <label for="fechaAdquisicion" class="form-label">Fecha de Adquisición:</label>
                                                <input type="date" class="form-control <% if (errores != null && errores.containsKey("fechaAdquisicion")) { %>is-invalid<% } %>" id="fechaAdquisicion" name="fechaAdquisicion" value="<%= fechaAdquisicionStr %>" required>
                                                <% if (errores != null && errores.containsKey("fechaAdquisicion")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("fechaAdquisicion") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'codigoBarras' -->
                                            <div class="mb-3">
                                                <label for="codigoBarras" class="form-label">Código de Barras:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("codigoBarras")) { %>is-invalid<% } %>" id="codigoBarras" name="codigoBarras" value="<%= (documento.getCodigoBarras() != null) ? documento.getCodigoBarras() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("codigoBarras")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("codigoBarras") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'idioma' -->
                                            <div class="mb-3">
                                                <label for="idioma" class="form-label">Idioma:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("idioma")) { %>is-invalid<% } %>" id="idioma" name="idioma" value="<%= (documento.getIdioma() != null) ? documento.getIdioma() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("idioma")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("idioma") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'tipoDocumento' -->
                                            <div class="mb-3">
                                                <label for="tipoDocumento" class="form-label">Tipo de Documento:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("tipoDocumento")) { %>is-invalid<% } %>" id="tipoDocumento" name="tipoDocumento" value="<%= (documento.getTipoDocumento() != null) ? documento.getTipoDocumento() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("tipoDocumento")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("tipoDocumento") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'organizacionEntidad' -->
                                            <div class="mb-3">
                                                <label for="organizacionEntidad" class="form-label">Organización/Entidad:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("organizacionEntidad")) { %>is-invalid<% } %>" id="organizacionEntidad" name="organizacionEntidad" value="<%= (documento.getOrganizacionEntidad() != null) ? documento.getOrganizacionEntidad() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("organizacionEntidad")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("organizacionEntidad") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'numeroSerie' -->
                                            <div class="mb-3">
                                                <label for="numeroSerie" class="form-label">Número de Serie:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("numeroSerie")) { %>is-invalid<% } %>" id="numeroSerie" name="numeroSerie" value="<%= (documento.getNumeroSerie() != null) ? documento.getNumeroSerie() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("numeroSerie")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("numeroSerie") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'formato' -->
                                            <div class="mb-3">
                                                <label for="formato" class="form-label">Formato:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("formato")) { %>is-invalid<% } %>" id="formato" name="formato" value="<%= (documento.getFormato() != null) ? documento.getFormato() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("formato")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("formato") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'dimensiones' -->
                                            <div class="mb-3">
                                                <label for="dimensiones" class="form-label">Dimensiones:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("dimensiones")) { %>is-invalid<% } %>" id="dimensiones" name="dimensiones" value="<%= (documento.getDimensiones() != null) ? documento.getDimensiones() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("dimensiones")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("dimensiones") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Campo 'requisitosEspeciales' -->
                                            <div class="mb-3">
                                                <label for="requisitosEspeciales" class="form-label">Requisitos Especiales:</label>
                                                <input type="text" class="form-control <% if (errores != null && errores.containsKey("requisitosEspeciales")) { %>is-invalid<% } %>" id="requisitosEspeciales" name="requisitosEspeciales" value="<%= (documento.getRequisitosEspeciales() != null) ? documento.getRequisitosEspeciales() : "" %>" required>
                                                <% if (errores != null && errores.containsKey("requisitosEspeciales")) { %>
                                                    <div class="invalid-feedback">
                                                        <%= errores.get("requisitosEspeciales") %>
                                                    </div>
                                                <% } %>
                                            </div>

                                            <!-- Botones de acción -->
                                            <button type="submit" class="btn btn-primary">Registrar Documento</button>
                                            <a href="ListarOtrosDocumentosServlet" class="btn btn-secondary">Cancelar</a>
                                        </form>
                                    </div>
                                </div>

                                
                            </div>
                        </div>
                        
                    </div>
                        <br />

                        <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->
                    <!-- Footer -->
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; Biblioteca Don Bosco 2024</span>
                            </div>
                        </div>
                    </footer>
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">¿Listo para partir?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Seleccione «Salir» a continuación si desea finalizar la sesión actual.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                            <a class="btn btn-primary" href="index.html">Salir</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap core JavaScript-->
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/jquery/jquery.min.js"></script>
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/js/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="/BibliotecaAmigosDeDonBoscoWeb/assets/js/demo/datatables-demo.js"></script>
    </body>
</html>
