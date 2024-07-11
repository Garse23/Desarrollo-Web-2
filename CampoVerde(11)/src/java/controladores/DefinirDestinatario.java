package controladores;

import DAO.UsuarioDAOImpl;
import DAO.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "DefinirDestinatario", urlPatterns = {"/DefinirDestinatario"})
public class DefinirDestinatario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdDestinatario = Integer.parseInt(request.getParameter("id"));
        Usuario Dusuario = new Usuario();
        UsuarioDAO dao = new UsuarioDAOImpl();
        Dusuario.setId(IdDestinatario);
        int idrol=dao.seleccionarRol(Dusuario);
        Dusuario.setRol(idrol);
        String NombreDestinatario = dao.SeleccionarNombreUsuario(Dusuario);
        HttpSession session = request.getSession();
        session.setAttribute("IdDestinatario", IdDestinatario);
        session.setAttribute("NombreDestinatario", NombreDestinatario);
        response.sendRedirect("Chat.jsp");
    }

}
