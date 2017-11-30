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
public class PositionRepository {
    private static final String findPosition = "SELECT * FROM positions WHERE positions.name = ?;";

    private static PositionRepository positionRepository = null;

    public static PositionRepository getPositionRepository() {
        if (PositionRepository.positionRepository == null) {
            PositionRepository.positionRepository = new PositionRepository();
        }
        return PositionRepository.positionRepository;
    }

    private PositionRepository() {}

    public int findPositionIdByName(String name) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(PositionRepository.findPosition);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        dbConnection.close();
        return id;
    }
}
