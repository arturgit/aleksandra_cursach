package admin.listeners;

import models.Level;
import models.Position;
import models.User;
import remote.AdminRemote;
import remote.ClientConnector;
import router.MainRouter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by Artur on 18.12.2017.
 */
public class UpdateUserListener implements ActionListener {
    private JTextField loginField = null;
    private JTextField passwordField = null;
    private JTextField nameField = null;
    private JComboBox positionBox = null;
    private JComboBox levelBox = null;
    private Long id = null;

    public UpdateUserListener(Long id, JTextField loginField, JTextField passwordField, JTextField nameField,
                              JComboBox positionBox, JComboBox levelBox) {
        this.id = id;
        this.loginField = loginField;
        this.passwordField = passwordField;
        this.nameField = nameField;
        this.positionBox = positionBox;
        this.levelBox = levelBox;
    }

    public void actionPerformed(ActionEvent e) {
        AdminRemote remote = ClientConnector.getAdminRemote();
        try {
            if (!remote.updateUser(this.createUserModel())) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Такой пользователь уже существует.");
            } else {
                MainRouter.getMainRouter().usersRoute();
            }
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    private User createUserModel() {
        return new User(this.id, loginField.getText(), passwordField.getText(), nameField.getText(),
                null, new Position(null, positionBox.getSelectedItem().toString()),
                new Level(null, levelBox.getSelectedItem().toString()));
    }
}
