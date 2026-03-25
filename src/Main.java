public class Main {
    public static void main(String[] args) {

        SistemaVentas sistema = new SistemaVentas();

        // Agregar vendedores
        Vendedor v1 = new Vendedor("V01", "Juan Pérez");
        Vendedor v2 = new Vendedor("V02", "María López");
        sistema.agregarVendedor(v1);
        sistema.agregarVendedor(v2);

        // Agregar productos
        Producto p1 = new Producto("P01", "Café", 5000);
        Producto p2 = new Producto("P02", "Azúcar", 3000);
        sistema.agregarProducto(p1);
        sistema.agregarProducto(p2);

        // Agregar ventas
        sistema.agregarVenta(new Venta(v1, p1, 10));
        sistema.agregarVenta(new Venta(v2, p2, 5));
        sistema.agregarVenta(new Venta(v1, p2, 3));

        // Mostrar todo
        sistema.mostrarDatos();
    }
}
