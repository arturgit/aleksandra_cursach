package models;

import java.io.Serializable;

/**
 * Created by Artur on 15.11.2017.
 */
public class User  implements Serializable {
    private Long id;
    private String login;
    private String password;
    private String name;
    private Role role;
    private Position position;
    private Level level;

    public User(Long id, String login, String password, String name, Role role, Position position, Level level) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.position = position;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Position getPosition() {
        return position;
    }

    public Level getLevel() {
        return level;
    }
}
