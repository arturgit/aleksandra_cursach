package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Artur on 30.11.2017.
 */
public class Test implements Serializable {
    private int id;
    private String title;
    private List<Question> questions;

    public Test(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
