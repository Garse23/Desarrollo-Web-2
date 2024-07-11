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


/**
 *
 * @author Usuario
 */
@WebServlet(name = "ProcesarAProducto", urlPatterns = {"/ProcesarAProducto"})
public class ProcesarAProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO daoProducto = new ProductoDAOImpl();
        Producto producto = new Producto();
        String nombre=request.getParameter("nombre");
        Double precio=Double.parseDouble(request.getParameter("precio"));
        int stock=Integer.parseInt(request.getParameter("stock"));
        int categoria=Integer.parseInt(request.getParameter("categoria"));
        producto.setNomProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);
        producto.setIdCategoria(categoria);
        daoProducto.AgregarProducto(producto);
        response.sendRedirect("paginaEmpleado.jsp");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
