package DAO;

import controladores.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DetalleVenta;

public class DetalleVentaDAOImpl implements DetalleVentaDAO{

    @Override
    public void insertarDetalleVenta(DetalleVenta detalleventa) {
        try {
            PreparedStatement stmt = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String insDVenta = "INSERT INTO detalleventa (cantidad, precioTotal, idProducto, idVenta) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(insDVenta);
                stmt.setInt(1, detalleventa.getCantidad());
                stmt.setDouble(2, detalleventa.getPrecioTotal());
                stmt.setInt(3, detalleventa.getIdProducto());
                stmt.setInt(4, detalleventa.getIdVenta());
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
    
}
