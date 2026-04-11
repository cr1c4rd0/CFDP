package EntregaGrupal;

// Author: Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gómez
// This class manages the lists of sellers, products and sales

import java.util.ArrayList;
import java.util.List;

public class SalesSystem {

    private List<Seller>  sellers;
    private List<Product> products;
    private List<Sale>    sales;

    // Constructor: creates an empty sales system
    public SalesSystem() {
        sellers  = new ArrayList<>();
        products = new ArrayList<>();
        sales    = new ArrayList<>();
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Sale> getSales() {
        return sales;
    }

    // Prints all sellers, products and sales to the console
    public void listData() {
        System.out.println("=== SELLERS ===");
        for (Seller s : sellers) {
            System.out.println(s.getDocumentType() + ";" + s.getDocumentNumber()
                    + " - " + s.getFullName());
        }

        System.out.println("\n=== PRODUCTS ===");
        for (Product p : products) {
            System.out.println(p.getId() + " - " + p.getName() + " $" + p.getPricePerUnit());
        }

        System.out.println("\n=== SALES ===");
        for (Sale s : sales) {
            System.out.println(s.getSeller().getFullName()
                    + " sold " + s.getQuantity()
                    + " units of " + s.getProduct().getName()
                    + " -> Total: $" + s.getTotal());
        }
    }
}
