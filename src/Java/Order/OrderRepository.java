package Java.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Java.Super.*;

public class  OrderRepository extends Repository {


    public ArrayList<Order> FindAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(

                     "SELECT * FROM orders"))  {



            while (rs.next()) {
                Order order = new Order(

                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date")

                );
                orders.add(order);
            }
        }
        return orders;


    }

    public List<Order> findOrdersByCustomerId(int customer_id) throws SQLException {

        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customer_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(

                        rs.getInt("order_id"),
                        customer_id,
                        rs.getDate("order_date")

                );
                orders.add(order);
            }
        }

        return orders;


    }


    public void addOrder(int id) throws SQLException {

        String sql = "INSERT INTO orders (customer_id, order_date) " +
                "VALUES (?, CURRENT_TIMESTAMP)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();


        }
    }





}
