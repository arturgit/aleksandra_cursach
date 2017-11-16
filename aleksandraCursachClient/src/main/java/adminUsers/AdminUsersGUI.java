package adminUsers;

import adminUsers.helpers.TableUser;
import models.User;
import serverRemote.ClientConnector;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Artur on 15.11.2017.
 */
public class AdminUsersGUI extends JFrame {

    public AdminUsersGUI() {
        super("Users");
        this.initFrameSettings();
        this.initComponents();
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        TableModel model = new UsersTableModel(this.getTableUsersFromServer());
        JTable table = new JTable(model);
        getContentPane().add(new JScrollPane(table));
    }

    private ArrayList<TableUser> getTableUsersFromServer() {
        ArrayList<User> users = null;
        try {
            users = (ArrayList<User>) ClientConnector.getAdminRemote().getUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
            users = new ArrayList<User>();
        }
        return this.transformUsersToTableUsers(users);
    }

    private ArrayList<TableUser> transformUsersToTableUsers(ArrayList<User> users) {
        ArrayList<TableUser> tableUsers = new ArrayList<TableUser>();
        for (User user: users) {
            tableUsers.add(new TableUser(user));
        }
        return tableUsers;
    }
}
