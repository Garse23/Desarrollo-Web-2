package controladores;


import DAO.ProductoDAO;

import DAO.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAOImpl();

    public List<Producto> obtenerProductosHabilitados() throws SQLException, ClassNotFoundException {
        return productoDAO.obtenerProductosHabilitados();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Producto> productos = obtenerProductosHabilitados();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("/productos.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}