<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page import="DAO.UsuarioDAOImpl"%>
<%
    UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
    Usuario usuarioLogeado = null;

    // Obtener el ID del usuario desde la sesión
    Integer idUsuarioSesion = (Integer) session.getAttribute("idUsuario");
    if (idUsuarioSesion != null) {
        List<Usuario> usuarios = usuarioDAO.obtenerUsuariosHabilitados();
        for (Usuario u : usuarios) {
            if (u.getId() == idUsuarioSesion) {
                usuarioLogeado = u;
                break;
            }
        }
    }

    if (usuarioLogeado == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Perfil de Usuario</title>
        <link rel="stylesheet" href="css/stylesModiUser.css">
    </head>
    <% String nombreUsuario = (String) session.getAttribute("nombreUsuario");
   Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");%>
    <body>
        <header>
            <div class="container-hero">
                <div class="container hero">
                    <div class="customer-support">

                    </div>

                    <div class="container-logo">
                        <img src="img/logoCampo.png" style="width: 90px;" alt="">
                        <h1 class="logo"><a href="index.jsp">Campo Verde </a></h1>
                    </div>

                    <div class="container-user">
                        <img src="img/person-fill.svg" alt="alt" style="width: 30px"/>
                        <div class="dropdown-content">
                            <%if (nombreUsuario != null) {
                            %>
                            <div class="col-3"></div>
                            <div class="col-2">
                                <span>Usuario: <%= nombreUsuario %></span>
                                <a href="ModificarUsuario.jsp">Modificar Usuario</a>
                                <a href="CerrarSesion">Cerrar Sesión</a>
                            </div>
                            <% 
                            if (rolUsuario != null) {
                            switch(rolUsuario) {
                            case 1:
                            %>
                            <a href="ModificarUsuario.jsp">Modificar Usuario</a>
                            <a href="CerrarSesion">Cerrar Sesión</a>
                            <%
                                            break;
                                        case 2:
                            %>
                            <a href="listaPedidos.jsp">Administrador</a>
                            <a href="CerrarSesion">Cerrar Sesión</a>                
                            <%
                                            break;
                                        case 4:
                            %>
                            <a href="listaPedidos.jsp">Administrador</a>
                            <a href="CerrarSesion">Cerrar Sesión</a>    
                            <%
                            default:
                                break;
                        }
                    } 
                            %>
                            <% 
                            }else {
                            %>
                            <a href="login.jsp">Iniciar Sesión</a>
                            <a href="login.jsp#">Registrarse</a>
                            <% 
                                                } 
                            %>
                        </div>


                    </div>

                    <div class="container-user2">
                        <i class="fa-solid fa-basket-shopping"></i>
                    </div>
                </div>
            </div>

            <div class="container-navbar">
                <nav class="navbar container">
                    <i class="fa-solid fa-bars"></i>
                    <ul class="menu">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="Categorias.jSP">Categorias</a></li>
                        <li><a href="#">Destacados</a></li>
                        <li><a href="#">Sobre Nosotros</a></li>
                    </ul>

                    <form class="search-form">
                        <input type="search" placeholder="Buscar..." />
                        <button class="btn-search">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </nav>
            </div>
        </header>
        <main>
            <div class="box">
                <div class="inner-box">
                    <div class="forms-wrap">
                        <form autocomplete="off" class="profile-form" method="post" action="ActualizarUsuarioServlet">

                            <div class="heading">
                                <h2>Perfil de Usuario</h2>
                            </div>

                            <div class="actual-form">
                                
                                <div class="input-wrap">
                                    <h2>Correo actual:</h2>
                                    <input
                                        id="correoO"
                                        name="correoO"
                                        type="email"
                                        class="input-field"
                                        value="<%= usuarioLogeado.getCorreo()  %>"
                                        required
                                        readonly
                                        />
                                </div>
                                
                                <div class="input-wrap">
                                    <h2>Correo nuevo:</h2>
                                    <input
                                        id="correo"
                                        name="correo"
                                        type="email"
                                        class="input-field"
                                        value="<%= usuarioLogeado.getCorreo()  %>"
                                        required
                                        />
                                </div>
                                        
                                <div class="input-wrap">
                                    <h2>Contraseña actual</h2>
                                    <input
                                        id="contrasena"
                                        name="contrasena"
                                        type="text"
                                        class="input-field"
                                        value="<%= usuarioLogeado.getContrasena() %> "
                                        readonly
                                        required
                                        />
                                </div>
                                        
                                <div class="input-wrap">
                                    <h2>Contraseña nueva</h2>
                                    <input
                                        id="contrasenaNueva"
                                        name="contrasenaNueva"
                                        type="text"
                                        class="input-field"
                                        value="Ingrese su contraseña"
                                        required
                                        />
                                </div>
                                <input type="hidden" name="idUsuario" value="<%= usuarioLogeado.getId() %>" />

                                <input type="submit" value="Guardar Cambios" class="sign-btn" />

                                <input type="button" value="Cerrar Sesión" class="sign-btn" onclick="window.location.href='logout.jsp';" />

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <footer class="footer">
            <div class="container container-footer">
                <div class="menu-footer">
                    <div class="contact-info">
                        <p class="title-footer">Información de Contacto</p>
                        <ul>
                            <li>
                                Dirección: Ica Subtanjalla :v
                            </li>
                            <li>Teléfono: 948 684 478</li>
                            <li>EmaiL: campoverdeORG@gmail.com</li>
                            <li>Siguenos como @CampoVerde en:</li>
                        </ul>
                        <div class="social-icons">
                            <span class="facebook">
                                <i class="fa-brands fa-facebook-f"></i>
                            </span>
                            <span class="twitter">
                                <i class="fa-brands fa-twitter"></i>
                            </span>
                            <span class="instagram">
                                <i class="fa-brands fa-instagram"></i>
                            </span>
                        </div>
                    </div>

                    <div class="information">
                        <p class="title-footer">Información</p>
                        <ul>
                            <li><a href="#">Acerca de Nosotros</a></li>
                            <li><a href="#">Información Delivery</a></li>
                            <li><a href="#">Politicas de Privacidad</a></li>
                            <li><a href="#">Términos y condiciones</a></li>
                            <li><a href="#">Contactános</a></li>
                        </ul>
                    </div>

                    <div class="my-account">
                        <p class="title-footer">Mi cuenta</p>

                        <ul>
                            <li><a href="#">Modificar Usuario</a></li>
                            <li><a href="#">Reembolsos</a></li>
                        </ul>
                    </div>

                    <div class="newsletter">
                        <p class="title-footer">Atención al cliente </p>

                        <div class="content">
                            <p>
                                +51 956-456-343
                            </p>
                            <p>
                                (021) 5654-2343
                            </p>
                        </div>
                    </div>
                </div>

                <div class="copyright">
                    <p>
                        Todos los derechos reservados Campo verde &copy; 2024
                    </p>

                    <img src="img/payment.png" alt="Pagos">
                </div>
            </div>
        </footer>
        
        <script>
            document.getElementById('contrasenaNueva').addEventListener('focus', function() {
                if (this.value === 'Ingrese su contraseña') {
                    this.value = '';
                }
            });

            document.getElementById('contrasenaNueva').addEventListener('blur', function() {
                if (this.value === '') {
                    this.value = 'Ingrese su contraseña';
                }
            });
        </script>                      
                         

        <script
            src="https://kit.fontawesome.com/81581fb069.js"
            crossorigin="anonymous"
        ></script>
        <!-- Javascript file -->
        <script src="js/app.js"></script>
        
    </body>
</html>
