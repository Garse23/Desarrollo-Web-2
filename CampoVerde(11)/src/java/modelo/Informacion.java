package modelo;

import java.sql.Date;
import java.sql.Time;

public class Informacion {
    int idInformacion;
    String Informacion;
    String nombreInformacion;
    int idRegistroInformacion;
    Date FechaRI;
    Time HoraRI;
    String CambioRI;
    int idUsuario;

    public int getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(int idInformacion) {
        this.idInformacion = idInformacion;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String Informacion) {
        this.Informacion = Informacion;
    }

    public String getNombreInformacion() {
        return nombreInformacion;
    }

    public void setNombreInformacion(String nombreInformacion) {
        this.nombreInformacion = nombreInformacion;
    }

    public int getIdRegistroInformacion() {
        return idRegistroInformacion;
    }

    public void setIdRegistroInformacion(int idRegistroInformacion) {
        this.idRegistroInformacion = idRegistroInformacion;
    }

    public Date getFechaRI() {
        return FechaRI;
    }

    public void setFechaRI(Date FechaRI) {
        this.FechaRI = FechaRI;
    }

    public Time getHoraRI() {
        return HoraRI;
    }

    public void setHoraRI(Time HoraRI) {
        this.HoraRI = HoraRI;
    }

    public String getCambioRI() {
        return CambioRI;
    }

    public void setCambioRI(String CambioRI) {
        this.CambioRI = CambioRI;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
