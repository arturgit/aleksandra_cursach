package repository;

import databaseConnector.DatabaseConnector;
import models.Option;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 03.12.2017.
 */
public class OptionRepository {
    private static final String selectOptionsByQuestionIdQuery = "SELECT * FROM options WHERE option.qurstion_id=?;";

    private static OptionRepository optionRepository = null;

    public static OptionRepository getOptionRepository() {
        if (OptionRepository.optionRepository == null) {
            OptionRepository.optionRepository = new OptionRepository();
        }
        return OptionRepository.optionRepository;
    }

    public OptionRepository() {
    }

    public List<Option> selectOptionsByQuestionId(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(OptionRepository.selectOptionsByQuestionIdQuery);
        statement.setInt(1, id);
        List<Option> options = toOptions(statement.executeQuery());
        dbConnection.close();
        return options;
    }

    private List<Option> toOptions(ResultSet rs) throws SQLException {
        List<Option> options = new LinkedList<Option>();
        while(rs.next()) {
            options.add(new Option(rs.getInt("id"), rs.getString("title"), rs.getBoolean("isTrue")));
        }
        return options;
    }
}
