package models;

import java.io.Serializable;

/**
 * Created by Artur on 15.11.2017.
 */
public class Role implements Serializable {
    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
