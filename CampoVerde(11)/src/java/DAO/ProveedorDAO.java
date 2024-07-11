package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Proveedor;

public interface ProveedorDAO {
    void registrarProveedor(Proveedor proveedor);
    void actualizarProveedor(Proveedor proveedor);
    void eliminarProveedor(Proveedor proveedor);
    List<Proveedor> obtenerProveedoresHabilitados() throws SQLException, ClassNotFoundException;
    List<Proveedor> obtenerProveedoresDeshabilitados() throws SQLException, ClassNotFoundException;
}
