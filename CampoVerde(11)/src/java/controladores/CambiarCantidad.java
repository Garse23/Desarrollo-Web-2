package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import modelo.Carrito;


@WebServlet(name = "CambiarCantidad", urlPatterns = {"/CambiarCantidad"})
public class CambiarCantidad extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        HttpSession session = request.getSession();
        
        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
        for (Carrito c : carrito) {
            if (idProducto == c.getProducto()) {
                c.setCantidad(cantidad);
                break;
            }
        }
        session.setAttribute("carrito", carrito);
        response.sendRedirect("Carrito.jsp");
    }

}
