package services;

import models.Question;
import repository.QuestionRepository;

import java.util.List;

/**
 * Created by Artur on 30.11.2017.
 */
public class QuestionService {

    private QuestionRepository questionRepository = null;

    public QuestionService() {
        this.questionRepository = QuestionRepository.getQuestionRepository();
    }

    public Question toQuestionWithOptions(Question question) {

    }
}
