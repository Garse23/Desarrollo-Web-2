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
            <nav class="navbar navbar-dark">
                <div class="row w-100 align-items-center text-end">
                    <div class="col-3 text-start">
                        <a href="index.jsp"><img src="imagenes/logo.png" alt="logo" class="" style="width:100px"></a>
                    </div>
                    <%if (IdUsuario != null){ %>
                    <div class="col-2 offset-6">
                        <div class="dropdown">
                            <button class="btn btn-light" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="imagenes/person-fill.svg" alt="alt" style="width: 30px"/>
                            </button>
                            <ul class="dropdown-menu" style="right:0; left: auto">
                                <li><span class="dropdown-item-text">Usuario: <%=nombreUsuario%></span></li>
                                <li><a class="dropdown-item" href="Chat.jsp">Chat</a></li>
                                <li><a class="dropdown-item" href="Foros.jsp">Foro</a></li>
                                    <% if (rolUsuario != null) {
                                            switch(rolUsuario) {
                                                case 1:
                                    %>
                                <li><a class="dropdown-item" href="paginaEmpleado.jsp">Empleado</a></li>
                                    <%
                                                    break;
                                                case 2:
                                    %>
                                <li><a class="dropdown-item" href="Proveedor.jsp">Proveedor</a></li>
                                    <%
                                                    break;
                                                case 4:
                                    %>
                                <li><a class="dropdown-item" href="paginaEmpleado.jsp">Empleado</a></li>
                                <li><a class="dropdown-item" href="Proveedor.jsp">Proveedor</a></li>
                                    <%
                                    default:%>
                                <li><a class="dropdown-item text-bg-danger" href="CerrarSesion">Cerrar Sesión</a></li>
                                    <%
                                            break;
                                    }
                                }%>
                            </ul>
                        </div>
                    </div>
                    <%} else {
                    %>
                    <div class="col-6"></div>
                    <div class="col-2  text-end">
                        <a href="login.jsp"><img src="imagenes/login.png" alt="Imagen 1"style="width:100px"></a>
                        <a href="registro.jsp"><img src="imagenes/registrate.png" alt="Imagen 1"style="width: 100px"></a>
                    </div>
                    <% 
                                        } 
                    %>
                    <div class="col-1">
                        <a href="Carrito.jsp"><img src="imagenes/carrito.png" alt=""style="width: 100px"></a>
                    </div>
                </div>
            </nav>
            <nav style="background: #4caf50">
                <br>
            </nav>
        </header>
        <div class="container">
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
                                <th>Editar</th>
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
                                        <input type="hidden" name="idProducto" value=""> 
                                        <button type="submit" class="btn btn-dark">Eliminar</button> 
                                    </form>
                                </td>
                                <td>
                                    <form> 
                                        <input type="hidden" name="idProducto" value="">
                                        <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalEditar">Editar</button>
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
                </div>
                <div class="col-6">
                    <br>
                    <div id="paypal-button-conteiner"></div>
                </div>
                <% }%>

            </div>
        </div>
        <script>
            var total = <%= total != null ? total : 0.0 %>;
        </script>
    </body>
</html>
