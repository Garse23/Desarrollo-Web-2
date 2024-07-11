<%@ page import="controladores.ProductoEmpleado" %>
<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ProductoDAO" %>
<%@ page import="DAO.ProductoDAOImpl" %>
<%    Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");
      String nomUsuario = (String) session.getAttribute("nombreUsuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleado</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <%if (rolUsuario==null) {%>
        <%response.sendRedirect("index.jsp");%>
    </body>
    <%}else if(rolUsuario==4 || rolUsuario==2){
    if(nomUsuario!=null){
    %>


    <header>
        <nav class="navbar navbar-dark">
            <div class="row w-100">
                <div class="col-3">
                    <img src="imagenes/logo.png" alt="logo" class="logo" style="width:100px">
                </div>

                <div class="col-1 offset-8 text-end align-items-center">
                    <nav class="navbar">
                        <div class="container-fluid">
                            <div class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Usuario <%=nomUsuario%>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#"></a></li>
                                    <li><a class="dropdown-item" href="#"></a></li>
                                    <li><a class="dropdown-item" href="#"></a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="navbar navbar-brand" style="background-color: #4CAF50">
                <div class="row w-100 text-center">
                    <div class ="col-4">
                        <a href="index.jsp" class="btn btn-outline-light">Inicio</a>
                    </div>
                    <div class ="col-4">
                        <a href="pedidosEmpleados.jsp" class="btn btn-outline-light">Pedidos</a>
                    </div>
                    <div class ="col-4">
                        <a href="paginaEmpleado.jsp" class="btn btn-outline-light">Productos</a>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="row">
        <div class="text-center">
            <br>
            <%
            ProductoDAO productoDAO = new ProductoDAOImpl();
            List<Producto> productos = null;
            try {
                productos = productoDAO.obtenerProductosDeshabilitados();
            } catch (Exception e) {
                e.printStackTrace();
            }
            %>

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>ID Categoría</th>
                        <th>Habilitar</th> 
                        <th>Editar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Producto producto : productos) { %>
                    <tr>
                        <td><%= producto.getIdProducto() %></td>
                        <td><%= producto.getNomProducto() %></td>
                        <td><%= producto.getPrecioProducto() %></td>
                        <td><%= producto.getStockProducto() %></td>
                        <td><%= producto.getIdCategoria() %></td>
                        <td>
                            <form action="HabilitarProducto" method="post"> 
                                <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>"> 
                                <button type="submit" class="btn btn-dark">Habilitar</button> 
                            </form>
                        </td>
                        <td>
                            <form> 
                                <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>">
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalEditar<%= producto.getIdProducto()%>">Editar</button>
                            </form>
                        </td>
                    </tr>

                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <% for (Producto producto : productos) { %>
    <div class="modal fade" id="ModalEditar<%= producto.getIdProducto()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Editar</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="EditarProducto" method="post">
                        <input type="hidden" name="idProducto" id="idProducto" value="<%= producto.getIdProducto()%>">
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" value="<%= producto.getNomProducto()%>" required><br>
                        </div>
                        <div class="form-group">
                            <label for="precio">Precio:</label>
                            <input type="text" id="precio" name="precio" class="form-control" value="<%= producto.getPrecioProducto()%>" required><br>
                        </div>
                        <div class="form-group">
                            <label for="stock">Stock:</label>
                            <input type="text" id="stock" name="stock" class="form-control" value="<%= producto.getStockProducto()%>" required><br>
                        </div>
                        <div class="form-group">
                            <label for="categoria">Categoría:</label>
                            <select id="categoria" name="categoria" class="form-control" required>
                                <option value=1>Agrícola</option>
                                <option value=2>Ganadería</option>
                            </select><br>
                        </div>
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <% }} %>

</body>
<%}%>