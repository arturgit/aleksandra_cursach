package models;

import java.io.Serializable;

/**
 * Created by Artur on 30.11.2017.
 */
public class Option implements Serializable {
    private int id;
    private String title;
    private boolean istrue;

    public Option(int id, String title, boolean istrue) {
        this.id = id;
        this.title = title;
        this.istrue = istrue;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIstrue() {
        return istrue;
    }
}
