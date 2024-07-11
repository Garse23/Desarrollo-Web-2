package modelo;

import java.sql.Date;
import java.sql.Time;

public class Pedido {
    private int id;
    private int idproducto;
    private String producto;
    private int idusuario;
    private String usuario;
    private String Rsocial;
    private int idestado;
    private String estado;
    private int habilitado;
    private Date fecha;
    private Time hora;
    private Date fEnvio;
    private Time hEnvio;
    private Date fRecibido;
    private Time hRecibido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
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

    public Date getfEnvio() {
        return fEnvio;
    }

    public void setfEnvio(Date fEnvio) {
        this.fEnvio = fEnvio;
    }

    public Time gethEnvio() {
        return hEnvio;
    }

    public void sethEnvio(Time hEnvio) {
        this.hEnvio = hEnvio;
    }

    public Date getfRecibido() {
        return fRecibido;
    }

    public void setfRecibido(Date fRecibido) {
        this.fRecibido = fRecibido;
    }

    public Time gethRecibido() {
        return hRecibido;
    }

    public void sethRecibido(Time hRecibido) {
        this.hRecibido = hRecibido;
    }

    public String getRsocial() {
        return Rsocial;
    }

    public void setRsocial(String Rsocial) {
        this.Rsocial = Rsocial;
    }
    
    

    public Pedido() {
    }

    public Pedido(int id, int idproducto, String producto, int idusuario, String usuario, String Rsocial, int idestado, String estado, int habilitado, Date fecha, Time hora, Date fEnvio, Time hEnvio, Date fRecibido, Time hRecibido) {
        this.id = id;
        this.idproducto = idproducto;
        this.producto = producto;
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.Rsocial = Rsocial;
        this.idestado = idestado;
        this.estado = estado;
        this.habilitado = habilitado;
        this.fecha = fecha;
        this.hora = hora;
        this.fEnvio = fEnvio;
        this.hEnvio = hEnvio;
        this.fRecibido = fRecibido;
        this.hRecibido = hRecibido;
    }




    
}
