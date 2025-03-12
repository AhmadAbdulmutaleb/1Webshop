package Java.Order;

import Java.Customer.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service-klass för kundhantering
 * Innehåller affärslogik mellan controller och repository
 */
public class OrderService {

    // Repository som hanterar alla databasanrop
    OrderRepository orderRepository;

    /**
     * Konstruktor för Customer.CustomerService
     * Initierar repository-lagret
     */
    public OrderService() {
        this.orderRepository = new OrderRepository();
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
    public void showAllOrders() throws SQLException {
        // Hämta alla kunder från repository-lagret
        ArrayList<Order> orders = orderRepository.FindAll();

        // Kontrollera om vi har några kunder att visa
        if (orders.isEmpty()) {
            System.out.println("Inga orders hittades.");
            return;
        }

        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Orderlista ===");
        for (Order order : orders) {
            System.out.println("Order{" +

                    "order_id= " + order.getOrderId() +
                    ", customer_id='" + order.getId() +'\'' +
                    ", order_date='" + order.getOrder_date() + '\'' +

                    '}');
        }
    }



    public void showOrderByCustomerId(int customerId) throws SQLException {
        // Hämta alla kunder från repository-lagret
        List<Order> order = orderRepository.findOrdersByCustomerId(customerId);

        if (order.isEmpty()) {
            System.out.println("Inga ordrar hittades för kund med ID: " + customerId);
            return;
        }


        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Ordrar för kund " + customerId + " ===");
        for (Order orders : order) {
            System.out.println(order.toString());
        }
    }

    public void addOrder(int customer_id) throws SQLException {

        orderRepository.addOrder(customer_id);
        System.out.println("Ny order har lagts till för kund " + customer_id + " med dagens datum.");
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