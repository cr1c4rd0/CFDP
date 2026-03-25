import java.util.ArrayList;
import java.util.List;

public class SistemaVentas {
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    public void agregarVendedor(Vendedor v) {
        vendedores.add(v);
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void agregarVenta(Venta v) {
        ventas.add(v);
    }

    public void listarDatos() {
        System.out.println("=== VENDEDORES ===");
        vendedores.forEach(v -> System.out.println(v.getId() + " - " + v.getNombre()));

        System.out.println("\n=== PRODUCTOS ===");
        productos.forEach(p -> System.out.println(p.getCodigo() + " - " + p.getNombre() + " $" + p.getPrecio()));

        System.out.println("\n=== VENTAS ===");
        ventas.forEach(v -> {
            System.out.println(v.getVendedor().getNombre() + " vendió " +
                               v.getCantidad() + " unidades de " + 
                               v.getProducto().getNombre());
        });
    }
}
