package repository;

import databaseConnector.DatabaseConnector;
import models.Question;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 30.11.2017.
 */
public class QuestionRepository {
    private static final String selectQuestionsByTestIdQuery = "SELECT * FROM questions WHERE questions.test_id=?;";

    private static QuestionRepository questionRepository = null;

    public static QuestionRepository getQuestionRepository() {
        if (QuestionRepository.questionRepository == null) {
            QuestionRepository.questionRepository = new QuestionRepository();
        }
        return QuestionRepository.questionRepository;
    }

    private QuestionRepository() {}

    public List<Question> selectQuestionsByTestId(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(QuestionRepository.selectQuestionsByTestIdQuery);
        statement.setInt(1, id);
        List<Question> questions = toQuestions(statement.executeQuery());
        dbConnection.close();
        return questions;
    }

    private List<Question> toQuestions(ResultSet rs) throws SQLException {
        List<Question> questions = new LinkedList<Question>();
        while(rs.next()) {
            questions.add(new Question(rs.getInt("id"), rs.getString("title")));
        }
        return questions;
    }
}
