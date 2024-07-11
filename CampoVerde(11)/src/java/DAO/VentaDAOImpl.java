package DAO;

import controladores.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Venta;

public class VentaDAOImpl implements VentaDAO {

    @Override
    public void insertarVenta(Venta venta) {
        try {
            PreparedStatement stmt = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                venta.setFechaVenta(Date.valueOf(fechaActual));
                venta.setHoraVenta(Time.valueOf(horaActual));
            try {
                String insVenta = "INSERT INTO venta (fechaVenta, horaVenta, idUsuario) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(insVenta);
                stmt.setDate(1, venta.getFechaVenta());
                stmt.setTime(2, venta.getHoraVenta());
                stmt.setInt(3, venta.getIdUsuario());
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
    public int IdVenta(Venta venta) {
        int IdVenta=0;
        try {

            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String id = "SELECT idventa FROM venta WHERE fechaVenta=? and horaVenta=? and idUsuario=?";
                stmt = conn.prepareStatement(id);
                stmt.setDate(1, venta.getFechaVenta());
                stmt.setTime(2, venta.getHoraVenta());
                stmt.setInt(3, venta.getIdUsuario());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    IdVenta = rs.getInt("idventa");
                }
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
        return IdVenta;
        
    }
    
    

}
