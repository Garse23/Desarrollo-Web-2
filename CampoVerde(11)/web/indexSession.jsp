<%@ page import="controladores.ProductoServlet" %>
<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CampoVerde</title>
        <link rel="stylesheet" href="css/stylesindex.css">
    </head>
    <body>
        <div class="imagenesLogin">
            <img src="imagenes/logo.png" alt="logo" class="logo">
            <% 
    String nombreUsuario = (String) session.getAttribute("nombreUsuario");
    Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");
    if (nombreUsuario != null) {
            %>
            <span>Bienvenido, <%= nombreUsuario %></span>
            <% 
                if (rolUsuario != null) {
                    switch(rolUsuario) {
                        case 1:
            %>
            <a href="paginaEmpleado.jsp" class="Externo">Empleado</a>
            <%
                            break;
                        case 2:
            %>
            <a href="paginaProveedor.jsp" class="Externo">Proveedor</a>
            <%
                            break;
                        case 4:
            %>
            <a href="paginaEmpleado.jsp" class="Externo">Empleado</a>
            <a href="paginaProveedor.jsp" class="Externo">Proveedor</a>
            <%
            default:
                // Otros casos, si es necesario
                break;
        }
    } 
            %>
            <a href="CerrarSesion"><img src="imagenes/cerrar.png" alt="Cerrar sesión" class="cerrar"></a>
                <% 
                    } else {
                %>
            <a href="login.jsp"><img src="imagenes/login.png" alt="Iniciar sesión"></a>
            <a href="registro.jsp"><img src="imagenes/registrate.png" alt="Registrarse"></a>
                <% 
                    } 
                %>
            <img src="imagenes/carrito.png" alt="">
        </div>

        <div class="navbar">
            <a href="index.jsp">Inicio</a>
            <a href="#categorias">Categorías</a>
            <a href="#sobre-nosotros">Sobre nosotros</a>
        </div>

        <div class="anuncios">
            <img src="imagenes/anuncio.png" alt="">
        </div>

        <div class="productos">
            <% 
                ProductoServlet productoServlet = new ProductoServlet();
                List<Producto> productos = null;
                try {
                    productos = productoServlet.obtenerProductosHabilitados();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
                if (productos != null) {
                    for (Producto producto : productos) {
            %>
            <div class="producto">
                <img src="" alt="Producto">
                <h3><%= producto.getNomProducto()%></h3>
                <p>Precio: $<%= producto.getPrecioProducto() %></p>
                <button class="btn-agregar">Agregar al carrito</button>
            </div>
            <% 
                    }
                }
            %>
        </div>
    </body>
</html>
