package DAO;

import java.sql.SQLException;
import java.util.List;
import modelo.Empleado;

public interface EmpleadoDAO {
    void registrarEmpleado(Empleado empleado);
    void actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(Empleado empleado);
    List<Empleado> obtenerEmpleados(Empleado empleado) throws SQLException, ClassNotFoundException;
}
