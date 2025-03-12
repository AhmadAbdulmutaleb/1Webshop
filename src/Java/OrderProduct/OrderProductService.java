package Java.OrderProduct;


import Java.Order.OrderRepository;
import Java.Product.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderProductService {


    private OrderProductRepository orderProductRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderProductService() {
        this.orderRepository = new OrderRepository();
        this.orderProductRepository = new OrderProductRepository();
        this.productRepository = new ProductRepository();
    }



    public void showAllOrderProducts(int orderId) throws SQLException {
        ArrayList<OrderProduct> orderProducts = orderProductRepository.getOrderProducts(orderId);

        if (orderProducts.isEmpty()) {
            System.out.println("Inga produkter hittades för order " + orderId);
            return;
        }

        double totalOrderValue = 0.0;

        System.out.println("\n=== Produkter i order " + orderId + " ===");
        for (OrderProduct item : orderProducts) {
            Product product = productRepository.getProductById(item.getProductId());
            String productName = (product != null) ? product.getName() : "Okänd produkt";

            System.out.println("Produkt: " + productName +
                    " (ID: " + item.getProductId() + ")" +
                    ", Antal: " + item.getQuantity() +
                    ", Pris/st: " + item.getUnitPrice() +
                    ", Totalt: " + item.getTotalaSumman());

            totalOrderValue += item.getTotalaSumman();
        }

        System.out.println("\n\nOrder totala värde : " + totalOrderValue);
    }


    public void addProductToOrder(int orderId, int productId, int quantity) throws SQLException {
// Kontrollera att ordern finns




        boolean orderExists = orderRepository.FindAll().stream()
                .anyMatch(order -> order.getOrderId() == orderId);

        if (!orderExists) {
            System.out.println("Java.Order med ID " + orderId + " finns inte.");
            return;
        }

        // Kontrollera att produkten finns och har tillräckligt i lager
        Product product = productRepository.getProductById(productId);

        if (product == null) {
            System.out.println("Produkt med ID " + productId + " finns inte.");
            return;
        }

        if (product.getStockQuantity() < quantity) {
            System.out.println("Otillräckligt antal i lager. Tillgängligt: " +
                    product.getStockQuantity() + ", Begärt: " + quantity);
            return;
        }

        // Lägg till produkt till ordern
        orderProductRepository.addProductToOrder(orderId, productId, quantity);

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.updateProductStock(productId, product.getStockQuantity());

        System.out.println("Produkten " + product.getName() + " har adderats i order nummer  " + orderId);
    }



}

