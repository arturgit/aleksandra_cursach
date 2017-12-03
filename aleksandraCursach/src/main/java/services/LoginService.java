package services;

import databaseConnector.DatabaseConnector;
import models.Level;
import models.Position;
import models.Role;
import models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artur on 15.11.2017.
 */
public class LoginService {
    private static final String loginQuery = "SELECT users.id, roles.id as role_id, roles.name as role_name, " +
            "positions.id as position_id, positions.name as position_name, users.name, " +
            "levels.id as level_id, levels.name as level_name " +
            "from users INNER JOIN roles ON users.role_id = roles.id " +
            "INNER JOIN positions ON users.position_id = positions.id " +
            "INNER JOIN levels ON users.level_id = levels.id WHERE login=? AND password=?;";

    public LoginService() { }

    public User login(String login, String password) throws SQLException, ClassNotFoundException, IOException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = this.prepareLoginStatement(dbConnection, login, password);
        User result = this.getUser(statement);
        dbConnection.close();
        return result;
    }

    private PreparedStatement prepareLoginStatement(Connection dbConnection, String login, String password) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(LoginService.loginQuery);
        statement.setString(1, login);
        statement.setString(2, password);
        return statement;
    }

    private User getUser(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        return rs.next()
                ? this.toUser(rs)
                : null;
    }

    private User toUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                "",
                "",
                rs.getString("name"),
                new Role(rs.getLong("role_id"), rs.getString("role_name")),
                new Position(rs.getLong("position_id"), rs.getString("position_name")),
                new Level(rs.getLong("level_id"), rs.getString("level_name"))
        );
    }
}
