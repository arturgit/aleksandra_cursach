package login.listeners;

import models.Role;
import router.MainRouter;
import serverRemote.ClientConnector;
import serverRemote.ServerRemote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by Artur on 15.11.2017.
 */
public class LoginButtonListener implements ActionListener {
    private JTextField loginTextField;
    private JPasswordField passwordField;

    public LoginButtonListener(JTextField loginTextField, JPasswordField passwordField) {
        this.loginTextField = loginTextField;
        this.passwordField = passwordField;
    }

    public void actionPerformed(ActionEvent e) {
        String login = this.loginTextField.getText();
        String password = new String(this.passwordField.getPassword());
        this.checkLogin(this.login(login, password));
    }

    private Role login(String login, String password) {
        try {
            ServerRemote serverRemote = ClientConnector.getServerRemote();
            return serverRemote.login(login, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkLogin(Role role) {
        if (role != null) {
            MainRouter router = MainRouter.getMainRouter();
            router.setRole(role);
            router.loginRoute();
        }
    }
}
