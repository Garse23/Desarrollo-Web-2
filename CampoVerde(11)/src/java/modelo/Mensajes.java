package modelo;

import java.sql.Date;
import java.sql.Time;

public class Mensajes {
    private int idMensaje;
    private String mensaje;
    private Date fecha;
    private Time hora;
    private int idUEnvio;
    private int idURecibido;
    private int Habilitado;

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getIdUEnvio() {
        return idUEnvio;
    }

    public void setIdUEnvio(int idUEnvio) {
        this.idUEnvio = idUEnvio;
    }

    public int getIdURecibido() {
        return idURecibido;
    }

    public void setIdURecibido(int idURecibido) {
        this.idURecibido = idURecibido;
    }

    public int getHabilitado() {
        return Habilitado;
    }

    public void setHabilitado(int Habilitado) {
        this.Habilitado = Habilitado;
    }
    
    
    
}
