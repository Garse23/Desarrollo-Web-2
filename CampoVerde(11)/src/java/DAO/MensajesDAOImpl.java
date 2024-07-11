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
import modelo.Mensajes;

public class MensajesDAOImpl implements MensajesDAO {

    @Override
    public void EnviarMensaje(Mensajes mensajes) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                mensajes.setFecha(Date.valueOf(fechaActual));
                mensajes.setHora(Time.valueOf(horaActual));

                String msg = "INSERT INTO mensajes (mensaje, fechaMensaje, horaMensaje, idusuario1, idusuario2) VALUES (?, ?, ?, ?, ?);";
                stmt = conn.prepareStatement(msg);
                stmt.setString(1, mensajes.getMensaje());
                stmt.setDate(2, mensajes.getFecha());
                stmt.setTime(3, mensajes.getHora());
                stmt.setInt(4, mensajes.getIdUEnvio());
                stmt.setInt(5, mensajes.getIdURecibido());
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
    public List<Mensajes> obtenerMensajesDisponibles(Mensajes mensaje) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mensajes> mensajes = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM mensajes where (idusuario1=? and idusuario2=? or idusuario1=? and idusuario2=?) and idhabilitado=1 order by fechaMensaje, horaMensaje";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,mensaje.getIdUEnvio());
            stmt.setInt(2,mensaje.getIdURecibido());
            stmt.setInt(3,mensaje.getIdURecibido());
            stmt.setInt(4,mensaje.getIdUEnvio());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int IdMensaje = rs.getInt("idmensajes");
                String msg = rs.getString("mensaje");
                Date Fecha = rs.getDate("fechaMensaje");
                Time Hora = rs.getTime("horaMensaje");
                int IdUEnvio = rs.getInt("idusuario1");
                int IdURecibido = rs.getInt("idusuario2");
                int habilitado = rs.getInt("idhabilitado");

                Mensajes ms = new Mensajes();
                ms.setIdMensaje(IdMensaje);
                ms.setMensaje(msg);
                ms.setFecha(Fecha);
                ms.setHora(Hora);
                ms.setIdUEnvio(IdUEnvio);
                ms.setIdURecibido(IdURecibido);
                ms.setHabilitado(habilitado);
                mensajes.add(ms);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return mensajes;
    }

}
