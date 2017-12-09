package repository;

import databaseConnector.DatabaseConnector;
import models.Result;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 09.12.2017.
 */
public class ResultRepository {
    private static final String selectAllResults = "SELECT results.id, users.login, positions.name as position, " +
            "levels.name as level, \n" +
            "COUNT(questions.id) as col, results.result FROM results \n" +
            "INNER JOIN users ON user_id = users.id\n" +
            "INNER JOIN positions ON users.position_id = positions.id\n" +
            "INNER JOIN levels ON users.level_id = levels.id\n" +
            "INNER JOIN tests ON results.test_id = tests.id\n" +
            "INNER JOIN questions ON questions.test_id = tests.id\n" +
            "GROUP BY results.id;";

    private static ResultRepository resultRepository = null;

    public static ResultRepository getResultRepository() {
        if (ResultRepository.resultRepository == null) {
            ResultRepository.resultRepository = new ResultRepository();
        }
        return ResultRepository.resultRepository;
    }

    private ResultRepository() {}

    public List<Result> getAllResults() throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(ResultRepository.selectAllResults);
        ResultSet rs = statement.executeQuery();
        List<Result> results = this.toResults(rs);
        dbConnection.close();
        return results;
    }


    private List<Result> toResults(ResultSet rs) throws SQLException {
        List<Result> questions = new LinkedList<>();
        while(rs.next()) {
            questions.add(new Result(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("position"),
                    rs.getString("level"),
                    rs.getInt("col"),
                    rs.getInt("result")));
        }
        return questions;
    }
}
