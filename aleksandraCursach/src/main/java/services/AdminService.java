package services;

import databaseConnector.DatabaseConnector;
import models.Level;
import models.Position;
import models.Role;
import models.User;
import repository.LevelRepositoty;
import repository.PositionRepository;

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

    private static final String updateUserQuery = "UPDATE users " +
            "SET login=?, password=?, name=?, position_id=?, level_id=? " +
            "WHERE id=?;";

    private static final String checkExist = "SELECT id FROM users WHERE login=?;";

    private static final String deleteUserQuery = "DELETE FROM users WHERE users.id = ?;";

    private LevelRepositoty levelRepositoty = null;
    private PositionRepository positionRepository = null;

    public AdminService() {
        this.levelRepositoty = LevelRepositoty.getLevelRepositoty();
        this.positionRepository = PositionRepository.getPositionRepository();
    }

    public List<User> getUsers() throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.selectUsersQuery);
        List<User> users = this.getListOfUser(statement);
        dbConnection.close();
        return users;
    }

    public boolean saveUser(User user) throws SQLException, IOException, ClassNotFoundException {
        int levelId = this.levelRepositoty.findLevelIdByName(user.getLevel().getName());
        int positionId = this.positionRepository.findPositionIdByName(user.getPosition().getName());
        boolean res = this.checkExist(user.getLogin());
        if(res){
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
        return res;
    }

    public boolean updateUser(User user) throws SQLException, IOException, ClassNotFoundException {
        int levelId = this.levelRepositoty.findLevelIdByName(user.getLevel().getName());
        int positionId = this.positionRepository.findPositionIdByName(user.getPosition().getName());
        boolean res = this.checkExist(user.getLogin(), user.getId());
        if(res){
            Connection dbConnection = DatabaseConnector.getDBConnection();
            PreparedStatement statement = dbConnection.prepareStatement(AdminService.updateUserQuery);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setLong(4, positionId);
            statement.setLong(5, levelId);
            statement.setLong(6, user.getId());
            statement.execute();
            dbConnection.close();
        }
        return res;
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

    private boolean checkExist(String login) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.checkExist);
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        boolean res = rs.next();
        dbConnection.close();
        return res;
    }

    private boolean checkExist(String login, Long id) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.checkExist);
        statement.setString(1, login);
        ResultSet rs = statement.executeQuery();
        boolean res = rs.next();
        if (res && id != rs.getInt("id")) {
            return false;
        }
        dbConnection.close();
        return res;
    }

    public void deleteUser(long id) throws SQLException, IOException, ClassNotFoundException {
        Connection dbConnection = DatabaseConnector.getDBConnection();
        PreparedStatement statement = dbConnection.prepareStatement(AdminService.deleteUserQuery);
        statement.setLong(1, id);
        statement.execute();
        dbConnection.close();
    }
}
