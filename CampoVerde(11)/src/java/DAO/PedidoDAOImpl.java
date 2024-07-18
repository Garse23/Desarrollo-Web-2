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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pedido;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public void realizarPedido(Pedido pedido) {
        try {

            PreparedStatement stmt = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                pedido.setFecha(Date.valueOf(fechaActual));
                pedido.setHora(Time.valueOf(horaActual));
                String inproveedor = "INSERT INTO pedido (idproducto, idusuario, idestadopedido, fechapedido, horapedido, idhabilitado) VALUES (?, ?, ?, ?, ?, 1)";
                stmt = conn.prepareStatement(inproveedor);
                stmt.setInt(1, pedido.getIdproducto());
                stmt.setInt(2, pedido.getIdusuario());
                stmt.setInt(3, pedido.getIdestado());
                stmt.setDate(4, pedido.getFecha());
                stmt.setTime(5, pedido.getHora());
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
    public void verificarEstadoPedido(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEstadoPedido(Pedido pedido) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            int estado=pedido.getIdestado();
            try {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();
                
                switch (estado) {
                    case 1:
                        String e = "UPDATE pedido SET idestadopedido = ?, fechaEnvioPedido = ?, horaEnvioPedido = ? WHERE (idpedido = ?);";
                        pedido.setfEnvio(Date.valueOf(fechaActual));
                        pedido.sethEnvio(Time.valueOf(horaActual));
                        stmt = conn.prepareStatement(e);
                        stmt.setInt(1, 2);
                        stmt.setDate(2, pedido.getfEnvio());
                        stmt.setTime(3, pedido.gethEnvio());
                        stmt.setInt(4, pedido.getId());
                        stmt.executeUpdate();
                        break;
                    case 2:
                        String r = "UPDATE pedido SET idestadopedido = ?, fechaRecibidoPedido = ?, horaRecibidoPedido = ? WHERE (idpedido = ?);";
                        pedido.setfRecibido(Date.valueOf(fechaActual));
                        pedido.sethRecibido(Time.valueOf(horaActual));
                        stmt = conn.prepareStatement(r);
                        stmt.setInt(1, 3);
                        stmt.setDate(2, pedido.getfRecibido());
                        stmt.setTime(3, pedido.gethRecibido());
                        stmt.setInt(4, pedido.getId());
                        stmt.executeUpdate();
                        break;
                    default:
                        throw new AssertionError();
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
    }

    @Override
    public List<Pedido> obtenerPedidosHabilitados() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> Pedidos = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM pedido inner join estadopedido on pedido.idestadopedido=estadoPedido.idestadopedido inner join producto on pedido.idproducto=producto.idproducto inner join proveedor on pedido.idusuario=proveedor.idusuario where pedido.idhabilitado=1;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idpedido = rs.getInt("idpedido");
                int idproducto = rs.getInt("idproducto");
                String producto = rs.getString("nomProducto");
                int idusuario = rs.getInt("idusuario");
                String usuario = rs.getString("nomProveedor");
                String Rsocial = rs.getString("razonsocialProveedor");
                int idestadopedido = rs.getInt("idestadopedido");
                String estadopedido = rs.getString("estadoPedido");
                int idhabilitado = rs.getInt("pedido.idhabilitado");
                Date fpedido = rs.getDate("fechapedido");
                Time hpedido = rs.getTime("horapedido");
                Date fEnvio = rs.getDate("fechaEnvioPedido");
                Time hEnvio = rs.getTime("horaEnvioPedido");
                Date fRecibido = rs.getDate("fechaRecibidoPedido");
                Time hRecibido = rs.getTime("horaRecibidoPedido");

                Pedido pedido = new Pedido(idpedido, idproducto, producto, idusuario, usuario, Rsocial, idestadopedido, estadopedido, idhabilitado, fpedido, hpedido, fEnvio, hEnvio, fRecibido, hRecibido);
                Pedidos.add(pedido);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return Pedidos;
    }

}
