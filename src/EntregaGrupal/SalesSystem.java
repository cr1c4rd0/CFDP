package EntregaGrupal;

/**
 * Central repository that holds all sellers, products and sales for the system.
 *
 * <p>Acts as an in-memory data store: objects are added via the {@code add*}
 * methods and retrieved via the {@code get*} methods.  The {@link #listData()}
 * method prints a human-readable summary of all stored data to standard output.
 *
 * @author Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gomez
 * @version 3.0
 */

import java.util.ArrayList;
import java.util.List;

public class SalesSystem {

    /** In-memory list of registered sellers. */
    private List<Seller> sellers;

    /** In-memory list of available products. */
    private List<Product> products;

    /** In-memory list of recorded sales transactions. */
    private List<Sale> sales;

    /**
     * Creates an empty sales system with no sellers, products or sales.
     */
    public SalesSystem() {
        sellers  = new ArrayList<>();
        products = new ArrayList<>();
        sales    = new ArrayList<>();
    }

    /**
     * Registers a seller in the system.
     *
     * @param seller the {@link Seller} to add; must not be {@code null}
     */
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    /**
     * Registers a product in the system.
     *
     * @param product the {@link Product} to add; must not be {@code null}
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Records a sales transaction in the system.
     *
     * @param sale the {@link Sale} to add; must not be {@code null}
     */
    public void addSale(Sale sale) {
        sales.add(sale);
    }

    /**
     * Returns the list of all registered sellers.
     *
     * @return unmodifiable view of sellers (caller should not mutate)
     */
    public List<Seller> getSellers() {
        return sellers;
    }

    /**
     * Returns the list of all registered products.
     *
     * @return list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the list of all recorded sales.
     *
     * @return list of sales
     */
    public List<Sale> getSales() {
        return sales;
    }

    /**
     * Prints a formatted summary of all sellers, products and sales to
     * {@link System#out}.
     *
     * <p>Output sections:
     * <pre>
     * === SELLERS ===
     * DocumentType;DocumentNumber - FullName
     * ...
     *
     * === PRODUCTS ===
     * ProductID - ProductName  $PricePerUnit
     * ...
     *
     * === SALES ===
     * SellerName sold N units of ProductName -> Total: $Amount
     * ...
     * </pre>
     */
    public void listData() {
        System.out.println("=== SELLERS ===");
        for (Seller s : sellers) {
            System.out.println(s.getDocumentType() + ";" + s.getDocumentNumber()
                    + " - " + s.getFullName());
        }

        System.out.println("\n=== PRODUCTS ===");
        for (Product p : products) {
            System.out.println(p.getId() + " - " + p.getName() + "  $" + (int) p.getPricePerUnit());
        }

        System.out.println("\n=== SALES ===");
        for (Sale s : sales) {
            System.out.println(s.getSeller().getFullName()
                    + " sold " + s.getQuantity()
                    + " units of " + s.getProduct().getName()
                    + " -> Total: $" + String.format("%.0f", s.getTotal()));
        }
    }
}
