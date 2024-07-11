package modelo;

public class Producto {
    private int idProducto;
    private String nomProducto;
    private double precioProducto;
    private int stockProducto;
    private int idCategoria;
    private int idhabilitado;
    
    // Constructor, getters y setters
    
    // Constructor
    public Producto(int idProducto, String nomProducto, double precioProducto, int stockProducto, int idCategoria) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.idCategoria = idCategoria;
    }

    public Producto(int id, String nombre, double precio, int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Producto() {
    }
    
    // Getters y setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdhabilitado() {
        return idhabilitado;
    }

    public void setIdhabilitado(int idhabilitado) {
        this.idhabilitado = idhabilitado;
    }
}
