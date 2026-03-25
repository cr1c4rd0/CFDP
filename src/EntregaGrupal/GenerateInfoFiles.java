package EntregaGrupal;

// Author: Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gómez
// This class generates flat files that will be used as input for the sales system

import java.io.*;
import java.util.Random;

public class GenerateInfoFiles {

    // Folder where the files will be saved
    private static final String DATA_FOLDER = "data/";

    // Valid document types
    private static final String[] DOCUMENT_TYPES = {"CC", "CE", "TI", "PA"};

    // Lists of names and last names to generate random sellers
    private static final String[] FIRST_NAMES = {
        "Juan", "Maria", "Carlos", "Ana", "Luis", "Sofia", "Andres", "Laura",
        "Miguel", "Valentina", "Jorge", "Camila", "David", "Isabella", "Daniel",
        "Sara", "Alejandro", "Natalia", "Ricardo", "Paula"
    };

    private static final String[] LAST_NAMES = {
        "Garcia", "Martinez", "Rodriguez", "Lopez", "Hernandez", "Gonzalez",
        "Perez", "Sanchez", "Ramirez", "Torres", "Flores", "Rivera", "Gomez",
        "Diaz", "Cruz", "Morales", "Reyes", "Vargas", "Castillo", "Ortiz"
    };

    // List of products to generate random products
    private static final String[] PRODUCT_NAMES = {
        "Cafe Tostado", "Azucar Blanca", "Arroz Diana", "Leche Entera",
        "Pan Tajado", "Aceite Girasol", "Sal Refinada", "Harina de Trigo",
        "Pasta Espagueti", "Atun en Lata", "Jabon de Bano", "Shampoo",
        "Detergente", "Papas Fritas", "Cebolla Cabezona", "Tomate Chonto",
        "Zanahoria", "Pechuga de Pollo", "Carne Molida", "Huevos"
    };

    // Number of products and sellers to generate
    private static final int NUM_PRODUCTS = 10;
    private static final int NUM_SELLERS  = 5;

    // Price range for products (in Colombian pesos)
    private static final int MIN_PRICE = 500;
    private static final int MAX_PRICE = 50000;

    // Quantity range per sale
    private static final int MIN_QUANTITY    = 1;
    private static final int MAX_QUANTITY    = 100;
    private static final int MAX_SALES_COUNT = 8;

    // Main method - generates all files
    public static void main(String[] args) {
        try {
            // Create the data folder if it does not exist
            File dataDir = new File(DATA_FOLDER);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            // Step 1: generate the products file
            createProductsFile(NUM_PRODUCTS);

            // Step 2: create random names and IDs for the sellers
            Random random = new Random();
            String[] names = new String[NUM_SELLERS];
            long[]   ids   = new long[NUM_SELLERS];

            for (int i = 0; i < NUM_SELLERS; i++) {
                names[i] = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]
                         + " "
                         + LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                ids[i] = 1000000000L + (long) random.nextInt(900000000);
            }

            // Step 3: generate the sellers info file
            createSellersInfoFile(NUM_SELLERS, names, ids);

            // Step 4: generate one sales file per seller
            for (int i = 0; i < NUM_SELLERS; i++) {
                int salesCount = random.nextInt(MAX_SALES_COUNT) + 1;
                createSellerSalesFile(salesCount, names[i], ids[i]);
            }

            System.out.println("All files generated successfully in: " + DATA_FOLDER);

        } catch (IOException e) {
            System.err.println("Error generating files: " + e.getMessage());
        }
    }

    // Creates the products file
    // Format of each line: ProductID;ProductName;PricePerUnit
    public static void createProductsFile(int numProducts) throws IOException {

        Random random   = new Random();
        String fileName = DATA_FOLDER + "products.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int i = 1; i <= numProducts; i++) {
            String name  = PRODUCT_NAMES[(i - 1) % PRODUCT_NAMES.length];
            int    price = random.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
            writer.write(i + ";" + name + ";" + price);
            writer.newLine();
        }

        writer.close();
        System.out.println("  Products file created: " + fileName);
    }

    // Creates the sellers info file using the given names and IDs
    // Format of each line: DocumentType;DocumentNumber;FirstName;LastName
    public static void createSellersInfoFile(int numSellers, String[] names, long[] ids)
            throws IOException {

        Random random   = new Random();
        String fileName = DATA_FOLDER + "sellers_info.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < numSellers; i++) {
            String docType = DOCUMENT_TYPES[random.nextInt(DOCUMENT_TYPES.length)];

            // Split full name into first name and last name
            String[] parts    = names[i].split(" ", 2);
            String firstName  = parts[0];
            String lastName   = parts[1];

            writer.write(docType + ";" + ids[i] + ";" + firstName + ";" + lastName);
            writer.newLine();
        }

        writer.close();
        System.out.println("  Sellers info file created: " + fileName);
    }

    // Creates the sales file for one seller
    // First line: DocumentType;DocumentNumber
    // Following lines: ProductID;Quantity;
    public static void createSellerSalesFile(int numSales, String name, long id)
            throws IOException {

        Random random   = new Random();
        String fileName = DATA_FOLDER + "seller_" + id + ".txt";
        String docType  = DOCUMENT_TYPES[random.nextInt(DOCUMENT_TYPES.length)];

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // Write seller identification on the first line
        writer.write(docType + ";" + id);
        writer.newLine();

        // Write each sale record
        for (int i = 0; i < numSales; i++) {
            int productId = random.nextInt(NUM_PRODUCTS) + 1;
            int quantity  = random.nextInt(MAX_QUANTITY - MIN_QUANTITY + 1) + MIN_QUANTITY;
            writer.write(productId + ";" + quantity + ";");
            writer.newLine();
        }

        writer.close();
        System.out.println("  Sales file created for " + name + " -> " + fileName);
    }
}
