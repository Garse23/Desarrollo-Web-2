package controladores;

import java.io.PrintWriter;
import java.util.List;

import modelo.Producto;
import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductoEmpleado", urlPatterns = {"/ProductoEmpleado"})
public class ProductoEmpleado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ProductoDAO productoDAO = new ProductoDAOImpl();
        List<Producto> productos = null;
        try {
            productos = productoDAO.obtenerProductosHabilitados();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        out.println("<title>Productos</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/stylesempleado.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"Encabezado\">");
        out.println("<img src=\"imagenes/logo.png\" alt=\"logo\" class=\"logo\">");
        out.println("<a href=\"ProductosEmpleado.jsp\" class=\"product-link\">Productos</a>");
        out.println("<a href=\"indexSession.jsp\" class=\"product-link\">Index</a>");
        out.println("<a href=\"indexSession.jsp\" class=\"product-link\">Index</a>");
        out.println("<a href=\"indexSession.jsp\" class=\"product-link\">Index</a>");
        out.println("<a href=\"indexSession.jsp\" class=\"product-link\">Index</a>");
        out.println("<a href=\"indexSession.jsp\" class=\"product-link\">Index</a>");
        out.println("</div>");
        out.println("<table border=\"1\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Precio</th>");
        out.println("<th>Stock</th>");
        out.println("<th>ID Categoría</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (Producto producto : productos) {
            out.println("<tr>");
            out.println("<td>" + producto.getIdProducto() + "</td>");
            out.println("<td>" + producto.getNomProducto() + "</td>");
            out.println("<td>" + producto.getPrecioProducto() + "</td>");
            out.println("<td>" + producto.getStockProducto() + "</td>");
            out.println("<td>" + producto.getIdCategoria() + "</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
