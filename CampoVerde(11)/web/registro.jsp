<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        
        <script src="js/bootstrap.min.js"></script>
        <style>
            body{
                background-image: url("css/iga/banner.jpg");
                background-size:cover;
            }
            
            .card-body{
                background-image: url("imagenes/BG.png");
            }
        </style>
    </head>
    <% String nombreUsuario = (String) session.getAttribute("nombreUsuario");
   Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");%>
    <body>
        <header>
            <div class="container-fluid" style="background: #4caf50">
                <nav class="navbar navbar-brand">
                    <div class="row w-100">
                        <div class="col-3">
                            <a href="index.jsp"><img src="imagenes/logo.png" alt="logo" class="logo" style="width:100px"></a>
                        </div>
                        <div class="col-6 offset-3 text-end">
                            <a href="login.jsp"><img src="imagenes/login.png" alt="Imagen 1"style="width:100px"></a>
                            <a href="registro.jsp"><img src="imagenes/registrate.png" alt="Imagen 1"style="width: 100px"></a>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <br>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4 align-items-center">
                    <div class="card">
                        <div class="card-header text-center">
                            <h2>Registro de Usuario</h2>
                        </div>
                        <div class="card-body">
                            <form action="procesarRegistro" method="post">
                                <div class="form-group">
                                    <label for="nombre">Nombre Completo:</label>
                                    <input type="text" id="nombre" name="nombre" required class="form-control">
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="correo">Correo Electrónico:</label>
                                    <input type="email" id="correo" name="correo" required class="form-control">
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="contrasena">Contraseña:</label>
                                    <input type="password" id="contrasena" name="contrasena" required class="form-control">
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="telefono">Teléfono:</label>
                                    <input type="tel" id="telefono" name="telefono" required class="form-control">
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="dni">DNI:</label>
                                    <input type="text" id="dni" name="dni" required class="form-control">
                                </div>
                                <br>
                                <button type="submit" class="btn btn-success">Registrarse</button>
                            </form>
                        </div>
                        <div class="card-footer">
                            <h7>¿Eres proveedor? <a href="" data-bs-toggle="modal" data-bs-target="#ModalProveedor" >Registrate aqui</a></h7>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="ModalProveedor" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5 text-center" id="exampleModalLabel">Proveedor</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="procesarRegistroProveedor" method="post">
                            <div class="form-group">
                                <label for="nombre">Nombre Completo:</label>
                                <input type="text" id="nombre" name="nombre" required class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="rsocial">Razón Social:</label>
                                <input type="text" id="rsocial" name="rsocial" required class="form-control">
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="correo">Correo Electrónico:</label>
                                <input type="email" id="correo" name="correo" required class="form-control">
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="contrasena">Contraseña:</label>
                                <input type="password" id="contrasena" name="contrasena" required class="form-control">
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="telefono">Teléfono:</label>
                                <input type="tel" id="telefono" name="telefono" required class="form-control">
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="ruc">Ruc:</label>
                                <input type="text" id="ruc" name="ruc" required class="form-control">
                            </div>
                            <br>
                            <button type="submit" class="btn btn-success">Registrarse</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
