package admin.helpers;

import models.Result;

/**
 * Created by Artur on 09.12.2017.
 */
public class TableResult {
    private Long id;
    private String login;
    private String position;
    private String level;
    private Integer colQuestions;
    private Integer rightQuestions;

    public TableResult(Long id, String login, String position, String level, Integer colQuestions, Integer rightQuestions) {
        this.id = id;
        this.login = login;
        this.position = position;
        this.level = level;
        this.colQuestions = colQuestions;
        this.rightQuestions = rightQuestions;
    }

    public TableResult(Result result) {
        this.id = result.getId();
        this.login = result.getLogin();
        this.position = result.getPosition();
        this.level = result.getLevel();
        this.colQuestions = result.getColQuestions();
        this.rightQuestions = result.getRightQuestions();
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
