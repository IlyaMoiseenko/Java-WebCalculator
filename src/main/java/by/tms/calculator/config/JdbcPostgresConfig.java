package by.tms.calculator.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcPostgresConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/Calculator";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (connection != null)
                return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
