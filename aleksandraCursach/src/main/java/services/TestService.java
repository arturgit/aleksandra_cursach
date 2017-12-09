package services;

import databaseConnector.DatabaseConnector;
import models.Test;
import repository.LevelRepositoty;
import repository.PositionRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artur on 30.11.2017.
 */
public class TestService {
    private static TestService testService = null;
    private static final String selectTestQuery = "SELECT * FROM tests WHERE tests.level_id=? AND tests.position_id=?;";
    private static final String saveTestQuery = "INSERT INTO results (user_id, test_id, result) VALUE (?, ?, ?);";

    public static TestService getInstance(){
        if (TestService.testService == null) {
            TestService.testService = new TestService();
        }
        return TestService.testService;
    }

    private LevelRepositoty levelRepositoty = null;
    private PositionRepository positionRepository = null;
    private QuestionService questionService = null;

    public TestService() {
        this.levelRepositoty = LevelRepositoty.getLevelRepositoty();
        this.positionRepository = PositionRepository.getPositionRepository();
        this.questionService = QuestionService.getQuestionService();
    }

    public Test getTest(String levelName, String positionName) throws SQLException, IOException, ClassNotFoundException {
        return this.getTest(new Long(this.levelRepositoty.findLevelIdByName(levelName)), new Long(this.positionRepository.findPositionIdByName(positionName)));
    }

    public Test getTest(Long levelId, Long positionId) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(TestService.selectTestQuery);
        statement.setLong(1, levelId);
        statement.setLong(2, positionId);
        Test test = this.toTest(statement.executeQuery());
        dbConnection.close();
        return test;
    }

    private Test toTest(ResultSet rs) throws SQLException, IOException, ClassNotFoundException {
        Test test = null;
        if (rs.next()) {
            test = new Test(rs.getInt("id"), rs.getString("title"));
        }
        return this.toTestWithQuestions(test);
    }

    private Test toTestWithQuestions(Test test) throws SQLException, IOException, ClassNotFoundException {
        test.setQuestions(this.questionService.getQuestionsByTestId(test));
        return test;
    }

    public void saveTest(int userId, int testId, int result) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(TestService.saveTestQuery);
        statement.setLong(1, userId);
        statement.setLong(2, testId);
        statement.setLong(3, result);
        statement.execute();
        dbConnection.close();
    }
}
