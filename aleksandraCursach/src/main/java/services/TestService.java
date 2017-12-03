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
    private static final String selectTestQuery = "Select * from tests WHERE tests.level_id=? and tests.position_id=?;";

    private LevelRepositoty levelRepositoty = null;
    private PositionRepository positionRepository = null;
    private QuestionService questionService = null;

    public TestService() {
        this.levelRepositoty = LevelRepositoty.getLevelRepositoty();
        this.positionRepository = PositionRepository.getPositionRepository();
        this.questionService = QuestionService.getQuestionService();
    }

    public Test getTest(String levelName, String positionName) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(TestService.selectTestQuery);
        statement.setInt(1, this.levelRepositoty.findLevelIdByName(levelName));
        statement.setInt(2, this.positionRepository.findPositionIdByName(positionName));
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
}
