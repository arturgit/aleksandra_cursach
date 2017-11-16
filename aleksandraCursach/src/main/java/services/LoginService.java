package services;

import databaseConnector.DatabaseConnector;
import models.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Artur on 15.11.2017.
 */
public class LoginService {
    private static final String loginQuery = "SELECT roles.id, roles.name from users INNER JOIN roles " +
            "ON users.role_id = roles.id WHERE login=? AND password=?;";

    public LoginService() { }

    public Role login(String login, String password) throws SQLException, ClassNotFoundException, IOException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = this.prepareLoginStatement(dbConnection, login, password);
        Role result = this.getRoleOfUser(statement);
        dbConnection.close();
        return result;
    }

    private PreparedStatement prepareLoginStatement(Connection dbConnection, String login, String password) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(LoginService.loginQuery);
        statement.setString(1, login);
        statement.setString(2, password);
        return statement;
    }

    private Role getRoleOfUser(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        return rs.next()
                ? new Role(new Long(rs.getString("id")), rs.getString("name"))
                : null;
    }
}
