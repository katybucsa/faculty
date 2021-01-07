package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Properties properties;
    private static final Logger logger= LogManager.getLogger();
    public DBConnection(Properties properties){
        this.properties=properties;
    }
    private static Connection connection=null;
    private Connection getNewConnection(){
        logger.traceEntry();
        String driver=properties.getProperty("curse.jdbc.driver");
        String url=properties.getProperty("curse.jdbc.url");
        String user=properties.getProperty("curse.jdbc.user");
        String password=properties.getProperty("curse.jdbc.password");
        logger.info("trying to connect to database... {}",url);
        logger.info("user: {}",user);
        logger.info("password: {}",password);
        Connection conn=null;
        try{
            Class.forName(driver);
            logger.info("Loaded driver ...{}",driver);
            if(user!=null && password!=null)
                conn= DriverManager.getConnection(url,user,password);
            else
                conn=DriverManager.getConnection(url);
        }catch (ClassNotFoundException cnfe){
            logger.error(cnfe);
            System.out.println("Error loading driver "+ cnfe);
        }catch (SQLException e){
            logger.error(e);
            System.out.println("Error getting connection "+ e);
        }
        return conn;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try{
            if(connection==null || connection.isClosed())
                connection=getNewConnection();
        }catch (SQLException e){
            logger.error(e);
            System.out.println("Error "+e);
        }
        logger.traceExit(connection);
        return connection;
    }
    public static void closeConnection(){
        logger.traceEntry("Close database connection");
        try{
            connection.close();
        }catch (SQLException e){
            logger.error(e);
        }
        logger.traceExit("Database connection closed successfully");
    }
}
