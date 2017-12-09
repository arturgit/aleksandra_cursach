package models;

import java.io.Serializable;

/**
 * Created by Artur on 09.12.2017.
 */
public class Result implements Serializable {
    private Long id;
    private String login;
    private String position;
    private String level;
    private Integer colQuestions;
    private Integer rightQuestions;

    public Result(Long id, String login, String position, String level, Integer colQuestions, Integer rightQuestions) {
        this.id = id;
        this.login = login;
        this.position = position;
        this.level = level;
        this.colQuestions = colQuestions;
        this.rightQuestions = rightQuestions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setColQuestions(Integer colQuestions) {
        this.colQuestions = colQuestions;
    }

    public void setRightQuestions(Integer rightQuestions) {
        this.rightQuestions = rightQuestions;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPosition() {
        return position;
    }

    public String getLevel() {
        return level;
    }

    public Integer getColQuestions() {
        return colQuestions;
    }

    public Integer getRightQuestions() {
        return rightQuestions;
    }
}
