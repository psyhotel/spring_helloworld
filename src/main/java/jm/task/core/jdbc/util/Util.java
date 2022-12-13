package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private static final String connectionURL = "jdbc:mysql://localhost:3306/mydbtest";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(connectionURL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}