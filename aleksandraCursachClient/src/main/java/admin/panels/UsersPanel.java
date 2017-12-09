package admin.panels;

import admin.helpers.TableUser;
import admin.helpers.UsersTableModel;
import admin.listeners.CreatingUserFormListener;
import admin.listeners.DeleteUserListener;
import models.User;
import remote.ClientConnector;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Artur on 09.12.2017.
 */
public class UsersPanel extends JPanel {
    private JTable table = null;
    private UsersTableModel tableModel = null;
    private JButton newButton = null;
    private JButton deleteButton = null;
    private JLabel titleLabel = null;

    public UsersPanel() {
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.createComponents();
        this.initJTable();
        this.initButtonMenu();
    }

    private void createComponents() {
        this.table = new JTable();
        this.newButton = new JButton("New");
        this.deleteButton = new JButton("Delete");
        this.titleLabel = new JLabel("Users: ");
    }

    private void initButtonMenu() {
        this.add(this.getButtonMenu(), BorderLayout.PAGE_START);
    }

    private JPanel getButtonMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.newButton.addActionListener(new CreatingUserFormListener(this.table));
        this.deleteButton.addActionListener(new DeleteUserListener(this.table, this.tableModel));
        panel.add(this.titleLabel);
        panel.add(this.newButton);
        panel.add(this.deleteButton);
        return panel;
    }

    private void initJTable() {
        this.add(this.getTable(), BorderLayout.CENTER);
    }

    private JScrollPane getTable() {
        this.tableModel = new UsersTableModel(this.getTableUsersFromServer());
        this.table = new JTable(this.tableModel);
        return new JScrollPane(table);
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
