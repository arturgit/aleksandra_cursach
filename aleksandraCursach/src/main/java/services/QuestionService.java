package services;

import models.Question;
import models.Test;
import repository.OptionRepository;
import repository.QuestionRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 30.11.2017.
 */
public class QuestionService {
    private static QuestionService questionService = null;

    private QuestionRepository questionRepository = null;
    private OptionRepository optionRepository = null;

    public static QuestionService getQuestionService() {
        if (QuestionService.questionService == null) {
            QuestionService.questionService = new QuestionService();
        }
        return QuestionService.questionService;
    }

    private QuestionService() {
        this.questionRepository = QuestionRepository.getQuestionRepository();
        this.optionRepository = OptionRepository.getOptionRepository();
    }

    public List<Question> getQuestionsByTestId(Test test) throws SQLException, IOException, ClassNotFoundException {
        List<Question> questions = this.questionRepository.selectQuestionsByTestId(test.getId());
        List<Question> result = new LinkedList<>();
        for(Question question: questions) {
            result.add(this.toQuestionWithOptions(question));
        }
        return result;
    }

    public Question toQuestionWithOptions(Question question) {
        try {
            question.setOptions(this.optionRepository.selectOptionsByQuestionId(question.getId()));
        } catch (Exception e) { }
        return question;
    }
}
