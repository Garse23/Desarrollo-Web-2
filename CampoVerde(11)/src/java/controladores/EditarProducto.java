package controladores;

import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Producto;


@WebServlet(name = "EditarProducto", urlPatterns = {"/EditarProducto"})
public class EditarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO daoProducto = new ProductoDAOImpl();
        Producto producto = new Producto();
        String nombre = request.getParameter("nombre");
        Double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        producto.setNomProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);
        producto.setIdCategoria(categoria);
        producto.setIdProducto(idProducto);

        daoProducto.EditarProducto(producto);
        response.sendRedirect("listaProductos.jsp");

    }

}
