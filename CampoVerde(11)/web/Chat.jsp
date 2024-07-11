<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="DAO.UsuarioDAO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="DAO.UsuarioDAOImpl" %>
<%@ page import="modelo.Mensajes" %>
<%@ page import="DAO.MensajesDAO" %>
<%@ page import="DAO.MensajesDAOImpl" %>
<%@ page import="java.sql.Date"%>
<%@ page import="java.sql.Time"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="js/chat.js"></script>
        <link href="css/chat.css" rel="stylesheet">
        <link rel="stylesheet" href="css/vaidroll.css">
        
    </head>

    <body>
        <%
            String nombreUsuario = (String) session.getAttribute("nombreUsuario");
            String NombreDestino = (String) session.getAttribute("NombreDestinatario");
            Integer IdUsuario = (Integer) session.getAttribute("idUsuario");
            Integer IdDestino = (Integer) session.getAttribute("IdDestinatario");
            Integer rolUsuario = (Integer) session.getAttribute("rolUsuario");
            if (IdUsuario == null) {
                response.sendRedirect("index.jsp");
            } else {
        %>
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
                            if (rolUsuario != null) {
                            switch(rolUsuario) {
                            case 1:
                            %>
                            <a href="login.jsp">Modificar Usuario</a>
                            <%
                                            break;
                                        case 2:
                            %>
                            <a href="listaPedidos.jsp">Administrador</a>            
                            <%
                                            break;
                                        case 4:
                            %>
                            <a href="listaPedidos.jsp">Administrador</a>    
                            <%
                            default:
                            %>
                            <a href="Chat.jsp">Chat</a>
                            <a href="Foros.jsp">Foro</a>
                            <a href="CerrarSesion">Cerrar Sesión</a>
                            <%break;
                    }
                } 
                            %>
                            <% 
                            }else {
                            %>
                            <a href="login.jsp">Iniciar Sesión</a>
                            <a href="registro.jsp">Registrarse</a>
                            <% 
                                                } 
                            %>
                        </div>


                    </div>

                    <div class="container-user2">
                        <i class="fa-solid fa-basket-shopping"></i>
                    </div>
                </div>
            </div>

            <div class="container-navbar">
                <nav class="navbar container">
                    <br>
                </nav>
            </div>
        </header>
        <section class="message-area">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="chat-area">
                            <div class="chatlist">
                                <div class="modal-dialog-scrollable">
                                    <div class="modal-content">
                                        <div class="chat-header">
                                            <div class="msg-search">
                                                <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Buscar" aria-label="search">
                                                <a class="add" href="#"><img class="img-fluid" src="https://mehedihtml.com/chatbox/assets/img/add.svg" alt="add"></a>
                                            </div>
                                        </div>

                                        <div class="modal-body">
                                            <div class="chat-lists">
                                                <div class="tab-content" id="myTabContent">
                                                    <div class="tab-pane fade show active" id="Open" role="tabpanel" aria-labelledby="Open-tab">
                                                        <div class="chat-list">
                                                            <%
                                                                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                                                                Usuario user = new Usuario();
                                                                user.setHabilitado(1);
                                                                List<Usuario> usuarios = null;
                                                                try {
                                                                    usuarios = usuarioDAO.obtenerUsuarios(user);
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            %>
                                                            <% for (Usuario usuario : usuarios) { %>
                                                            <div class="d-flex align-items-center chat-item">
                                                                <div class="flex-shrink-0">
                                                                    <img class="img-fluid" src="imagenes/person-fill.svg" alt="user img">
                                                                </div>
                                                                <div class="flex-grow-1 ms-3">
                                                                    <h3><%=usuarioDAO.SeleccionarNombreUsuario(usuario)%></h3>
                                                                    <p><%=usuario.getCorreo()%></p>
                                                                </div>
                                                                <form action="DefinirDestinatario" method="post" class="ms-auto">
                                                                    <input type="hidden" name="id" id="id" value="<%=usuario.getId()%>">
                                                                    <button type="submit" class="btn"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                                                                </form>
                                                            </div>
                                                            <% } %>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%
                                MensajesDAO mensajesDAO = new MensajesDAOImpl();
                                List<Mensajes> mensajes = null;
                                Mensajes mensaje = new Mensajes();
                                mensaje.setIdUEnvio(IdUsuario);

                                if (IdDestino == null) {
                                    mensaje.setIdURecibido(0);
                                } else {
                                    mensaje.setIdURecibido(IdDestino);
                                }

                                try {
                                    mensajes = mensajesDAO.obtenerMensajesDisponibles(mensaje);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                            <div class="chatbox">
                                <div class="modal-dialog-scrollable">
                                    <div class="modal-content">
                                        <div class="msg-head">
                                            <div class="row">
                                                <div class="col-8">
                                                    <div class="d-flex align-items-center">
                                                        <span class="chat-icon"><img class="img-fluid" src="" alt="image title"></span>
                                                        <div class="flex-shrink-0">
                                                            <img class="img-fluid" src="imagenes/person-fill.svg" alt="user img">
                                                        </div>
                                                        <div class="flex-grow-1 ms-3">
                                                            <h3><%if(NombreDestino==null){%>

                                                                <%}else{%>
                                                                <%=NombreDestino%>
                                                                <%}%></h3>
                                                            <p></p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-4">
                                                    <ul class="moreoption">
                                                        <li class="navbar nav-item dropdown">
                                                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                                <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                                            </a>
                                                            <ul class="dropdown-menu">
                                                                <li><a class="dropdown-item" href="#">Action</a></li>
                                                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-body">
                                            <div class="msg-body">
                                                <ul>
                                                    <% LocalDate fecha = null; %>
                                                    <% LocalDate fechaActual = LocalDate.now(); %>
                                                    <% for (Mensajes msg : mensajes) { %>
                                                    <% if (fecha == null || !fecha.equals(msg.getFecha().toLocalDate())) {
                                                    fecha = msg.getFecha().toLocalDate(); %>
                                                    <% if (fecha.equals(fechaActual)) { %>
                                                    <div class="divider">
                                                        <h6>Hoy</h6>
                                                    </div>
                                                    <% } else { %>
                                                    <div class="divider">
                                                        <h6><%= fecha %></h6>
                                                    </div>
                                                    <% } %>
                                                    <% } %>
                                                    <% if (msg.getIdUEnvio() == IdUsuario) { %>
                                                    <li class="repaly">
                                                        <p><%= msg.getMensaje() %></p>
                                                        <span class="time"><%= msg.getHora() %></span>
                                                    </li>
                                                    <% } %>
                                                    <% if (msg.getIdURecibido() == IdUsuario) { %>
                                                    <li class="sender">
                                                        <p><%= msg.getMensaje() %> </p>
                                                        <span class="time"><%= msg.getHora() %></span>
                                                    </li>
                                                    <% } %>
                                                    <% } %>

                                                </ul>
                                            </div>
                                        </div>

                                        <div class="send-box">
                                            <%if(IdDestino==null){%>
                                            <form>
                                                <input type="text" class="form-control" aria-label="message…" placeholder="Escribe un mensaje...">
                                                <button type="button"><i class="fa fa-paper-plane" aria-hidden="true"></i> Enviar</button>
                                            </form>
                                            <%}else{%>
                                            <form action="EnviarMensaje" method="post">
                                                <input type="text" class="form-control" aria-label="message…" placeholder="Escribe un mensaje..." id="mensaje" name="mensaje">
                                                <button type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i> Enviar</button>
                                            </form>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <% } %>
    </body>
</html>
