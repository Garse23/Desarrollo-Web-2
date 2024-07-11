package controladores;

import DAO.PedidoDAOImpl;
import DAO.PedidoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Pedido;

@WebServlet(name = "realizarPedido", urlPatterns = {"/realizarPedido"})
public class realizarPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pedido pedido = new Pedido();
        PedidoDAO daoPedido = new PedidoDAOImpl();
        int Proveedor = Integer.parseInt(request.getParameter("proveedor"));
        int Producto = Integer.parseInt(request.getParameter("producto"));
        int Estado=1;
        
        pedido.setIdusuario(Proveedor);
        pedido.setIdproducto(Producto);
        pedido.setIdestado(Estado);
        
        daoPedido.realizarPedido(pedido);
        response.sendRedirect("pedidosEmpleados.jsp");
    }
    
    

}
