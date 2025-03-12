package Java;

import Java.Customer.CustomerController;
import Java.Login.LoginController;
import Java.Order.OrderController;
import Java.OrderProduct.OrderProduct;
import Java.Product.Product;
import Java.Product.ProductController;

public class Main {
    public static void main(String[] args) {

        CustomerController customerController = new CustomerController();
        customerController.run();

        OrderController orderController = new OrderController();
        orderController.run();

        ProductController productController = new ProductController();
        productController.run();

        LoginController loginController = new LoginController();
        loginController.run();



    }


}