package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.RespuestasForo;

public interface RespuestasForoDAO {
    void AgregarRespuesta(RespuestasForo RF);
    List<RespuestasForo> obtenerRespuestas(RespuestasForo RF) throws SQLException, ClassNotFoundException;
}
