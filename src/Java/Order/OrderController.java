package Java.Order;

import java.sql.SQLException;
import java.util.Scanner;

import Java.Product.ProductRepository;
import Java.OrderProduct.OrderProductService;


public class OrderController {

    OrderService orderService;
    private OrderProductService orderProductService;
    private ProductRepository productRepository;

    Scanner scanner;

    public OrderController() {


        this.orderService = new OrderService();
        this.orderProductService = new OrderProductService();
        this.productRepository = new ProductRepository();
        this.scanner = new Scanner(System.in);
    }

    public void run() {



        while (true) {
            try {

                System.out.println("\n");
                System.out.println("Orderhantering " );

                System.out.println("1.  Visa alla Orders    ");
                System.out.println("2.  Hitta en Order       ");
                System.out.println("3.  Lägg till en Order   ");
                System.out.println("4.  Lägg till produkter i en order    ");
                System.out.println("0.  Avsluta           " );

                System.out.print(" Välj ett alternativ: " );

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        System.out.println("\n=== Visar alla Ordrar " );
                        orderService.showAllOrders();
                        System.out.println( " Alla Ordrar listade !" );
                        break;

                    case "2":
                        System.out.println( "\n=== Sök Order innehåll " );
                        System.out.print( " Ange KundensID: " );
                        String idInput = scanner.nextLine();
                        int id = Integer.parseInt(idInput);
                        orderService.showOrderByCustomerId(id);
                        System.out.println(" Orders hittad!" );
                        break;

                    case "3":
                        System.out.println( "\n=== Lägg till ny order " );
                        System.out.print( " Ange kund-ID: " );
                        String customerIdInput = scanner.nextLine();
                        int customerId = Integer.parseInt(customerIdInput);

                        // Skapa en ny order med dagens datum automatiskt
                        orderService.addOrder(customerId);
                        System.out.println(" Ny order tillagd framgångsrikt med dagens datum!");
                        break;

                    case "4":
                        System.out.println( " Handla/ lägg Produkter i en order" );
                        handlaOrderProducts();


                    case "0":
                        System.out.println( "\n Avslutar Orderhantering... " );


                        return;
                    default:
                        System.out.println(" Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {
                System.out.println(" Databasfel: " + e.getMessage() );
            } catch (Exception e) {
                System.out.println(" Ett fel uppstod: " + e.getMessage() );
                scanner.nextLine();
            }
        }
    }

    private void handlaOrderProducts(){



        while (true) {
            try {
                System.out.println("\n");
                System.out.println( "Hantera Orderprodukter " );

                System.out.println("1.  Visa produkter i en order    " );
                System.out.println( "2.  Lägg till produkt i order    " );
                System.out.println( "3.  Ändra antal av en produkt    " );
                System.out.println("4.   Ta bort produkt från order   " );
                System.out.println(" 0.   Tillbaka till huvudmenyn    ");
               ;
                System.out.print(" Välj ett alternativ:");

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        showOrderProducts();
                        break;

                    case "2":
                        addProductToOrder();
                        break;

                    case "3":
                        //updateProductQuantity();
                        break;

                    case "4":
                        //removeProductFromOrder();

                        break;

                    case "0":
                        return;

                    default:
                        System.out.println(" Ogiltigt val, försök igen" );
                }

            } catch (SQLException e) {
                System.out.println( " Databasfel: " + e.getMessage() );
            } catch (Exception e) {
                System.out.println(" Ett fel uppstod: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
    private void showOrderProducts() throws SQLException {



        System.out.println("\n=== Visa produkter i order \uD83D\uDED2 ===");
        System.out.print(" Ange order-ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        orderProductService.showAllOrderProducts(orderId);
        System.out.println(" Alla kunder listade framgångsrikt!");

    }



    private void addProductToOrder() throws SQLException {



        System.out.println("\n=== Lägg till produkt i order  ===");

        // Visa alla tillgängliga produkter först
        System.out.println("\nTillgängliga produkter:");
        productRepository.getAll();

        System.out.print(" Ange order-ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print(" Ange produkt-ID: " );
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print(" Ange antal: " );
        int quantity = Integer.parseInt(scanner.nextLine());

        orderProductService.addProductToOrder(orderId, productId, quantity);

    }
}



