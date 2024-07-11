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
import modelo.Foro;

public class ForoDAOImpl implements ForoDAO{

    @Override
    public void AgregarForo(Foro foro) {
        try {

            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                LocalDate fechaActual = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                foro.setFechaForo(Date.valueOf(fechaActual));
                foro.setHoraForo(Time.valueOf(horaActual));

                String msg = "INSERT INTO foro (nombreForo, anuncioForo, fechaForo, horaForo, idUsuario, idHabilitado) VALUES (?, ?, ?, ?, ?,1);";
                stmt = conn.prepareStatement(msg);
                stmt.setString(1, foro.getNombreForo());
                stmt.setString(2, foro.getAnuncioForo());
                stmt.setDate(3, foro.getFechaForo());
                stmt.setTime(4, foro.getHoraForo());
                stmt.setInt(5, foro.getIdUsuario());
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
    public List<Foro> obtenerForos(Foro foro) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Foro> foros = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM foro where idHabilitado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,foro.getIdHabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int IdForo = rs.getInt("idforo");
                String NombreForo = rs.getString("nombreForo");
                String AnuncioForo = rs.getString("anuncioForo");
                Date Fecha = rs.getDate("fechaForo");
                Time Hora = rs.getTime("horaForo");
                int IdUsuario = rs.getInt("idUsuario");
                int habilitado = rs.getInt("idHabilitado");

                Foro f = new Foro();
                f.setIdForo(IdForo);
                f.setNombreForo(NombreForo);
                f.setAnuncioForo(AnuncioForo);
                f.setFechaForo(Fecha);
                f.setHoraForo(Hora);
                f.setIdUsuario(IdUsuario);
                f.setIdHabilitado(habilitado);
                foros.add(f);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return foros;
    }
        
}
