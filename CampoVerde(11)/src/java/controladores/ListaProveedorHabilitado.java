package controladores;

import DAO.ProveedorDAOImpl;
import DAO.ProveedorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Proveedor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ListaProveedorHabilitado", urlPatterns = {"/ListaProveedorHabilitado"})
public class ListaProveedorHabilitado extends HttpServlet {

    private ProveedorDAO proveedorDAO = new ProveedorDAOImpl();
    
    public List<Proveedor> obtenerProveedoresHabilitados() throws SQLException, ClassNotFoundException {
        return proveedorDAO.obtenerProveedoresHabilitados();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
            List<Proveedor> proveedores = obtenerProveedoresHabilitados();
            request.setAttribute("proveedores", proveedores);
            request.getRequestDispatcher("/pedidosEmpleados.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
