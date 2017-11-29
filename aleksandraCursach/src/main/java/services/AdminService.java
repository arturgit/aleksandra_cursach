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

    private static final String saveUserQuery = "INSERT INTO users (login, password, name, role_id, position_id, level_id) " +
            "VALUE (?, ?, ?, ?, ?, ?);";

    private static final String findLevel = "SELECT * FROM levels WHERE levels.name = ?;";

    private static final String findPosition = "SELECT * FROM positions WHERE positions.name = ?;";

    private static final String deleteUserQuery = "DELETE FROM users WHERE users.id = ?;";

    public List<User> getUsers() throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.selectUsersQuery);
        List<User> users = this.getListOfUser(statement);
        dbConnection.close();
        return users;
    }

    public void saveUser(User user) throws SQLException, IOException, ClassNotFoundException {
        int levelId = this.findLevelByName(user.getLevel().getName());
        int positionId = this.findPositionByName(user.getPosition().getName());

        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.saveUserQuery);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setLong(4, 1);
        statement.setLong(5, positionId);
        statement.setLong(6, levelId);
        statement.execute();
        dbConnection.close();
    }

    private int findLevelByName(String name) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.findLevel);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        dbConnection.close();
        return id;
    }

    private int findPositionByName(String name) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.findPosition);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        dbConnection.close();
        return id;
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

    public void deleteUser(long id) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.deleteUserQuery);
        statement.setLong(1, id);
        statement.execute();
        dbConnection.close();
    }
}
