package DAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Mensajes;

public interface MensajesDAO {
    
    void EnviarMensaje(Mensajes mensajes);
    List<Mensajes> obtenerMensajesDisponibles(Mensajes mensajes) throws SQLException, ClassNotFoundException;
}
