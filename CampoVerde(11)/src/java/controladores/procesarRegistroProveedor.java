package controladores;

import DAO.ProveedorDAO;
import DAO.ProveedorDAOImpl;
import DAO.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Usuario;
import DAO.UsuarioDAO;
import modelo.Proveedor;

@WebServlet(name = "procesarRegistroProveedor", urlPatterns = {"/procesarRegistroProveedor"})
public class procesarRegistroProveedor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO daoUsuario = new UsuarioDAOImpl();
        Usuario usuario = new Usuario();
        ProveedorDAO daoProveedor = new ProveedorDAOImpl();
        Proveedor proveedor = new Proveedor();

        String nombre = request.getParameter("nombre");
        String rsocial = request.getParameter("rsocial");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        int rol = 2;
        int idUsuario = 0;
        String telefono = request.getParameter("telefono");
        String ruc = request.getParameter("ruc");

        proveedor.setNombre(nombre);
        proveedor.setRazonSocial(rsocial);
        proveedor.setRuc(ruc);
        proveedor.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);
        usuario.setHabilitado(2);
        try {
            daoUsuario.registrarUsuario(usuario);
            idUsuario = daoUsuario.seleccionarID(usuario);
            proveedor.setUsuario(idUsuario);
            daoProveedor.registrarProveedor(proveedor);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }

    }
}
