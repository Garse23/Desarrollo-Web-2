package DAO;

import controladores.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAOImpl implements EmpleadoDAO{

    @Override
    public void registrarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Empleado> obtenerEmpleados(Empleado empleado) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Empleado> Empleados = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM empleado WHERE idhabilitado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empleado.getHabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idEmpleado = rs.getInt("idempleado");
                String nombre = rs.getString("nomEmpleado");
                String Apellido = rs.getString("apellEmpleado");
                String telefono = rs.getString("telEmpleado");
                String dni = rs.getString("dniEmpleado");
                Date ContratoI = rs.getDate("inicioContrato");
                Date ContratoF = rs.getDate("finalContrato");
                int rol = rs.getInt("idrol");
                int usuario = rs.getInt("idusuario");
                int Habilitado = rs.getInt("idhabilitado");
                
                Empleado empl = new Empleado() ;
                empl.setID(idEmpleado);
                empl.setNombre(nombre);
                empl.setApellido(Apellido);
                empl.setTelefono(telefono);
                empl.setDNI(dni);
                empl.setContratoI(ContratoI);
                empl.setContratoF(ContratoF);
                empl.setRol(rol);
                empl.setUsuario(usuario);
                empl.setHabilitado(Habilitado);

                Empleados.add(empl);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return Empleados;
    }
    
}
