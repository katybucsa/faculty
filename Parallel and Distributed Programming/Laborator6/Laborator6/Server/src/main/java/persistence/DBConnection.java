package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created on: 19-Dec-19, 23:06
 *
 * @author: Katy Bucșa
 */

public class DBConnection {
    private Properties properties;

    public DBConnection(Properties properties) {
        this.properties = properties;
    }

    private static Connection connection = null;

    private Connection getNewConnection() {
        String driver = properties.getProperty("concerte.jdbc.driver");
        String url = properties.getProperty("concerte.jdbc.url");
        String user = properties.getProperty("concerte.jdbc.user");
        String password = properties.getProperty("concerte.jdbc.password");
        Connection conn = null;
        try {
            Class.forName(driver);
            if (user != null && password != null)
                conn = DriverManager.getConnection(url, user, password);
            else
                conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver " + cnfe);
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }
        return conn;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed())
                connection = getNewConnection();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

