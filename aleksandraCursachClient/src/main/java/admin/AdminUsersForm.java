package admin;

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
 * Created by Artur on 15.11.2017.
 */
public class AdminUsersForm extends JFrame {
    private JTable table = null;
    private UsersTableModel tableModel = null;
    private JButton newButton = null;
    private JButton editButton = null;
    private JButton deleteButton = null;

    public AdminUsersForm() {
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
        this.setLayout(new GridBagLayout());
        this.createComponents();
        this.initJTable();
        this.initButtonMenu();
    }

    private void createComponents() {
        this.table = new JTable();
        this.newButton = new JButton("New");
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("Delete");
    }

    private void initButtonMenu() {
        GridBagConstraints c =  new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill   = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        getContentPane().add(this.getButtonMenu(), c);
    }

    private JPanel getButtonMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newButton.addActionListener(new CreatingUserFormListener(this.table));
//        editButton.addActionListener(new EditUserListener(this.table));
        deleteButton.addActionListener(new DeleteUserListener(this.table, this.tableModel));
        panel.add(newButton);
//        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    private void initJTable() {
        GridBagConstraints c =  new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 10;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill   = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        getContentPane().add(this.getTable(), c);
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

    public void reloadUsers() {
        this.table.setModel(new UsersTableModel(this.getTableUsersFromServer()));
    }
}
