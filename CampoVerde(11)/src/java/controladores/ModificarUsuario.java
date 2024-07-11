/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import DAO.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "ActualizarUsuarioServlet", urlPatterns = {"/ActualizarUsuarioServlet"})

public class ModificarUsuario  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        // Crear objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        // Actualizar usuario en la base de datos
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        usuarioDAO.actualizarUsuario(usuario);

        // Redirigir al perfil
        response.sendRedirect("ModificarUsuario.jsp");
    }
}
