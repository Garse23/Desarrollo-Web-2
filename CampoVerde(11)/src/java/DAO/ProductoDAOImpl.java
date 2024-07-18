package DAO;

import controladores.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;
import modelo.Producto;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public List<Producto> obtenerProductos(Producto prd) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT idProducto, nomProducto, precioProducto, stockProducto, idCategoria FROM producto WHERE idHabilitado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,prd.getIdhabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nomProducto = rs.getString("nomProducto");
                double precioProducto = rs.getDouble("precioProducto");
                int stockProducto = rs.getInt("stockProducto");
                int idCategoria = rs.getInt("idCategoria");

                // Agrega declaraciones de impresión para verificar los valores obtenidos de la base de datos
                System.out.println("ID del producto: " + idProducto);
                System.out.println("Nombre del producto: " + nomProducto);
                System.out.println("Precio del producto: " + precioProducto);
                System.out.println("Stock del producto: " + stockProducto);
                System.out.println("ID de la categoría: " + idCategoria);

                Producto producto = new Producto(idProducto, nomProducto, precioProducto, stockProducto, idCategoria);
                productos.add(producto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return productos;
    }

    @Override
    public void AgregarProducto(Producto producto) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "INSERT INTO producto (nomProducto, precioProducto, stockProducto, idcategoria, idhabilitado) VALUES (?, ?, ?, ?, 1);";
                stmt = conn.prepareStatement(user);
                stmt.setString(1, producto.getNomProducto());
                stmt.setDouble(2, producto.getPrecioProducto());
                stmt.setInt(3, producto.getStockProducto());
                stmt.setInt(4, producto.getIdCategoria());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores si es necesario
            } finally {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void EliminarProducto(Producto producto) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "DELETE FROM producto WHERE idProducto = ?";
                stmt = conn.prepareStatement(user);
                stmt.setInt(1, producto.getIdProducto());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores si es necesario
            } finally {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void EditarProducto(Producto producto) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "UPDATE producto SET nomProducto = ?, precioProducto = ?, stockProducto = ?, idcategoria = ? WHERE (idProducto = ?);";
                stmt = conn.prepareStatement(user);
                stmt.setString(1, producto.getNomProducto());
                stmt.setDouble(2, producto.getPrecioProducto());
                stmt.setInt(3, producto.getStockProducto());
                stmt.setInt(4, producto.getIdCategoria());
                stmt.setInt(5, producto.getIdProducto());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores si es necesario
            } finally {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void PedirProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void SeleccionarProducto(Producto prdct) {

    }
    // Agrega este método a tu clase ProductoDAOImpl

    public String generarTablaHTML(List<Producto> productos) {
        StringBuilder tablaHTML = new StringBuilder();
        tablaHTML.append("<table border=\"1\">");
        tablaHTML.append("<thead>");
        tablaHTML.append("<tr>");
        tablaHTML.append("<th>ID</th>");
        tablaHTML.append("<th>Nombre</th>");
        tablaHTML.append("<th>Precio</th>");
        tablaHTML.append("<th>Stock</th>");
        tablaHTML.append("<th>ID Categoría</th>");
        tablaHTML.append("</tr>");
        tablaHTML.append("</thead>");
        tablaHTML.append("<tbody>");

        for (Producto producto : productos) {
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(producto.getIdProducto()).append("</td>");
            tablaHTML.append("<td>").append(producto.getNomProducto()).append("</td>");
            tablaHTML.append("<td>").append(producto.getPrecioProducto()).append("</td>");
            tablaHTML.append("<td>").append(producto.getStockProducto()).append("</td>");
            tablaHTML.append("<td>").append(producto.getIdCategoria()).append("</td>");
            tablaHTML.append("</tr>");
        }

        tablaHTML.append("</tbody>");
        tablaHTML.append("</table>");

        return tablaHTML.toString();
    }

    @Override
    public void InhabilitarProducto(Producto producto) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "UPDATE producto SET idhabilitado = 2 WHERE (idProducto = ?);";
                stmt = conn.prepareStatement(user);
                stmt.setInt(1, producto.getIdProducto());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores si es necesario
            } finally {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void HabilitarProducto(Producto producto) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "UPDATE producto SET idhabilitado = 1 WHERE (idProducto = ?);";
                stmt = conn.prepareStatement(user);
                stmt.setInt(1, producto.getIdProducto());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores si es necesario
            } finally {

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @Override
    public List<Producto> obtenerProductosPorCategoria(int categoriaId) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT idProducto, nomProducto, precioProducto, stockProducto, idCategoria FROM producto WHERE idHabilitado=1 AND idCategoria = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoriaId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nomProducto = rs.getString("nomProducto");
                double precioProducto = rs.getDouble("precioProducto");
                int stockProducto = rs.getInt("stockProducto");
                int idCategoria = rs.getInt("idCategoria");

                Producto producto = new Producto(idProducto, nomProducto, precioProducto, stockProducto, idCategoria);
                productos.add(producto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return productos;
    }

    @Override
    public List<Categoria> obtenerCategorias() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT idCategoria, nomCategoria FROM categoria";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCategoria = rs.getInt("idCategoria");
                String nombreCategoria = rs.getString("nomCategoria");
                Categoria categoria = new Categoria(idCategoria, nombreCategoria);
                categorias.add(categoria);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return categorias;
    }

    @Override
    public List<Producto> buscarProductosPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        List<Producto> productos = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();

        String sql = "SELECT idProducto, nomProducto, precioProducto, stockProducto, idCategoria FROM producto WHERE nomProducto LIKE ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("idProducto");
                    String nomProducto = rs.getString("nomProducto");
                    double precioProducto = rs.getDouble("precioProducto");
                    int stockProducto = rs.getInt("stockProducto");
                    int idCategoria = rs.getInt("idCategoria");
                    Producto producto = new Producto(idProducto, nomProducto, precioProducto, stockProducto, idCategoria);
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

}
