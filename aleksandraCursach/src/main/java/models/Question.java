package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Artur on 30.11.2017.
 */
public class Question implements Serializable {
    private int id;
    private String title;
    private List<Option> options;

    public Question(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
