package modelo;

import java.sql.Date;

public class Empleado {
    private int ID;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String DNI;
    private Date ContratoI;
    private Date ContratoF;
    private int Rol;
    private int Usuario;
    private int Habilitado;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getRol() {
        return Rol;
    }

    public void setRol(int Rol) {
        this.Rol = Rol;
    }

    public Date getContratoI() {
        return ContratoI;
    }

    public void setContratoI(Date ContratoI) {
        this.ContratoI = ContratoI;
    }

    public Date getContratoF() {
        return ContratoF;
    }

    public void setContratoF(Date ContratoF) {
        this.ContratoF = ContratoF;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getHabilitado() {
        return Habilitado;
    }

    public void setHabilitado(int Habilitado) {
        this.Habilitado = Habilitado;
    }
    
    
}
