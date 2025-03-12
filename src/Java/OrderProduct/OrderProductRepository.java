package Java.OrderProduct;

import Java.Product.*;

import Java.Super.Repository;

import java.sql.*;
import java.util.ArrayList;

public class OrderProductRepository extends Repository {

    private ProductRepository productRepository;

    public OrderProductRepository() {
        this.productRepository = new ProductRepository();
    }


    public ArrayList<OrderProduct> getOrderProducts(int orderId) throws  SQLException {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();

        String sql = "SELECT * FROM orders_products WHERE order_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                OrderProduct orderProduct = new OrderProduct(
                        rs.getInt("order_product_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );

                orderProducts.add(orderProduct);

            }



        }

        return orderProducts;
    }



    public void addProductToOrder(int orderId, int productId, int quantity ) throws SQLException {

        Product product = productRepository.getProductById(productId);
        Double unitPrice = product.getPrice();
        String sql = "INSERT INTO orders_products (order_id, product_Id, quantity, unit_price ) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, unitPrice);


            pstmt.executeUpdate();
        }
    }

}
