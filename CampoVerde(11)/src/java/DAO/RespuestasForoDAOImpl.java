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
import modelo.RespuestasForo;

public class RespuestasForoDAOImpl implements RespuestasForoDAO{

    @Override
    public void AgregarRespuesta(RespuestasForo RF) {
    try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                RF.setFechaRF(Date.valueOf(fechaActual));
                RF.setHoraRF(Time.valueOf(horaActual));

                String msg = "INSERT INTO respuestaforo (respuesta, fechaRF, horaRF, idUsuario, idforo, idhabilitado) VALUES (?, ?, ?, ?, ?, 1);";
                stmt = conn.prepareStatement(msg);
                stmt.setString(1, RF.getRespuestaForo());
                stmt.setDate(2, RF.getFechaRF());
                stmt.setTime(3, RF.getHoraRF());
                stmt.setInt(4, RF.getIdUsuario());
                stmt.setInt(5, RF.getIdForo());
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
    public List<RespuestasForo> obtenerRespuestas(RespuestasForo RF) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RespuestasForo> RForo = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM respuestaforo where idforo=? and idhabilitado=? order by fechaRF, horaRF;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,RF.getIdForo());
            stmt.setInt(2,RF.getIdHabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int IdRF = rs.getInt("idrespuestaforo");
                String Respuesta = rs.getString("respuesta");
                Date Fecha = rs.getDate("fechaRF");
                Time Hora = rs.getTime("horaRF");
                int IdUsuario = rs.getInt("idUsuario");
                int IdForo = rs.getInt("idforo");
                int habilitado = rs.getInt("idhabilitado");

                RespuestasForo respuestas = new RespuestasForo();
                respuestas.setIdRF(IdRF);
                respuestas.setRespuestaForo(Respuesta);
                respuestas.setFechaRF(Fecha);
                respuestas.setHoraRF(Hora);
                respuestas.setIdUsuario(IdUsuario);
                respuestas.setIdForo(IdForo);
                respuestas.setIdHabilitado(habilitado);
                RForo.add(respuestas);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return RForo;
    }
    
}
