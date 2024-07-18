package DAO;

import java.sql.SQLException;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import modelo.Usuario;

public interface UsuarioDAO {
    void registrarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    int seleccionarID(Usuario usuario);
    int seleccionarRol(Usuario usuario);
    int seleccionarHabilitado(Usuario usuario);
    String SeleccionarNombreUsuario(Usuario usuario);
    String SeleccionarNombreDestinatario(Usuario usuario);
    List<Usuario> obtenerUsuarios(Usuario usuario) throws SQLException, ClassNotFoundException;
    public SecretKeySpec CrearClave(String llave);
    public String Encriptar(String encriptar);
}
