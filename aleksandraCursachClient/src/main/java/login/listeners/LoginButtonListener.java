package login.listeners;

import models.User;
import remote.ClientConnector;
import remote.ServerRemote;
import router.MainRouter;

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

    private User login(String login, String password) {
        try {
            ServerRemote serverRemote = ClientConnector.getServerRemote();
            return serverRemote.login(login, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkLogin(User user) {
        if (user != null) {
            MainRouter router = MainRouter.getMainRouter();
            router.setRole(user.getRole());
            router.setLevel(user.getLevel());
            router.setPosition(user.getPosition());
            router.setName(user.getName());
            router.loginRoute();
        }
    }
}
