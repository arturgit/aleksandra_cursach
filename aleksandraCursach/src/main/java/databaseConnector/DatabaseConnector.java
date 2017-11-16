package databaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artur on 15.11.2017.
 */
public class DatabaseConnector {
    private static final String driverName = "com.mysql.jdbc.Driver";

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException, IOException {
        DatabasePropertiesLoader loader = DatabasePropertiesLoader.getDatabasePropertiesLoader();
        Class.forName(DatabaseConnector.driverName);
        Connection dbConnection = DriverManager
                .getConnection(loader.getUrl(), loader.getUser(), loader.getPassword());
        return dbConnection;
    }
}
