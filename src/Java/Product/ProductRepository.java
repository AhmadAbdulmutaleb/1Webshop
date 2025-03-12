package Java.Product;

import Java.Super.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository {

    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getInt("Manufacturer_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
                System.out.println(product);
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException {

        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Product(productId,
                    rs.getInt("manufacturer_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock_quantity")
            );
        }
    }

        /*

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
    */

    public List<Product> findByCategory(Integer categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT p.*, m.name as manufacturer_name FROM products p " +
                             "JOIN products_categories pc ON p.product_id = pc.product_id " +
                             "LEFT JOIN manufacturers m ON p.manufacturer_id = m.manufacturer_id " +
                             "WHERE pc.category_id = ?")) {

            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getInt("Manufacturer_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
                products.add(product);
            }
        }

        return products;
    }

    public void updateProductStock(int productId, int newQuantity) throws SQLException {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, productId);

            pstmt.executeUpdate();
        }
    }

    public boolean updateProduct(int productId, int manufacturerId, String name, String description, double price, int stockQuantity) throws SQLException {


        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(" UPDATE products SET manufacturer_id = ?, name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?")) {

            pstmt.setInt(1, manufacturerId);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, stockQuantity);
            pstmt.setInt(6,productId);

            int  rowsUpdaterad = pstmt.executeUpdate();
            return rowsUpdaterad > 0 ;
        }

    }


}