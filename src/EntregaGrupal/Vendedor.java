public class Vendedor {
    private String id;
    private String nombre;

    public Vendedor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String un vendedor con un producto vendido:

```java
public class Venta {
    private Vendedor vendedor;
    private Producto producto;
    private int cantidad;

    public Venta(Vendedor vendedor, Producto producto, int cantidad) {
        this.vendedor = vendedor;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Vendedor getVendedor() { return vendedor; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}
``
