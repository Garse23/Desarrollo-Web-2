package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Informacion;

public interface InformacionDAO {
    void AgregarInformacion(Informacion informacion);
    void CambiarInformacion(Informacion informacion);
    List<Informacion> obtenerInformacion(Informacion informacion) throws SQLException, ClassNotFoundException;
}
