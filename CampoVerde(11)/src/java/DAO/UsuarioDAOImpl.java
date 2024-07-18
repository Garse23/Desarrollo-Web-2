package DAO;
import javax.crypto.SecretKey;
import controladores.ConexionDB;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UsuarioDAOImpl implements UsuarioDAO {

    String LLAVE="Encriptado";
    
    @Override
    public void registrarUsuario(Usuario usuario) {

        try {
            PreparedStatement stmt = null;

            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String user = "INSERT INTO usuario (correoUsuario, passUsuario, idrol, idhabilitado) VALUES (?, ?, ?,?)";
                stmt = conn.prepareStatement(user);
                stmt.setString(1, usuario.getCorreo());
                stmt.setString(2, usuario.getContrasena());
                stmt.setInt(3, usuario.getRol());
                stmt.setInt(4, usuario.getHabilitado());
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
    public void actualizarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int seleccionarID(Usuario usuario) {
        int idusuario = 0;
        try {

            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String id = "SELECT idusuario FROM usuario WHERE correoUsuario=? and passUsuario=?";
                stmt = conn.prepareStatement(id);
                stmt.setString(1, usuario.getCorreo());
                stmt.setString(2, usuario.getContrasena());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    idusuario = rs.getInt("idusuario");
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
        return idusuario;
    }

    @Override
    public int seleccionarRol(Usuario usuario) {
        int idRol = 0;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String sql = "SELECT idrol FROM usuario WHERE idusuario=?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, usuario.getId());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    idRol = rs.getInt("idrol");
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
        return idRol;
    }

    @Override
    public String SeleccionarNombreUsuario(Usuario usuario) {
        String Nombre = "";
        int idRol = usuario.getRol();
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            String sql = null;
            try {
                switch (idRol) {
                    case 1:
                        sql = "SELECT nomEmpleado FROM empleado inner join usuario on usuario.idusuario=empleado.idusuario where usuario.idusuario=?";
                        break;
                    case 2:
                        sql = "SELECT nomProveedor FROM proveedor inner join usuario on usuario.idusuario=proveedor.idusuario where usuario.idusuario=?";
                        break;
                    case 3:
                        sql = "SELECT nomCliente FROM cliente inner join usuario on usuario.idusuario=cliente.idusuario where usuario.idusuario=?";
                        break;
                    case 4:
                        sql = "SELECT nomEmpleado FROM empleado inner join usuario on usuario.idusuario=empleado.idusuario where usuario.idusuario=?";
                }
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, usuario.getId());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    switch (idRol) {
                        case 1:
                            Nombre = rs.getString("nomEmpleado");
                        case 2:
                            Nombre = rs.getString("nomProveedor");
                        case 3:
                            Nombre = rs.getString("nomCliente");
                        case 4:
                            Nombre = rs.getString("nomEmpleado");
                    }
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
        return Nombre;
    }

    @Override
    public String SeleccionarNombreDestinatario(Usuario usuario) {
        String Nombre = "";
        int idRol = usuario.getRol();
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            String sql = null;
            try {
                switch (idRol) {
                    case 1:
                        sql = "SELECT nomEmpleado FROM empleado inner join usuario where idusuario=?";
                        break;
                    case 2:
                        sql = "SELECT nomProveedor FROM proveedor inner join usuario where idusuario=?";
                        break;
                    case 3:
                        sql = "SELECT nomCliente FROM cliente inner join usuario where idusuario=?";
                        break;
                    case 4:
                        sql = "SELECT nomEmpleado FROM empleado inner join usuario where idusuario=?";
                }
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, usuario.getId());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    switch (idRol) {
                        case 1:
                            Nombre = rs.getString("nomEmpleado");
                        case 2:
                            Nombre = rs.getString("nomProveedor");
                        case 3:
                            Nombre = rs.getString("nomCliente");
                        case 4:
                            Nombre = rs.getString("nomEmpleado");
                    }
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
        return Nombre;
    }
    

    @Override
    public int seleccionarHabilitado(Usuario usuario) {
        int habilitado=0;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ConexionDB conexion = ConexionDB.getInstancia();
            Connection conn = conexion.getConnection();
            try {
                String sql = "SELECT idhabilitado FROM usuario WHERE correoUsuario=? and passUsuario=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuario.getCorreo());
                stmt.setString(2, usuario.getContrasena());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    habilitado = rs.getInt("idhabilitado");
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
        return habilitado;
    }

    @Override
    public List<Usuario> obtenerUsuarios(Usuario usuario) throws SQLException, ClassNotFoundException {
    PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> Usuarios = new ArrayList<>();
        ConexionDB conexion = ConexionDB.getInstancia();
        Connection conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM usuario WHERE idhabilitado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getHabilitado());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("idusuario");
                String Correo = rs.getString("correoUsuario");
                String Contrasena = rs.getString("passUsuario");
                int rol = rs.getInt("idrol");
                int Habilitado = rs.getInt("idhabilitado");
                
                Usuario user = new Usuario() ;
                user.setId(idUsuario);
                user.setCorreo(Correo);
                user.setContrasena(Contrasena);
                user.setRol(rol);
                user.setHabilitado(Habilitado);
                
                Usuarios.add(user);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return Usuarios;
    }

    @Override
    public SecretKeySpec CrearClave(String llave) {
        try{
            byte [] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena,16);
            SecretKeySpec secretkeyspec = new SecretKeySpec(cadena, "AES");
            return secretkeyspec;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public String Encriptar(String encriptar) {
        try{
            SecretKeySpec secretkeyspec = CrearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretkeyspec);
            
            byte [] cadena = encriptar.getBytes("UTF-8");
            byte [] encriptada = cipher.doFinal(cadena);
            String cadena_encriptada = Base64.getEncoder().encodeToString(encriptada);
            return cadena_encriptada;
        }catch(Exception e){
            return "";
        }
    }
    
    
    

}
