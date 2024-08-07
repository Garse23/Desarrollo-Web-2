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
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/FechaHora.js"></script>
        <title>SB Admin 2 - Tables</title>

        <!-- Custom fonts for this template -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        
    </head>
    
    <body id="page-top">
        <%if (rolUsuario==null || rolUsuario==2 ||rolUsuario==1) {%>
        <%response.sendRedirect("index.jsp");%>
        
    </body>
    <%}else if(rolUsuario==4 || rolUsuario==1){
    %>
    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-text mx-3">Campo Verde </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Tablas
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <% if(rolUsuario==1||rolUsuario==4){%>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ListaEmpleado"
                   aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Productos</span>
                </a>
                <div id="ListaEmpleado" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Seleccione Tabla:</h6>
                        <a class="collapse-item" href="listaProductos.jsp">Productos</a>
                        <a class="collapse-item" href="listaPedidos.jsp">Pedidos</a>
                        <a class="collapse-item" href="listaProductosIna.jsp">Productos Inhabilitados</a>
                        <a class="collapse-item" href="">Categorias</a>
                    </div>
                </div>
            </li>
            <%}%>
            <!-- Nav Item - Utilities Collapse Menu -->
            <%if(rolUsuario==4){%>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ListaAdministrador"
                   aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Usuarios</span>
                </a>
                <div id="ListaAdministrador" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Seleccione Tabla:</h6>
                        <a class="collapse-item" href="listaClientes.jsp">Clientes</a>
                        <a class="collapse-item" href="listaEmpleados.jsp">Empleados</a>
                        <a class="collapse-item" href="listaProveedores.jsp">Proveedores</a>
                    </div>
                </div>
            </li>
            <%}%>

            <%if(rolUsuario==4||rolUsuario==2){%>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ListaProveedor"
                   aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-table"></i>
                    <span>
                        <%if(rolUsuario==4){%>
                        Ventana de Proveedor
                        <%}else{%>
                        Pedidos                      
                        <%}%>
                    </span>
                </a>
                <div id="ListaProveedor" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Seleccione Tabla:</h6>
                        <a class="collapse-item" href="listaPedidosProv.jsp">Pedidos</a>
                    </div>
                </div>
            </li>
            <%}%>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Administrador</span>
                                <img class="img-profile rounded-circle"
                                     src="imagenes/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="index.jsp" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Cerrar sesi�n
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <%
                PedidoDAO pedidoDAO = new PedidoDAOImpl();
                List<Pedido> pedidos = null;
                try {
                    pedidos = pedidoDAO.obtenerPedidosHabilitados();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                %>
                <div class="container-fluid">

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Demostraci�n tablas</h6>
                        </div>
                        <div class="card-body">
                            <div class="table">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
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
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Campo Verde 2024</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">�Listo para salir?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">�</span>
                    </button>
                </div>
                <div class="modal-body">Seleccione "Cerrar Sesi�n" a continuacion si esta listo para terminar su sesion actual</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                    <a class="btn btn-primary" href="CerrarSesion">Cerrar Sesi�n</a>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/datatables-demo.js"></script>
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
                            <label for="fechaenvio">Fecha de Env�o: <%=pedido.getfEnvio()%></label>
                        </div>
                        <div class="form-group">
                            <label for="horaenvio">Hora de Env�o: <%=pedido.gethEnvio()%></label>
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
    <% } %>
</body>
<%}%>
</html>