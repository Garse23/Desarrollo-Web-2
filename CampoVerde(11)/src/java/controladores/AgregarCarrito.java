package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Carrito;

@WebServlet(name = "AgregarCarrito", urlPatterns = {"/AgregarCarrito"})
public class AgregarCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int Cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String origen = request.getParameter("origen");
        HttpSession session = request.getSession();

        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        boolean lista = false;
        for (Carrito c : carrito) {
            if (idProducto == c.getProducto()) {
                c.setCantidad(c.getCantidad() + Cantidad);
                lista = true;
                break;
            }
        }
        if(!lista){
        carrito.add(new Carrito(idProducto, Cantidad));
        }
        session.setAttribute("carrito", carrito);
        switch (origen) {
            case "index":
                response.sendRedirect("index.jsp");
                break;
            default:
                ;
        }
    }
}
