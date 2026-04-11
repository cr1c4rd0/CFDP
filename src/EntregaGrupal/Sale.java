package EntregaGrupal;

// Author: Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gómez
// This class represents a sale record in the system

public class Sale {

    private Seller  seller;
    private Product product;
    private int     quantity;

    // Constructor: creates a sale linking a seller, a product and the quantity sold
    public Sale(Seller seller, Product product, int quantity) {
        this.seller   = seller;
        this.product  = product;
        this.quantity = quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Returns the total revenue for this sale
    public double getTotal() {
        return quantity * product.getPricePerUnit();
    }
}
