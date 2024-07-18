<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Carrito" %>
<%@ page import="controladores.ProductoServlet" %>
<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
    </head>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CampoVerde</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script
            src="https://www.paypal.com/sdk/js?client-id=AUGAqFV6WctWmIDjXQeTJMfpv7UTtPne1C3AjHQnYDVMxjau5izB44Me8tOV90Ktm2ubUCSZe5BGJbju&currency=USD"
        ></script>
        <script src="js/carrito.js" ></script>
        <link rel="stylesheet" href="css/stylesindex.css" />
        <link rel="stylesheet" href="css/CarritoSinLogear.css" />
    </head>
    <% String nombreUsuario = (String) session.getAttribute("nombreUsuario");
   Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");
    Integer IdUsuario = (Integer) session.getAttribute("idUsuario");
    List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
    Double total = (Double) session.getAttribute("total");
    if (total==null){
    total=0.0;
        }
    %>
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
                </nav>
            </div>
        </header>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container" style="size: 100px">
            <div class="row">
                <div class="col-6">
                    <h1>Carrito de Compras</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th>ID Categoría</th>
                                <th>Eliminar</th> 
                            </tr>
                        </thead>
                        <% 
                        ProductoServlet productoServlet = new ProductoServlet();
                        List<Producto> productos = null;
                        try {
                            productos = productoServlet.obtenerProductosHabilitados();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }%>

                        <% if (carrito != null) { %>
                        <% for (Carrito c : carrito) { %>
                        <%if (productos != null) {
                            for (Producto producto : productos) {
                            if (producto.getIdProducto()==c.getProducto()){
                        %>
                        <tbody>
                            <tr>
                                <td><%= producto.getIdProducto() %></td>
                                <td><%= producto.getNomProducto()%></td>
                                <td>
                                    <form action="CambiarCantidad" method="post">
                                        <input type="hidden" id="idProducto" name="idProducto" value="<%=c.getProducto()%>">
                                        <input type="number" id="cantidad" name="cantidad" value="<%= c.getCantidad()%>" class="btn btn-outline-dark" style="width: 80px">
                                    </form>
                                </td>
                                <td><%= producto.getPrecioProducto()*c.getCantidad()%></td>
                                <td><%=producto.getIdCategoria()%></td>
                                <td>
                                    <form action="EliminarCarrito" method="post"> 
                                        <input type="hidden" name="idProducto" value="<%=c.getProducto()%>"> 
                                        <button type="submit" class="btn btn-dark">Eliminar</button> 
                                    </form>
                                </td>

                            </tr>
                        </tbody>
                        <%total=total+producto.getPrecioProducto()*c.getCantidad();%>
                        <% } %>
                        <% } %>
                        <% } %>
                        <% } %>
                    </table>
                    <h3>Total: <%= total%></h3>
                    <a href="productos?Categorias=?"><button class="btn btn-warning"> Seguir comprando</button></a>
                </div>
                <div class="col-6">
                    <br>
                    <%if(IdUsuario!=null){%>
                    <div id="paypal-button-conteiner"></div>
                    <%}else{%>
                    <div class="FLogin">
                        <a href="login.jsp">Logueate</a> para realizar la compra
                    </div>

                    <%}%>
                </div>
                <% }%>
            </div>
        </div>
        <script>
            var total = <%= total != null ? total : 0.0 %>;
        </script>
    </body>
</html>
