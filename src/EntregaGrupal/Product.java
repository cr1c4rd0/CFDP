package EntregaGrupal;

// Author: Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gómez
// This class represents a product in the system

public class Product {

    private int    id;
    private String name;
    private double pricePerUnit;

    // Constructor: creates a product with its id, name and price
    public Product(int id, String name, double pricePerUnit) {
        this.id           = id;
        this.name         = name;
        this.pricePerUnit = pricePerUnit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }
}
