package controladores;

import DAO.DetalleVentaDAO;
import DAO.DetalleVentaDAOImpl;
import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import DAO.VentaDAO;
import DAO.VentaDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelo.Carrito;
import modelo.DetalleVenta;
import modelo.Venta;
import modelo.Producto;
import controladores.ProductoServlet;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ProcesarVenta", urlPatterns = {"/ProcesarVenta"})
public class ProcesarVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Se ha llamado a ProcesarVenta Servlet doget");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Venta venta = new Venta();
        DetalleVenta dv = new DetalleVenta();
        VentaDAO ventadao = new VentaDAOImpl();
        DetalleVentaDAO dvdao = new DetalleVentaDAOImpl();
        ProductoDAO pdao = new ProductoDAOImpl();
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        venta.setFechaVenta(Date.valueOf(fechaActual));
        venta.setHoraVenta(Time.valueOf(horaActual));
        HttpSession session = request.getSession();
        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
        Integer IdUsuario = (Integer) session.getAttribute("idUsuario");
        System.out.println(IdUsuario);
        if (IdUsuario == null) {
            response.sendRedirect("Carrito.jsp?alert=UsuarioNoLogueado");
        } else {
            venta.setIdUsuario(IdUsuario);

            ProductoServlet productoServlet = new ProductoServlet();
            List<Producto> productos = null;
            try {
                productos = productoServlet.obtenerProductosHabilitados();
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean Excedente = false;
            for (Carrito c : carrito) {

                double total = 0.0;
                for (Producto p : productos) {
                    if (p.getIdProducto() == c.getProducto()) {
                        if (p.getStockProducto() >= c.getCantidad()) {
                            total = c.getCantidad() * p.getPrecioProducto();
                            dv.setCantidad(c.getCantidad());
                            dv.setIdProducto(c.getProducto());
                            int Nuevostock = p.getStockProducto() - c.getCantidad();
                            p.setStockProducto(Nuevostock);
                            pdao.EditarProducto(p);
                        } else {
                            System.out.println("Producto Excedente");
                            Excedente = true;
                            break;
                        }
                    }
                }
                if (!Excedente) {
                    ventadao.insertarVenta(venta);
                    int IdVenta = ventadao.IdVenta(venta);
                    System.out.println("IdVenta: " + IdVenta);
                    dv.setIdVenta(IdVenta);
                    dv.setPrecioTotal(total);
                    System.out.println("Total:" + total);
                    dvdao.insertarDetalleVenta(dv);
                }
            }
        }
    }
;
}
