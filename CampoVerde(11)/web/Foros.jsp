<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Foro" %>
<%@ page import="DAO.ForoDAO" %>
<%@ page import="DAO.ForoDAOImpl" %>
<%@ page import="java.sql.Date"%>
<%@ page import="java.sql.Time"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foros</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/stylesindex.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
    </head>
    <% String nombreUsuario = (String) session.getAttribute("nombreUsuario");
       Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");
        Integer IdUsuario = (Integer) session.getAttribute("idUsuario");%>
    <body>
        <%if (IdUsuario==null){
        response.sendRedirect("index.jsp");
            }else{%>
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
                <br>
            </div>
        <%
        ForoDAO foroDAO = new ForoDAOImpl();
        List<Foro> foros = null;
        Foro foro = new Foro();
        foro.setIdHabilitado(1);
            
            try {
            foros = foroDAO.obtenerForos(foro);
            } catch (Exception e) {
            e.printStackTrace();
            }
        %>
        <br>
        <div class="row">
            <div class="col-2 align-content-center text-center">
                <% if(rolUsuario==2 || rolUsuario==4){%>
                <div class="container-fluid">
                    <div class="card">
                        <div class="card-header">
                            Crear Foro
                        </div>
                        <div class="card-body">
                            <form action="AgregarForo" method="post">
                                <div class="form-group text-start">
                                    <label class="form-label">Titulo:</label>
                                    <input type="text" class="form-control" name="titulo" id="titulo">
                                </div>
                                <div class="form-group text-start">
                                    <label class="form-label">Mensaje:</label>
                                    <textarea class="form-control" placeholder="400 caracteres" id="mensaje" name="mensaje" rows="13"></textarea>
                                </div><br>
                                <div class="form-group text-start">
                                    <input type="submit" class="btn btn-success" value="Crear">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
            <div class="col-8 text-center">

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Titulo</th>
                            <th>Creador</th>
                            <th>Fecha</th>
                            <th></th>
                                <% if(rolUsuario==2 || rolUsuario==4){%>
                            <th>Eliminar</th> 
                            <th>Editar</th>
                                <%}%>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Foro f : foros) { %>
                        <tr>
                            <td><%=f.getIdForo()%></td>
                            <td><%=f.getNombreForo()%></td>
                            <td><%=f.getIdUsuario()%></td>
                            <td><%=f.getFechaForo()%></td>
                            <td>
                                <form action="Foro" method="get">
                                    <input type="hidden" name="Foro" id="Foro" value="<%=f.getIdForo()%>">
                                    <button type="submit" class="btn btn-primary">Acceder al foro</button>
                                </form>
                            </td>
                            <% if(rolUsuario==2 || rolUsuario==4){%>
                            <td>
                                <form action="" method="post"> 
                                    <input type="hidden" name="idProducto" value=""> 
                                    <button type="submit" class="btn btn-warning">Eliminar</button> 
                                </form>
                            </td>
                            <td>
                                <form> 
                                    <input type="hidden" name="idProducto" value="">
                                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#ModalEditar">Editar</button>
                                </form>
                            </td>
                            <%}%>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
                    <%}%>
    </body>
</html>
