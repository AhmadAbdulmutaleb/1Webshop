package Java.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service-klass för kundhantering
 * Innehåller affärslogik mellan controller och repository
 */
public class ProductService {

    // Repository som hanterar alla databasanrop
    ProductRepository productRepository;

    /**
     * Konstruktor för Customer.CustomerService
     * Initierar repository-lagret
     */
    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    /**
     * Hämtar och visar alla kunder från databasen
     * Service-lagret kan här:
     * - Formatera utskriften
     * - Lägga till affärslogik (t.ex. filtrera bort inaktiva kunder)
     * - Hantera specialfall (t.ex. om listan är tom)
     *
     * @throws SQLException vid problem med databasanrop
     */
    public void showAllProducts() throws SQLException {
        // Hämta alla kunder från repository-lagret
        ArrayList<Product> products = productRepository.getAll();

        // Kontrollera om vi har några kunder att visa
        if (products.isEmpty()) {
            System.out.println("Inga Prudukter hittades.");
            return;
        }

        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Prudukt lista ===");
        for (Product product : products) {
            System.out.println("Pr.Customer{" +
                    "product_Id=" + product.getProductId() +
                    ", Manufacturer_Id='" + product.getManufacturerId() + '\'' +
                    ", name='" + product.getName() + '\'' +
                    ", description='" + product.getDescription() + '\'' +
                    ", price='" + product.getPrice() + '\'' +
                    ", stock_quantity='" + product.getStockQuantity() + '\'' +

                    '}');
        }
    }

    // I ProductService.java
    public List<Product> getProductsByCategory(Integer category_Id) throws SQLException {
        return productRepository.findByCategory(category_Id);
    }

    public Product showProductsById(int productId) throws SQLException {
        // Hämta alla kunder från repository-lagret
        Product product = productRepository.getProductById(productId);

        if (product!=null) {
            // Skriv ut alla kunder med tydlig formatering
            System.out.println(product.toString());
        }

        return product;
    }

    public boolean updateProduct(int productId, int manufacturerId, String name, String description, double price, int stockQuantity) throws SQLException {

        return productRepository.updateProduct(productId, manufacturerId, name, description, price, stockQuantity);

    }


    public Product showProductById(int productId) throws SQLException {
        return showProductsById(productId);
    }



    /*
    public void addProduct(String name, String email, String phone, String address, String password) throws SQLException {

        productRepository.addProduct(name, email, phone, address, password);

    }

    /**
     * Här kan man lägga till fler metoder som t.ex:
     * - getCustomerById
     * - addNewCustomer
     * - updateCustomer
     * - deleteCustomer
     * - findCustomerByEmail
     */
}