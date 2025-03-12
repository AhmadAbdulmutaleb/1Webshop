package Java.Admin;

import Java.Super.*;

import java.sql.*;
import java.util.ArrayList;

public class AdminRepository extends Repository {

    public ArrayList<Admin> getAll() throws SQLException {
        ArrayList<Admin> admins = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM admins")) {

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                admins.add(admin);
            }
        }
        return admins;
    }

    public Admin getAdminByUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM admins WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Admin(rs.getInt("admin_id"),
                    rs.getString("username"),
                    rs.getString("password"));
        }
    }
}
