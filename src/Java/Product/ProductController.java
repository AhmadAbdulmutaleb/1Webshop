package Java.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Java.Category.Category;
import Java.Category.CategoryRepository;
import Java.Customer.Customer;
import Java.Customer.CustomerRepository;


public class ProductController {

    private CategoryRepository categoryRepository;
    private ProductService productService;
    // Service-lager för kundhantering, hanterar affärslogik
    ProductRepository productRepository;

    // Scanner för användarinput
    Scanner scanner;

    public ProductController() {
        // Skapa instanser av nödvändiga objekt
        this.categoryRepository = new CategoryRepository();
        this.productService = new ProductService();
        this.productRepository = new ProductRepository();
        this.scanner = new Scanner(System.in);

    }

    /**
     * Huvudloop för kundhantering
     * Visar meny och hanterar användarval
     */
    public void run() {
        while (true) {
            try {
                // Skriv ut menyalternativ direkt i run-metoden för tydlighet
                System.out.println("\n=== Produkthantering ===");
                System.out.println("1. Visa alla produkter");
                System.out.println("2. Visa en produkt");
                System.out.println("3. Lägg till en produkt");
                System.out.println("4. Filtrera produkter på kategori");
                System.out.println("5. Uppdaterar Produktinformantion/ saldo på lager");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");

                // Läser in valet av Produkthantering meny
                int select = scanner.nextInt();

                // Vi hanterar valet med switch
                switch (select) {
                    case 1:

                        productRepository.getAll();
                        break;
                    case 2:

                        System.out.println("Ange ID");
                        int id = scanner.nextInt();
                        productService.showProductsById(id);
                        break;
                    case 3:
                            // DEN SKA BLI LÄGG TILL PRODUKT IST FÖR KUND
                        System.out.println("Ange namn");
                        String name = scanner.next();
                        System.out.println("Ange email");
                        String email = scanner.next();
                        System.out.println("Ange telefon");
                        String phone = scanner.next();
                        System.out.println("Ange adress");
                        String address = scanner.next();
                        System.out.println("Ange lösenord");
                        String password = scanner.next();

                        //customerService.addCustomer(name, email, phone, address, password);
                        break;
                    case 4:
                        // Category val
                        filterProductsByCategory();
                        break;
                    case 5:
                        System.out.println("Du uppdaterar produktinformation");
                        System.out.println("\n=== Sök produkt ");
                        scanner.nextLine(); // Vet inte hur det funkar här men systemet funkar iallafall
                        System.out.print(" Ange produkt-ID: ");
                        String idInput2 = scanner.nextLine();

                        int productId;
                        try {
                            productId = Integer.parseInt(idInput2);
                        } catch (NumberFormatException e) {
                            System.out.println("Fel produkt ID, försök igen");
                            break;
                        }

                        Product product = productService.showProductById(productId);
                        if (product == null) {
                            System.out.println("Ingen produkt hittad med ID = " + productId);
                            break;
                        }
                        System.out.println("Produkt hittad! ID = " + productId);


                        System.out.print(" Nytt manufacturer-ID (" + product.getManufacturerId() + "): ");
                        String newManufacturerIdString = scanner.nextLine();
                        int newManufacturerId = product.getManufacturerId();
                        if (!newManufacturerIdString.isEmpty()) {
                            try {
                                newManufacturerId = Integer.parseInt(newManufacturerIdString);
                            } catch (NumberFormatException e) {
                                System.out.println("Ogiltigt manufacturer-ID, Vi behåller gammal manufacturer-ID! ");
                            }
                        }


                        System.out.print(" Nytt produktnamn (" + product.getName() + "): ");
                        String newProductName = scanner.nextLine();
                        if (newProductName.isEmpty()) {
                            newProductName = product.getName();
                        }


                        System.out.print(" Ny beskrivning (" + product.getDescription() + "): ");
                        String newDescription = scanner.nextLine();
                        if (newDescription.isEmpty()) {
                            newDescription = product.getDescription();
                        }


                        System.out.print(" Nytt pris (" + product.getPrice() + "): ");
                        String newPriceString = scanner.nextLine();
                        double newPrice = product.getPrice();
                        if (!newPriceString.isEmpty()) {
                            try {
                                newPrice = Double.parseDouble(newPriceString);
                            } catch (NumberFormatException e) {
                                System.out.println("Ogiltigt Price, Vi behåller gammal Price! ");
                            }
                        }


                        System.out.print(" Nytt lagersaldo (" + product.getStockQuantity() + "): ");
                        String newStockQuantityString = scanner.nextLine();
                        int newStockQuantity = product.getStockQuantity();
                        if (!newStockQuantityString.isEmpty()) {
                            try {
                                newStockQuantity = Integer.parseInt(newStockQuantityString);
                            } catch (NumberFormatException e) {
                                System.out.println("Ogiltigt lagersaldo, Vi behåller gammal lagersaldo");
                            }
                        }


                        boolean updated = productService.updateProduct(productId, newManufacturerId, newProductName, newDescription, newPrice, newStockQuantity);
                        if (updated) {
                            System.out.println("Produktinformation uppdaterad framgångsrikt!");
                        } else {
                            System.out.println("Kunde inte uppdatera produkten");
                        }
                        break;
                    case 0:
                        System.out.println("Avslutar kundhantering...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {

                System.out.println("Ett fel uppstod vid databasanrop: " + e.getMessage());
            } catch (Exception e) {

                System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    private void filterProductsByCategory() throws SQLException {
        System.out.println("\n===== FILTRERA PRODUKTER PÅ KATEGORI =====");

        scanner.nextLine();

        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            System.out.println("Inga kategorier finns tillgängliga.");
            return;
        }


        System.out.println("Tillgängliga kategorier:");
        for (Category category : categories) {
            System.out.println(category.getCategory_id() + ". " + category.getName());
        }


        System.out.print("\nVälj kategori-ID: ");
        try {
            int categoryId = Integer.parseInt(scanner.nextLine());


            List<Product> products = productService.getProductsByCategory(categoryId);

            if (products.isEmpty()) {
                System.out.println("Inga produkter hittades i vald category.");
                return;
            }


            System.out.println("\nProdukter i vald category:");
            for (Product product : products) {
                System.out.println(product);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt category ID. Ange ett Korrekt ID!! .");
        }
    }
}