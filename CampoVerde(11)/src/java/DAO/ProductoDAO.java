package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Producto;
import modelo.Categoria;

public interface ProductoDAO {
    List<Producto> obtenerProductosHabilitados() throws SQLException, ClassNotFoundException;
    List<Producto> obtenerProductosPorCategoria(int categoriaId) throws SQLException, ClassNotFoundException;
    List<Producto> buscarProductosPorNombre(String nombre) throws SQLException, ClassNotFoundException;
    List<Categoria> obtenerCategorias() throws SQLException, ClassNotFoundException;
    void AgregarProducto(Producto producto);
    void EliminarProducto(Producto producto);
    void EditarProducto(Producto producto);
    void PedirProducto(Producto producto);
    void SeleccionarProducto(Producto producto);
    void InhabilitarProducto(Producto producto);
    void HabilitarProducto(Producto producto);
    
}
