<%-- 
    Document   : obrasInsertar
    Created on : 21-nov-2024, 20:06:22
    Author     : HP
--%>

<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Dashboard</title>

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
                            <a class="collapse-item" href="">CD</a>
                            <a class="collapse-item" href="libros.jsp">Libros</a>
                            <a class="collapse-item" href="obras.jsp">Obras</a>  
                            <a class="collapse-item" href="">Otros Documentos</a>
                            <a class="collapse-item" href="">Revistas</a>
                            <a class="collapse-item" href="tesis.jsp">Tesis</a>                         
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

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Agregar Obra</h1>

                        <form action="ControladorObra">
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Tipo de Obra</label>
                                <input name="tipo" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Genero</label>
                                <input name="genero" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Formato</label>
                                <input name="formato" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Dimensiones</label>
                                <input name="dimensiones" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Título</label>
                                <input name="titulo" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Descripción</label>
                                <input name="descripcion" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Año de Publicación</label>
                                <input name="publicacion" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Editorial</label>
                                <input name="editorial" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Ubicación</label>
                                <input name="ubicacion" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Codigo de Barras</label>
                                <input name="codigo" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Idioma</label>
                                <input name="idioma" type="text" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Cantidad</label>
                                <input name="cantidad" type="text" class="form-control">
                            </div>    
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Autor</label>
                                <input name="autor" type="text" class="form-control">
                            </div>                                
                            <button class="btn btn-primary w-100 py-2" type="submit" name="accion" value="agregar">Agregar</button>
                        </form>
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
                            <a class="btn btn-primary" href="index.jsp">Salir</a>
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
