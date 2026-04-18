import EntregaGrupal.GenerateInfoFiles;
import EntregaGrupal.Product;
import EntregaGrupal.Sale;
import EntregaGrupal.Seller;
import EntregaGrupal.SalesSystem;

import java.io.*;
import java.util.*;

/**
 * Entry point for the CFDP Sales System project.
 *
 * <p>This class orchestrates two phases:
 * <ol>
 *   <li><b>Phase 1 – Data generation:</b> calls {@link GenerateInfoFiles#main(String[])}
 *       to produce {@code data/products.txt}, {@code data/sellers_info.txt} and one
 *       {@code data/seller_<id>.txt} file per seller.</li>
 *   <li><b>Phase 2 – Reporting:</b> reads the generated files, loads them into a
 *       {@link SalesSystem} instance, then writes:
 *       <ul>
 *         <li>{@code data/salesReport.csv}  – sellers ordered by total revenue (descending)</li>
 *         <li>{@code data/productsReport.csv} – products ordered by total quantity sold (descending)</li>
 *       </ul>
 *   </li>
 * </ol>
 *
 * <p>No user input is required during execution.
 *
 * @author Subgrupo 7: Santiago Fuentes Lopez, Cristian Mauricio Ricardo Rojas, Lina Garnica Gomez
 * @version 3.0
 */
public class Main {

    /** Folder that holds all flat files and output CSVs. */
    private static final String DATA_FOLDER = "data/";

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("=== CFDP Sales System — Phase 1: Generate files ===");
        GenerateInfoFiles.main(args);

        System.out.println("\n=== CFDP Sales System — Phase 2: Read files and generate reports ===");

