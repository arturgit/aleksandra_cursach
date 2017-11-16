package databaseConnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Artur on 15.11.2017.
 */
public class DatabasePropertiesLoader {
    private static final String propertiesPath = "src/main/resources/config.properties";
    private static DatabasePropertiesLoader databasePropertiesLoader = null;

    public static DatabasePropertiesLoader getDatabasePropertiesLoader() throws IOException {
        if (DatabasePropertiesLoader.databasePropertiesLoader == null) {
            DatabasePropertiesLoader.databasePropertiesLoader = new DatabasePropertiesLoader();
        }
        return DatabasePropertiesLoader.databasePropertiesLoader;
    }

    private Properties prop = null;

    private DatabasePropertiesLoader() throws IOException {
        this.prop = new Properties();
        InputStream input = new FileInputStream(DatabasePropertiesLoader.propertiesPath);
        this.prop.load(input);
    }

    public String getUrl() {
        return this.prop.getProperty("dburl");
    }

    public String getUser() {
        return this.prop.getProperty("dbuser");
    }

    public String getPassword() {
        return this.prop.getProperty("dbpassword");
    }
}
