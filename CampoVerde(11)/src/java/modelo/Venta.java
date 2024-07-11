package modelo;

import java.sql.Date;
import java.sql.Time;

public class Venta {
    int idventa;
    Date fechaVenta;
    Time horaVenta;
    int idUsuario;

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Time getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(Time horaVenta) {
        this.horaVenta = horaVenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
