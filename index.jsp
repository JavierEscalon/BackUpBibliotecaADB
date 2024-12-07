<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
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
    <body class="bg-gradient-primary">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6  bg-login-image d-flex justify-content-center align-items-center">
                                    <img class="mb-4 " src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Universidad_don_bosco.jpg/270px-Universidad_don_bosco.jpg" alt="" width="200" height="200">
                                </div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Bienvenido a la Biblioteca Amigos de Don Bosco!</h1>
                                        </div>

                                        <form class="user" action="LoginController" method="POST">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user"
                                                       id="exampleInputEmail" name="usuario" aria-describedby="emailHelp"
                                                       placeholder="Usuario...">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" class="form-control form-control-user"
                                                       id="exampleInputPassword" placeholder="Password">
                                            </div>

                                            <button class="btn btn-primary btn-user btn-block" type="submit">
                                                Iniciar Sesion
                                            </button>

                                        </form>
                                        <br />
                                        <%
                                            String mensaje = (String) request.getAttribute("mensaje");
                                            String tipoMensaje = (String) request.getAttribute("tipoMensaje");
                                        %>
                                        <% if (mensaje != null) {%>
                                        <div class="alert alert-<%= tipoMensaje%> alert-dismissible fade show" role="alert">
                                            <%= mensaje%>
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <% }%>
                                    </div>
                                </div>
                            </div>
                        </div>
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
    </body>
</html>
