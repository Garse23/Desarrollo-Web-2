package modelo;

import java.sql.Date;
import java.sql.Time;

public class RespuestasForo {
    private int idRF;
    private String RespuestaForo;
    private Date FechaRF;
    private Time HoraRF;
    private int idUsuario;
    private int idForo;
    private int idHabilitado;

    public int getIdRF() {
        return idRF;
    }

    public void setIdRF(int idRF) {
        this.idRF = idRF;
    }

    public String getRespuestaForo() {
        return RespuestaForo;
    }

    public void setRespuestaForo(String RespuestaForo) {
        this.RespuestaForo = RespuestaForo;
    }

    public Date getFechaRF() {
        return FechaRF;
    }

    public void setFechaRF(Date FechaRF) {
        this.FechaRF = FechaRF;
    }

    public Time getHoraRF() {
        return HoraRF;
    }

    public void setHoraRF(Time HoraRF) {
        this.HoraRF = HoraRF;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdForo() {
        return idForo;
    }

    public void setIdForo(int idForo) {
        this.idForo = idForo;
    }

    public int getIdHabilitado() {
        return idHabilitado;
    }

    public void setIdHabilitado(int idHabilitado) {
        this.idHabilitado = idHabilitado;
    }
    
    
}

