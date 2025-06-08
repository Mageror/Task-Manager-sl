package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ This ensures the driver is registered
            String URL = "jdbc:mysql://localhost:3306/task_system";
            String USER = "root";
            String PASSWORD = "SASI.4227.s";
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
