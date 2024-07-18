package controladores;

import DAO.PedidoDAO;
import DAO.PedidoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.Pedido;

@WebServlet(name = "ActualizarPedido", urlPatterns = {"/ActualizarPedido"})
public class ActualizarPedido extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pedido pedido = new Pedido();
        PedidoDAO pdao = new PedidoDAOImpl();
        int estado= Integer.parseInt(request.getParameter("estado"));
        int id= Integer.parseInt(request.getParameter("id"));
        pedido.setIdestado(estado);
        pedido.setId(id);
        pdao.actualizarEstadoPedido(pedido);
        response.sendRedirect("listaPedidos.jsp");
        
    }
}
