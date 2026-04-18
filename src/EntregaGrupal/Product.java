package EntregaGrupal;

/**
 * Represents a product available for sale in the system.
 *
 * <p>Products are loaded once from {@code data/products.txt} and are
 * referenced by {@link Sale} objects.  The price is stored as a
 * {@code double} to allow fractional Colombian-peso values if needed.
 *
 * @author Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gomez
 * @version 3.0
 */
public class Product {

    /** Unique numeric identifier of the product. */
    private int id;

    /** Human-readable product name. */
    private String name;

    /** Price per unit in Colombian pesos. */
    private double pricePerUnit;

    /**
     * Creates a product with its identifier, name and unit price.
     *
     * @param id           the unique product ID
     * @param name         the name of the product
     * @param pricePerUnit the price charged per unit (COP)
     */
    public Product(int id, String name, double pricePerUnit) {
        this.id           = id;
        this.name         = name;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Returns the unique identifier of this product.
     *
     * @return product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of this product.
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price per unit of this product.
     *
     * @return price per unit in COP
     */
    public double getPricePerUnit() {
        return pricePerUnit;
    }
}
