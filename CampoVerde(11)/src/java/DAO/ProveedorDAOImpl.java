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
import modelo.Proveedor;

public class ProveedorDAOImpl implements ProveedorDAO {

    @Override
    public void registrarProveedor(Proveedor proveedor) {
        try {

            PreparedStatement stmt = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String inproveedor = "INSERT INTO proveedor (nomProveedor, razonsocialProveedor, telProveedor, rucProveedor, idrol, idusuario, idhabilitado) VALUES (?, ?, ?, ?, 2, ?, 2);";
                stmt = conn.prepareStatement(inproveedor);
                stmt.setString(1, proveedor.getNombre());
                stmt.setString(2, proveedor.getRazonSocial());
                stmt.setString(3, proveedor.getTelefono());
                stmt.setString(4, proveedor.getRuc());
                stmt.setInt(5, proveedor.getUsuario());
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
    public void actualizarProveedor(Proveedor proveedor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProveedor(Proveedor proveedor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Proveedor> obtenerProveedoresHabilitados() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Proveedor> proveedores = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM proveedor WHERE idHabilitado=1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProveedor = rs.getInt("idproveedor");
                String nombre = rs.getString("nomProveedor");
                String razonSocial = rs.getString("razonsocialProveedor");
                String telefono = rs.getString("telProveedor");
                String ruc = rs.getString("rucProveedor");
                int idUsuario = rs.getInt("idusuario");
                int idRol = rs.getInt("idrol");
                int idhabilitado = rs.getInt("idhabilitado");

                
                Proveedor proveedor = new Proveedor(idProveedor, nombre, razonSocial, telefono, ruc, idUsuario, idRol, idhabilitado);
                proveedores.add(proveedor);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return proveedores;
    }

    @Override
    public List<Proveedor> obtenerProveedoresDeshabilitados() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
