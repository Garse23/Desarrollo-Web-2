package modelo;

public class Carrito {
    private int Producto;
    private int cantidad;

    public int getProducto() {
        return Producto;
    }

    public void setProducto(int Producto) {
        this.Producto = Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito(int Producto, int cantidad) {
        this.Producto = Producto;
        this.cantidad = cantidad;
    }
    
    
    
}
