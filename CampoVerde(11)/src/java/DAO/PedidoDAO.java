package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Pedido;

public interface PedidoDAO {
    void realizarPedido(Pedido pedido);
    void verificarEstadoPedido(Pedido pedido);
    void actualizarEstadoPedido(Pedido pedido);
    List<Pedido> obtenerPedidosHabilitados() throws SQLException, ClassNotFoundException;
}
