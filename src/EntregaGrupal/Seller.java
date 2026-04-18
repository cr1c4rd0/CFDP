package EntregaGrupal;

/**
 * Represents a seller registered in the sales system.
 *
 * <p>Each seller is identified by a document type (e.g. CC, CE, TI, PA)
 * and a unique document number.  The class is intentionally immutable:
 * all fields are set in the constructor and exposed only through getters.
 *
 * @author Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gomez
 * @version 3.0
 */
public class Seller {

    /** Colombian document type (e.g. "CC", "CE", "TI", "PA"). */
    private String documentType;

    /** Unique numeric identifier of the seller. */
    private long documentNumber;

    /** First name of the seller. */
    private String firstName;

    /** Last name of the seller. */
    private String lastName;

    /**
     * Creates a seller with their full identification data.
     *
     * @param documentType   the type of identity document (CC, CE, TI or PA)
     * @param documentNumber the numeric document number
     * @param firstName      the seller's first name
     * @param lastName       the seller's last name
     */
    public Seller(String documentType, long documentNumber,
                  String firstName, String lastName) {
        this.documentType   = documentType;
        this.documentNumber = documentNumber;
        this.firstName      = firstName;
        this.lastName       = lastName;
    }

    /**
     * Returns the document type of this seller.
     *
     * @return document type string (e.g. "CC")
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Returns the numeric document number of this seller.
     *
     * @return document number
     */
    public long getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Returns the first name of this seller.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of this seller.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name of this seller (first name + space + last name).
     *
     * @return full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
