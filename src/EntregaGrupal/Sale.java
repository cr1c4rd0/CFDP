package EntregaGrupal;

/**
 * Represents a single sales transaction in the system.
 *
 * <p>A sale associates one {@link Seller} with one {@link Product} and
 * records how many units were sold.  The total revenue for the transaction
 * is computed on demand via {@link #getTotal()}.
 *
 * @author Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gomez
 * @version 3.0
 */
public class Sale {

    /** The seller who made this sale. */
    private Seller seller;

    /** The product that was sold. */
    private Product product;

    /** Number of units sold in this transaction. */
    private int quantity;

    /**
     * Creates a sale record linking a seller, a product and the quantity sold.
     *
     * @param seller   the seller who performed the transaction
     * @param product  the product that was sold
     * @param quantity the number of units sold (must be &gt; 0)
     */
    public Sale(Seller seller, Product product, int quantity) {
        this.seller   = seller;
        this.product  = product;
        this.quantity = quantity;
    }

    /**
     * Returns the seller associated with this sale.
     *
     * @return the seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * Returns the product associated with this sale.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Returns the number of units sold in this transaction.
     *
     * @return quantity sold
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Computes the total revenue for this sale.
     *
     * <p>Calculated as: {@code quantity * product.pricePerUnit}
     *
     * @return total revenue in COP
     */
    public double getTotal() {
        return quantity * product.getPricePerUnit();
    }
}
