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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 15.11.2017.
 */
public class AdminService {
    private static final String selectUsersQuery = "SELECT users.id, users.login, users.password, users.name, " +
            "users.role_id, users.position_id, users.level_id, roles.name as role_name, " +
            "positions.name as position_name, levels.name as level_name from users INNER JOIN roles " +
            "ON users.role_id = roles.id INNER JOIN positions ON users.position_id = positions.id " +
            "INNER JOIN levels ON users.level_id = levels.id;";

    public List<User> getUsers() throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.selectUsersQuery);
        return this.getListOfUser(statement);
    }

    private List<User> getListOfUser(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            users.add(this.fromResultSet(rs));
        }
        return users;
    }

    private User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
            new Long(rs.getString("id")),
            rs.getString("login"),
            rs.getString("password"),
            rs.getString("name"),
            new Role(new Long(rs.getString("role_id")), rs.getString("role_name")),
            new Position(new Long(rs.getString("position_id")), rs.getString("position_name")),
            new Level(new Long(rs.getString("level_id")), rs.getString("level_name"))
        );
    }
}
