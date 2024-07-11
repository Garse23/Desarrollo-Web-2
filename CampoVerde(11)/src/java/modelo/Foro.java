package modelo;

import java.sql.Date;
import java.sql.Time;

public class Foro {
    private int idForo;
    private String NombreForo;
    private String AnuncioForo;
    private Date fechaForo;
    private Time horaForo;
    private int idUsuario;
    private int idHabilitado;

    public int getIdForo() {
        return idForo;
    }

    public void setIdForo(int idForo) {
        this.idForo = idForo;
    }

    public String getNombreForo() {
        return NombreForo;
    }

    public void setNombreForo(String NombreForo) {
        this.NombreForo = NombreForo;
    }

    public String getAnuncioForo() {
        return AnuncioForo;
    }

    public void setAnuncioForo(String AnuncioForo) {
        this.AnuncioForo = AnuncioForo;
    }

    public Date getFechaForo() {
        return fechaForo;
    }

    public void setFechaForo(Date fechaForo) {
        this.fechaForo = fechaForo;
    }

    public Time getHoraForo() {
        return horaForo;
    }

    public void setHoraForo(Time horaForo) {
        this.horaForo = horaForo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHabilitado() {
        return idHabilitado;
    }

    public void setIdHabilitado(int idHabilitado) {
        this.idHabilitado = idHabilitado;
    }
    
    
}
