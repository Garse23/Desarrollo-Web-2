package controladores;

import DAO.UsuarioDAO;
import DAO.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import modelo.Usuario;

@WebServlet(name = "procesarLogin", urlPatterns = {"/procesarLogin"})
public class procesarLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO usuariodao = new UsuarioDAOImpl() {
        };
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        int idRol = 0;
        int idUsuario = 0;
        String NombreUsuario = "";
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        idUsuario = usuariodao.seleccionarID(usuario);
        usuario.setId(idUsuario);
        idRol = usuariodao.seleccionarRol(usuario);
        usuario.setRol(idRol);

        NombreUsuario = usuariodao.SeleccionarNombreUsuario(usuario);
        HttpSession session = request.getSession();
        int habilitado = usuariodao.seleccionarHabilitado(usuario);
        if (habilitado == 1) {
            session.setAttribute("nombreUsuario", NombreUsuario);
            session.setAttribute("rolUsuario", idRol);
            session.setAttribute("idUsuario", idUsuario);
            response.sendRedirect("index.jsp");

        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
