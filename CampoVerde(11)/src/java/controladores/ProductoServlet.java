package controladores;

import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Categoria;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAOImpl();

    public List<Producto> obtenerProductosHabilitados() throws SQLException, ClassNotFoundException {
        Producto producto = new Producto();
        producto.setIdhabilitado(1);
        return productoDAO.obtenerProductos(producto);
    }

    public List<Producto> obtenerProductosPorCategoria(int categoriaId) throws SQLException, ClassNotFoundException {
        return productoDAO.obtenerProductosPorCategoria(categoriaId);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        return productoDAO.buscarProductosPorNombre(nombre);
    }

    public List<Categoria> obtenerCategorias() throws SQLException, ClassNotFoundException {
        return productoDAO.obtenerCategorias();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String categoriaIdStr = request.getParameter("categoria");
            String busqueda = request.getParameter("busqueda");
            List<Producto> productos;

            if (busqueda != null && !busqueda.isEmpty()) {
                productos = buscarProductosPorNombre(busqueda);
            } else if (categoriaIdStr != null && !categoriaIdStr.isEmpty()) {
                int categoriaId = Integer.parseInt(categoriaIdStr);
                productos = obtenerProductosPorCategoria(categoriaId);
            } else {
                productos = obtenerProductosHabilitados();
            }

            List<Categoria> categorias = obtenerCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/Categorias.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("Formato de ID de categoría inválido.", e);
        }
    }
}
