package DAO;

import controladores.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Informacion;

public class InformacionDAOImpl implements InformacionDAO{

    @Override
    public void AgregarInformacion(Informacion informacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void CambiarInformacion(Informacion informacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Informacion> obtenerInformacion(Informacion informacion) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Informacion> datos = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM informacion where nomInformacion LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,informacion.getNombreInformacion()+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int IdInformacion = rs.getInt("idinformacion");
                String Info = rs.getString("informacion");
                String nomInformacion = rs.getString("nomInformacion");
                
                Informacion inf = new Informacion();
                inf.setIdInformacion(IdInformacion);
                inf.setInformacion(Info);
                inf.setNombreInformacion(nomInformacion);
                datos.add(inf);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return datos;
    }
    
    
}
