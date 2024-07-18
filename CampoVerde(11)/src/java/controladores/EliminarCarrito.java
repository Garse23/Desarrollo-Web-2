package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import modelo.Carrito;

@WebServlet(name = "EliminarCarrito", urlPatterns = {"/EliminarCarrito"})
public class EliminarCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        HttpSession session = request.getSession();
        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
        Iterator<Carrito> iterator = carrito.iterator();
        while (iterator.hasNext()) {
            Carrito c = iterator.next();
            if (idProducto == c.getProducto()) {
                iterator.remove();
                break;
            }
        }
        response.sendRedirect("Carrito.jsp");
    }

}
