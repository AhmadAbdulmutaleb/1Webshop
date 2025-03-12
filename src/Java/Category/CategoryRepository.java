package Java.Category;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Java.Super.Repository.URL;

public class CategoryRepository {
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"));
                categories.add(category);
            }
        }

        return categories;
    }
}
