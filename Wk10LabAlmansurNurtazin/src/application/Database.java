package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_DRIVER =
            "com.mysql.cj.jdbc.Driver";

    private static final String DB_CONNECTION =
            "jdbc:mysql://localhost/employmentdb";

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "class123";

    public static Connection getDBConnection() {

        Connection connection = null;

        try {

            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(
                    DB_CONNECTION,
                    DB_USER,
                    DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}