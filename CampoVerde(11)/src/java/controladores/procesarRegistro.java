
package controladores;

import DAO.ClienteDAO;
import DAO.ClienteDAOImpl;
import DAO.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Usuario;
import DAO.UsuarioDAO;
import modelo.Cliente;


@WebServlet(name = "procesarRegistro", urlPatterns = {"/procesarRegistro"})
public class procesarRegistro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO daoUsuario = new UsuarioDAOImpl();
        ClienteDAO daoCliente = new ClienteDAOImpl();
        Usuario usuario = new Usuario();
        Cliente cliente = new Cliente();
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        int rol = 3;
        int idUsuario=0;
        String telefono = request.getParameter("telefono");
        String dni = request.getParameter("dni");
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuario.setHabilitado(1);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setDNI(dni);
        try {
            daoUsuario.registrarUsuario(usuario);
            idUsuario=daoUsuario.seleccionarID(usuario);
            cliente.setUsuario(idUsuario);
            daoCliente.registrarCliente(cliente);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }

    }
}
