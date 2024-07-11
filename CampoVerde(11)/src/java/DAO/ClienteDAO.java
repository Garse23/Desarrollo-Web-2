package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Cliente;

public interface ClienteDAO {
    void registrarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
    List<Cliente> obtenerCliente(Cliente cliente) throws SQLException, ClassNotFoundException;
}
