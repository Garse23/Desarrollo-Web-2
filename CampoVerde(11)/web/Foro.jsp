<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Foro" %>
<%@ page import="DAO.ForoDAO" %>
<%@ page import="DAO.ForoDAOImpl" %>
<%@ page import="modelo.RespuestasForo" %>
<%@ page import="DAO.RespuestasForoDAO" %>
<%@ page import="DAO.RespuestasForoDAOImpl" %>
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
       Integer IdForo = (Integer) request.getAttribute("Foro");
        Integer IdUsuario = (Integer) session.getAttribute("idUsuario");%>
    <body>
        <%if (IdUsuario!=null){%>
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
        </header>
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
        RespuestasForoDAO RFDAO = new RespuestasForoDAOImpl();
        List<RespuestasForo> RespuestasF = null;
        RespuestasForo RF = new RespuestasForo();
        RF.setIdHabilitado(1);
        RF.setIdForo(IdForo);
            
            try {
            RespuestasF = RFDAO.obtenerRespuestas(RF);
            } catch (Exception e) {
            e.printStackTrace();
            }
        %>
        <br>
        <div class="row">
            <div class="col-2 text-center">
                <a href="Foros.jsp"><button class="btn btn-outline-danger">volver</button></a>
            </div>

            <div class="col-8">
                <% for (Foro f : foros) { %>
                <% if(IdForo==f.getIdForo()){ %>
                <div class="card form-label">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-10">
                                <%=f.getNombreForo()%>
                            </div>
                            <div class="col-2 text-end">
                                <%=f.getFechaForo()%>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="">
                            <%=f.getAnuncioForo()%>
                        </div>
                    </div>
                    <div class="card-footer">
                        <%=f.getIdUsuario()%>
                    </div>
                </div>
                <br>
                <% for (RespuestasForo rf : RespuestasF) { %>
                <div class="card">
                    <div class="card-body">
                        <%=rf.getRespuestaForo()%>
                    </div>
                </div>
                    <br>
                <%}%>
                <div class="card">
                    <div class="card-body">
                        <form action="ResponderForo" method="post">
                            <input type="hidden" id="IdForo" name="IdForo" value="<%=IdForo%>">
                            <div class="form-group">
                                <div class="row w-100">
                                    <div class="col-11">
                                        <textarea id="mensajeforo" name="mensajeforo" class="form-control" placeholder="Mensaje (400 caracteres)"></textarea>
                                    </div>
                                    <div class="col-1">
                                        <input type="submit" class="btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%}%>
                <%}%>
            </div>
        </div>
        <%}else{
    response.sendRedirect("index.jsp");
        }%>
    </body>
</html>
