package modelo;

public class Proveedor {
    private int id;
    private String Nombre;
    private String RazonSocial;
    private String Telefono;
    private String Ruc;
    private int Usuario;
    private int Rol;
    private int Habilitado;

    public Proveedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHabilitado() {
        return Habilitado;
    }

    public void setHabilitado(int Habilitado) {
        this.Habilitado = Habilitado;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String Ruc) {
        this.Ruc = Ruc;
    }

    public int getRol() {
        return Rol;
    }

    public void setRol(int Rol) {
        this.Rol = Rol;
    }

    public Proveedor(int id, String Nombre, String RazonSocial, String Telefono, String Ruc, int Usuario, int Rol, int Habilitado) {
        this.id = id;
        this.Nombre = Nombre;
        this.RazonSocial = RazonSocial;
        this.Telefono = Telefono;
        this.Ruc = Ruc;
        this.Usuario = Usuario;
        this.Rol = Rol;
        this.Habilitado = Habilitado;
    }


    
}
