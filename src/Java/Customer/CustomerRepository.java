package Java.Customer;

import Java.Super.Repository;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository extends Repository {

    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password")
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException {

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(customerId,
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("password") );
        }
    }

    public Customer getCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("password") );
        }
    }

    public void addCustomer(String name, String phone, String email, String address, String password) throws SQLException {

        String sql = "INSERT INTO customers (name, email, phone, address, password) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, password);

            pstmt.executeUpdate();
        }
    }




    public  void deleteCustomer(int id) throws SQLException{

        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);


            pstmt.executeUpdate();
        }
    }


    public boolean updateCustomer(int id, String name, String email, String phone, String address, String password) throws SQLException {


        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE customers SET name = ?," +
                     " email = ?, phone = ?, address = ?, password = ?" +
                     " WHERE customer_id = ?")) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, password);
            pstmt.setInt(6,id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // returnerar ture om minst en rad ändras.
        }

    }





    /**
     * Här kan fler metoder läggas till som t.ex:
     * - addCustomer
     * - getCustomerById
     * - updateCustomer
     * - deleteCustomer
     * - findCustomerByEmail
     *
     * Varje metod kommer följa samma mönster:
     * 1. Skapa Connection med DriverManager.getConnection(URL)
     * 2. Skapa Statement eller PreparedStatement
     * 3. Utför databasoperationen
     * 4. Hantera resultatet
     * 5. Låt try-with-resources stänga alla resurser
     */
}