package DAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Foro;
public interface ForoDAO {
    void AgregarForo(Foro foro);
    List<Foro> obtenerForos(Foro foro) throws SQLException, ClassNotFoundException;
}
