<%@ page import="controladores.ProductoEmpleado" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Producto" %>
<%@ page import="DAO.ProductoDAO" %>
<%@ page import="DAO.ProductoDAOImpl" %>
<%@ page import="modelo.Proveedor" %>
<%@ page import="DAO.ProveedorDAO" %>
<%@ page import="DAO.ProveedorDAOImpl" %>
<%@ page import="modelo.Pedido" %>
<%@ page import="DAO.PedidoDAO" %>
<%@ page import="DAO.PedidoDAOImpl" %>
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
        <script src="js/FechaHora.js"></script>
    </head>
    <body>
        <%if (rolUsuario==null) {%>
        <%response.sendRedirect("index.jsp");%>
    </body>
    <%}else if(rolUsuario==4 || rolUsuario==2){
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
                        <a href="paginaEmpleado.jsp" class="btn btn-outline-light">Productos</a>
                    </div>
                    <div class ="col-4">
                        <a href="productosInhabilitados.jsp" class="btn btn-outline-light">Productos Inhabilitados</a>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <%
    ProductoDAO productoDAO = new ProductoDAOImpl();
    List<Producto> productos = null;
    Producto p = new Producto();
    p.setIdhabilitado(1);
    try {
        productos = productoDAO.obtenerProductos(p);
    } catch (Exception e) {
        e.printStackTrace();
    }
            
    ProveedorDAO proveedorDAO = new ProveedorDAOImpl();
    List<Proveedor> proveedores = null;
    try {
        proveedores = proveedorDAO.obtenerProveedoresHabilitados();
    } catch (Exception e) {
        e.printStackTrace();
    }
            
    PedidoDAO pedidoDAO = new PedidoDAOImpl();
    List<Pedido> pedidos = null;
    try {
        pedidos = pedidoDAO.obtenerPedidosHabilitados();
    } catch (Exception e) {
        e.printStackTrace();
    }
    %>
    <div class="row">
        <div class="col-md-4">
            <div class="container-fluid ">
                <br>
                <div class="card">
                    <div class="card-header bg-dark">
                        <h1 class="text-white text-center">Pedir Producto</h1>
                    </div>
                    <div class="card-body">
                        <form action="realizarPedido" method="post">
                            <div class="form-group">
                                <label for="proveedor">Proveedor:</label>
                                <select id="proveedor" name="proveedor" class="form-control" required>
                                    <% if (proveedores != null) { %>
                                    <% for (Proveedor proveedor : proveedores) { %>
                                    <option value="<%=proveedor.getUsuario()%>"><%=proveedor.getNombre()%></option>
                                    <%}}%>

                                </select>
                            </div><br>
                            <div class="form-group">
                                <label for="producto">Producto:</label>
                                <select id="producto" name="producto" class="form-control" required>
                                    <% for (Producto producto : productos) { %>
                                    <option value="<%= producto.getIdProducto() %>"><%= producto.getNomProducto() %></option>
                                    <%}%>
                                </select>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-success">Añadir</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <br>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Producto</th>
                        <th>Proveedor</th>
                        <th>Estado</th> 
                        <th>Detalles</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Pedido pedido : pedidos) { %>
                    <tr>
                        <td><%=pedido.getId()%></td>
                        <td><%=pedido.getProducto()%></td>
                        <td><button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalProveedor<%=pedido.getIdusuario()%>"><%=pedido.getUsuario()%></button></td>
                        <td>
                            <% int estado = pedido.getIdestado();
                            switch (estado) {
                                case 1:
                            %>
                            <button type="button" class="btn btn-secondary" disabled><%=pedido.getEstado()%></button>
                            <%
                                    break;
                                case 2:
                            %>
                            <button type="button" class="btn btn-warning"><%=pedido.getEstado()%></button>
                            <%

                                    break;
                                case 3:
                            %>
                            <button type="button" class="btn btn-success" disabled><%=pedido.getEstado()%></button>
                            <%

                                    break;
                            }
                            %>
                        </td>
                        <td>
                            <form> 
                                <input type="hidden" name="idProducto" value="">
                                <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#ModalDetallePedido<%=pedido.getId()%>">Detalles</button>
                            </form>
                        </td>
                        <td>
                            <form action="" method="post"> 
                                <input type="hidden" name="idProducto" value=""> 
                                <button type="submit" class="btn btn-dark">Eliminar</button> 
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <% for (Pedido pedido : pedidos) { %>
    <div class="modal fade" id="ModalDetallePedido<%=pedido.getId()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Detalles</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="" method="post">
                        <div class="form-group">
                            <label for="fechapedido">Fecha de Pedido: <%=pedido.getFecha()%></label>
                        </div>
                        <div class="form-group">
                            <label for="horapedido">Hora de Pedido: <%=pedido.getHora()%></label>
                        </div>
                        <% int estado = pedido.getIdestado();
                        if(estado==2){%>
                        <br>
                        <div class="form-group">
                            <label for="fechaenvio">Fecha de Pedido: <%=pedido.getfEnvio()%></label>
                        </div>
                        <div class="form-group">
                            <label for="horaenvio">Hora de Pedido: <%=pedido.gethEnvio()%></label>
                        </div>
                        <%}if(estado==3){%>
                        <br>
                        <div class="form-group">
                            <label for="fechaenvio">Fecha de Envío: <%=pedido.getfEnvio()%></label>
                        </div>
                        <div class="form-group">
                            <label for="horaenvio">Hora de Envío: <%=pedido.gethEnvio()%></label>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="fechaenvio">Fecha de Recibido: <%=pedido.getfRecibido()%></label>
                        </div>
                        <div class="form-group">
                            <label for="horaenvio">Hora de Recibido: <%=pedido.gethRecibido()%></label>
                        </div>
                        <%}%>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="ModalProveedor<%=pedido.getIdusuario()%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content text-center">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Proveedor</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="nombre">Empresa: <%=pedido.getRsocial()%></label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%}}%>
</body>
