package EntregaGrupal;

// Author: Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gómez
// This class represents a seller in the system

public class Seller {

    private String documentType;
    private long   documentNumber;
    private String firstName;
    private String lastName;

    // Constructor: creates a seller with their identification data
    public Seller(String documentType, long documentNumber, String firstName, String lastName) {
        this.documentType   = documentType;
        this.documentNumber = documentNumber;
        this.firstName      = firstName;
        this.lastName       = lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Returns the full name of the seller
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
