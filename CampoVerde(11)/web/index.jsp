<%@ page import="controladores.ProductoServlet" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.Informacion" %>
<%@ page import="DAO.InformacionDAO" %>
<%@ page import="DAO.InformacionDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Categoria" %>
<%@ page import="DAO.ProductoDAO" %>
<%@ page import="DAO.ProductoDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CampoVerde</title>
        <link rel="stylesheet" href="css/stylesindex.css" />
        <script src="js/index.js"></script>

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
                        <h1 class="logo"><a href="">Campo Verde </a></h1>
                    </div>

                    <div class="container-user">
                        <img src="img/person-fill.svg" alt="alt" style="width: 30px"/>
                        <div class="dropdown-content">
                            <%if (nombreUsuario != null) {
                            %>
                            <div class="col-3"></div>
                            <div class="col-2">
                                <span>Usuario: <%= nombreUsuario %></span>
                            </div>
                            <% 
                            if (rolUsuario != null) {%>
                            
                            <a href="ModificarUsuario.jsp">ModificarUsuario</a>
                            <%switch(rolUsuario) {
                            case 1:
                            %>
                            <a href="listaProductos.jsp">Productos</a>
                            <%
                                            break;
                                        case 2:
                            %>
                            <a href="listaPedidosProv.jsp">Pedidos</a> 
                            <%
                                            break;
                                        case 4:
                            %>
                            <a href="listaClientes.jsp">Administrador</a>    
                            <%
                                break;
                            default:
                            %>
                            <%break;
                    }%>
                            <a href="Chat.jsp">Chat</a>
                            <a href="Foros.jsp">Foro</a>
                            <a href="CerrarSesion">Cerrar Sesión</a>
                <%} 
                            %>
                            <% 
                            }else {
                            %>
                            <a href="login.jsp">Iniciar Sesión</a>
                            <a href="login.jsp">Registrarse</a>
                            <% 
                                                } 
                            %>
                        </div>


                    </div>

                    <div class="container-user2">
                        <a href="Carrito.jsp"><i class="fa-solid fa-basket-shopping"></i></a>
                    </div>
                </div>
            </div>

            <div class="container-navbar">
                <nav class="navbar container">
                    <i class="fa-solid fa-bars"></i>
                    <ul class="menu">
                        <li><a href="#">Inicio</a></li>
                        <li><a href="productos?Categorias=">Categorias</a></li>
                        <li><a href="#destacados">Destacados</a></li>
                        <li><a href="#Nosotros">Sobre Nosotros</a></li>
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

        <section class="banner">
            <div class="content-banner">
                <p>Primera Calidad</p>
                <h2>Productos <br />100% Naturales</h2>
                <a href="productos?Categorias=">Comprar ahora</a>
            </div>
        </section>

        <main class="main-content">
            <br>
<%ProductoDAO productoDAO = new ProductoDAOImpl();
List<Categoria> categorias = null;
try {
                categorias = productoDAO.obtenerCategorias();
            } catch (Exception e) {
                e.printStackTrace();
            }%>
            <section class="container top-categories">
                <h1 class="heading-1">Mejores Categorías</h1>
                <div class="container-categories">
                    <%for (Categoria c: categorias){%>
                    <div class="card-category category-moca">
                        <p><%=c.getNombreCategoria()%></p>
                        <a href="productos?Categorias=<%=c.getIdCategoria()%>"><span>Ver más</span></a>
                    </div>
                    <%}%>
                </div>
            </section>

            <section class="gallery">
                <img
                    src="img/gallery1.jpg"
                    alt="Gallery Img1"
                    class="gallery-img-1"
                    /><img
                    src="img/gallery2.jpg"
                    alt="Gallery Img2"
                    class="gallery-img-2"
                    /><img
                    src="img/gallery3.jpg"
                    alt="Gallery Img3"
                    class="gallery-img-3"
                    /><img
                    src="img/gallery4.jpg"
                    alt="Gallery Img4"
                    class="gallery-img-4"
                    /><img
                    src="img/gallery5.jpg"
                    alt="Gallery Img5"
                    class="gallery-img-5"
                    />
            </section>
            <% 
                ProductoServlet productoServlet = new ProductoServlet();
                List<Producto> productos = null;
                try {
                    productos = productoServlet.obtenerProductosHabilitados();
                } catch (Exception e) {
                    e.printStackTrace();
                }%>
            <%
                InformacionDAO idao = new InformacionDAOImpl();
                Informacion info = new Informacion();
                List<Informacion> blog = null;
                List<Informacion> destacados = null;
                
                try {
                    info.setNombreInformacion("Blog");
                    blog = idao.obtenerInformacion(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            <section class="container specials" id="destacados">
                <h1 class="heading-1">Destacados</h1>

                <div class="container-products">
                    <!-- Producto 1 -->
                    <%for (Producto producto : productos) {
                    %>
                    <%if(producto.getIdProducto()==18||producto.getIdProducto()==19||producto.getIdProducto()==20||producto.getIdProducto()==22){ %>
                    <div class="card-product">
                        <div class="container-img">
                            <img src="img/cafe-irish.jpg" alt="Cafe Irish" />
                            <span class="discount">-13%</span>
                            <div class="button-group">
                                <span>
                                    <i class="fa-regular fa-eye"></i>
                                </span>
                                <span>
                                    <i class="fa-regular fa-heart"></i>
                                </span>
                                <span>
                                    <i class="fa-solid fa-code-compare"></i>
                                </span>
                            </div>
                        </div>
                        <div class="content-card-product">
                            <div class="stars">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-regular fa-star"></i>
                            </div>
                            <h3><%=producto.getNomProducto()%></h3>
                            <span >
                                <form action="AgregarCarrito" method="post">
                                    <input type="hidden" id="idProducto" name="idProducto" value="<%=producto.getIdProducto()%>">
                                    <input type="hidden" id="cantidad" name="cantidad" value="1">
                                    <input type="hidden" id="origen" name="origen" value="index">
                                    <button type="submit" class="add-cart">
                                        <i class="fa-solid fa-basket-shopping"></i>
                                    </button>
                                </form>
                            </span>
                            <p class="price">S/.<%=producto.getPrecioProducto()%> <span>$5.30</span></p>
                        </div>
                    </div>
                    <%}%>
                    <%}%>
                </div>
            </section>

            <section class="container blogs" id="Nosotros">
                <h1 class="heading-1">Sobre Nosotros</h1>

                <div class="container-blogs">
                    <%if (blog!=null) {
                for(Informacion i : blog){%>
                    <div class="card-blog">
                        <div class="container-img">
                            <img src="img/blog-<%=i.getIdInformacion()%>.jpg" alt="Imagen Inf <%=i.getIdInformacion()%>" />
                            <div class="button-group-blog">
                                <span>
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </span>
                                <span>
                                    <i class="fa-solid fa-link"></i>
                                </span>
                            </div>
                        </div>
                        <div class="content-blog">
                            <p id="informacion<%=i.getIdInformacion()%>">
                                <%=i.getInformacion()%>
                            </p>
                            <div class="btn-read-more">Leer más</div>
                            <%if(rolUsuario!=null){
                            if(rolUsuario==4){%>
                            <img src="img/three-dots.svg" id="Editar<%=i.getIdInformacion()%>">
                            <%}}%>
                        </div>
                    </div>
                    <%}}%>
                </div>
            </section>
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

        <script
            src="https://kit.fontawesome.com/81581fb069.js"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
