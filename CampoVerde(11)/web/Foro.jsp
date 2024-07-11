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
            <nav class="navbar navbar-dark">
                <div class="row w-100 align-items-center text-end">
                    <div class="col-3 text-start">
                        <a href="index.jsp"><img src="imagenes/logo.png" alt="logo" class="" style="width:100px"></a>
                    </div>
                    <div class="col-4 offset-1">
                        <div class="text-center">
                            <h2>Foros de discusión</h2>
                        </div>
                    </div>
                    <%if (IdUsuario != null){ %>
                    <div class="col-2 offset-1">
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
            <div class="col-2">

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
