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
import modelo.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void registrarCliente(Cliente cliente) {
        try {

            PreparedStatement stmt = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String inscliente = "INSERT INTO cliente (nomCliente, telCliente, dniCliente, idrol, idUsuario) VALUES (?, ?, ?, 3, ?)";
                stmt = conn.prepareStatement(inscliente);
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getTelefono());
                stmt.setString(3, cliente.getDNI());
                stmt.setInt(4, cliente.getUsuario());
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
    public void actualizarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> obtenerCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> Clientes = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM cliente WHERE idhabilitado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getHabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idcliente");
                String nombre = rs.getString("nomCliente");
                String telefono = rs.getString("telCliente");
                String dni = rs.getString("dniCliente");
                int rol = rs.getInt("idrol");
                int usuario = rs.getInt("idusuario");
                int Habilitado = rs.getInt("idhabilitado");
                
                Cliente cl = new Cliente() ;
                cl.setID(idCliente);
                cl.setNombre(nombre);
                cl.setTelefono(telefono);
                cl.setDNI(dni);
                cl.setRol(rol);
                cl.setUsuario(usuario);
                cl.setHabilitado(Habilitado);
                Clientes.add(cl);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return Clientes;
    }
    
}
