package repository;

import databaseConnector.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artur on 30.11.2017.
 */
public class LevelRepositoty {
    private static final String findLevel = "SELECT * FROM levels WHERE levels.name = ?;";

    private static LevelRepositoty levelRepositoty = null;

    public static LevelRepositoty getLevelRepositoty() {
        if (LevelRepositoty.levelRepositoty == null) {
            LevelRepositoty.levelRepositoty = new LevelRepositoty();
        }
        return LevelRepositoty.levelRepositoty;
    }

    private LevelRepositoty() {}

    public int findLevelIdByName(String name) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(LevelRepositoty.findLevel);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        dbConnection.close();
        return id;
    }
}