        try {
            SalesSystem system = new SalesSystem();

            // ── 1. Load products ───────────────────────────────────────────
            Map<Integer, Product> productMap = loadProducts(system);

            // ── 2. Load sellers ────────────────────────────────────────────
            List<Seller> sellers = loadSellers(system);

            // ── 3. Load sales for each seller ──────────────────────────────
            loadSales(system, sellers, productMap);

            // ── 4. Display all data ────────────────────────────────────────
            system.listData();

            // ── 5. Generate reports ────────────────────────────────────────
            generateSalesReport(system);
            generateProductsReport(system, productMap);

            System.out.println("\nProcess finished with exit code 0");

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            System.exit(1);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File-loading helpers
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Reads {@code data/products.txt} and registers every product in the system.
     *
     * <p>Expected line format: {@code ProductID;ProductName;PricePerUnit}
     *
     * @param system the {@link SalesSystem} to populate
     * @return a map from product ID to {@link Product} for later look-ups
     * @throws IOException if the file cannot be read
     */
    private static Map<Integer, Product> loadProducts(SalesSystem system) throws IOException {
        Map<Integer, Product> map = new HashMap<>();
        String path = DATA_FOLDER + "products.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";");
                int    id    = Integer.parseInt(parts[0].trim());
                String name  = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                Product p = new Product(id, name, price);
                system.addProduct(p);
                map.put(id, p);
            }
        }
        System.out.println("  Products loaded: " + map.size());
        return map;
    }

    /**
     * Reads {@code data/sellers_info.txt} and registers every seller in the system.
     *
     * <p>Expected line format: {@code DocumentType;DocumentNumber;FirstName;LastName}
     *
     * @param system the {@link SalesSystem} to populate
     * @return the list of loaded {@link Seller} objects
     * @throws IOException if the file cannot be read
     */
    private static List<Seller> loadSellers(SalesSystem system) throws IOException {
        List<Seller> list = new ArrayList<>();
        String path = DATA_FOLDER + "sellers_info.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(";");
                String docType = parts[0].trim();
                long   docNum  = Long.parseLong(parts[1].trim());
                String fname   = parts[2].trim();
                String lname   = parts[3].trim();
                Seller s = new Seller(docType, docNum, fname, lname);
                system.addSeller(s);
                list.add(s);
            }
        }
        System.out.println("  Sellers loaded: " + list.size());
        return list;
    }

    /**
     * For each seller, reads their individual sales file
     * ({@code data/seller_<documentNumber>.txt}) and registers every sale in the system.
     *
     * <p>File format:
     * <pre>
     * Line 1: DocumentType;DocumentNumber
     * Lines 2+: ProductID;Quantity;
     * </pre>
     *
     * @param system     the {@link SalesSystem} to populate
     * @param sellers    the list of sellers already loaded into the system
     * @param productMap map of product IDs to {@link Product} instances
     * @throws IOException if any seller file cannot be read
     */
    private static void loadSales(SalesSystem system,
                                   List<Seller> sellers,
                                   Map<Integer, Product> productMap) throws IOException {
        int totalSales = 0;
        for (Seller seller : sellers) {
            String path = DATA_FOLDER + "seller_" + seller.getDocumentNumber() + ".txt";
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("  [WARN] No sales file found for " + seller.getFullName());
                continue;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine(); // skip header line (DocumentType;DocumentNumber)
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    // Remove trailing semicolons before splitting
                    if (line.endsWith(";")) line = line.substring(0, line.length() - 1);
                    String[] parts = line.split(";");
                    if (parts.length < 2) continue;
                    int productId = Integer.parseInt(parts[0].trim());
                    int quantity  = Integer.parseInt(parts[1].trim());
                    Product product = productMap.get(productId);
                    if (product == null) {
                        System.out.println("  [WARN] Product ID " + productId + " not found, skipping.");
                        continue;
                    }
                    system.addSale(new Sale(seller, product, quantity));
                    totalSales++;
                }
            }
        }
        System.out.println("  Sales loaded: " + totalSales);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Report generators
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Computes total revenue per seller and writes {@code data/salesReport.csv}.
     *
     * <p>Output format per line: {@code SellerName;TotalRevenue}
     * Rows are sorted by total revenue in descending order.
     *
     * @param system the populated {@link SalesSystem}
     * @throws IOException if the output file cannot be written
     */
    private static void generateSalesReport(SalesSystem system) throws IOException {
        Map<Long, Double> revenueMap = new LinkedHashMap<>();
        Map<Long, String> nameMap    = new LinkedHashMap<>();

        for (Seller s : system.getSellers()) {
            revenueMap.put(s.getDocumentNumber(), 0.0);
            nameMap.put(s.getDocumentNumber(), s.getFullName());
        }
        for (Sale sale : system.getSales()) {
            long id = sale.getSeller().getDocumentNumber();
            revenueMap.put(id, revenueMap.getOrDefault(id, 0.0) + sale.getTotal());
        }

        // Sort descending by total revenue
        List<Map.Entry<Long, Double>> sorted = new ArrayList<>(revenueMap.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        String path = DATA_FOLDER + "salesReport.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<Long, Double> entry : sorted) {
                String name  = nameMap.get(entry.getKey());
                double total = entry.getValue();
                bw.write(name + ";" + String.format("%.0f", total));
                bw.newLine();
            }
        }
        System.out.println("\n  salesReport.csv generated -> " + path);
        for (Map.Entry<Long, Double> e : sorted) {
            System.out.printf("    %-25s  $%,.0f%n", nameMap.get(e.getKey()), e.getValue());
        }
    }

    /**
     * Computes total quantity sold per product and writes {@code data/productsReport.csv}.
     *
     * <p>Output format per line: {@code ProductName;PricePerUnit}
     * Rows are sorted by total quantity sold in descending order.
     *
     * @param system     the populated {@link SalesSystem}
     * @param productMap map of product IDs to {@link Product} instances
     * @throws IOException if the output file cannot be written
     */
    private static void generateProductsReport(SalesSystem system,
                                                Map<Integer, Product> productMap)
            throws IOException {
        Map<Integer, Integer> qtyMap = new HashMap<>();
        for (Integer id : productMap.keySet()) qtyMap.put(id, 0);
        for (Sale sale : system.getSales()) {
            int id = sale.getProduct().getId();
            qtyMap.put(id, qtyMap.getOrDefault(id, 0) + sale.getQuantity());
        }

        // Sort descending by quantity sold
        List<Map.Entry<Integer, Integer>> sorted = new ArrayList<>(qtyMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        String path = DATA_FOLDER + "productsReport.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry<Integer, Integer> entry : sorted) {
                Product p = productMap.get(entry.getKey());
                bw.write(p.getName() + ";" + (int) p.getPricePerUnit());
                bw.newLine();
            }
        }
        System.out.println("\n  productsReport.csv generated -> " + path);
        for (Map.Entry<Integer, Integer> e : sorted) {
            Product p = productMap.get(e.getKey());
            System.out.printf("    %-25s  $%,d   [%d units sold]%n",
                    p.getName(), (int) p.getPricePerUnit(), e.getValue());
        }
    }
}
