package adminUsers.helpers;

import models.User;

/**
 * Created by Artur on 16.11.2017.
 */
public class TableUser {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String role;
    private String position;
    private String level;

    public TableUser(Long id, String login, String password, String name, String role, String position, String level) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.position = position;
        this.level = level;
    }

    public TableUser(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.role = user.getRole().getName();
        this.position = user.getPosition().getName();
        this.level = user.getLevel().getName();
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

    public String getRole() {
        return role;
    }

    public String getPosition() {
        return position;
    }

    public String getLevel() {
        return level;
    }
}
