<%@ page import="controladores.ProductoServlet" %>
<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Categoria" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CampoVerde</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
        <link rel="stylesheet" href="css/stylesindex.css"/>
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
                        <h1 class="logo"><a href="/">Campo Verde </a></h1>
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
                        <li><a href="Categorias.jsp">Categorias</a></li>
                        <li><a href="Categorias.jsp">Destacados</a></li>
                        <li><a href="#">Sobre Nosotros</a></li>
                    </ul>

                    <form class="search-form" method="GET" action="productos">
                        <input type="search" name="busqueda" placeholder="Buscar..." />
                        <button class="btn-search" type="submit">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </nav>
            </div>
        </header>

        <div class="container">
            <form class="d-flex justify-content-center align-items-center mt-3" method="GET" action="productos">
                <select name="categoria" id="categoria" class="form-select me-2" style="width: 200px; height: 45px;">
                    <option value="">Todas las categorías</option>
                    <% 
                        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
                        if (categorias != null) {
                            for (Categoria categoria : categorias) {
                    %>
                    <option value="<%= categoria.getIdCategoria() %>"><%= categoria.getNombreCategoria() %></option>
                    <% 
                            }
                        }
                    %>
                </select>
                <button type="submit" class="btn btn-primary" style="height: 45px; width: 50px">Filtrar</button>
            </form>


            <div class="row mt-4">
                <% 
                    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                    if (productos != null) {
                        for (Producto producto : productos) {
                %>
                <div class="col-sm-3 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h3><%= producto.getNomProducto() %></h3>
                        </div>
                        <div class="card-body">
                            <label>S/<%= producto.getPrecioProducto() %></label>
                        </div>
                        <div class="card-footer text-center">
                            <a href="Categorias.jsp" class="btn btn-success">Añadir al carrito</a>
                        </div>
                    </div>
                </div>
                <% 
                        }
                    }
                %>
            </div>
        </div>

        <footer class="footer">
            <div class="container container-footer">
                <div class="menu-footer">
                    <div class="contact-info">
                        <p class="title-footer">Información de Contacto</p>
                        <ul>
                            <li>
                                Dirección: Ica Subtanjalla 
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

        <script
            src="https://kit.fontawesome.com/81581fb069.js"
            crossorigin="anonymous"
        ></script>

    </body>
</html>
