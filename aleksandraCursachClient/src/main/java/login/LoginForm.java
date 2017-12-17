package login;

import login.listeners.CloseButtonListener;
import login.listeners.LoginButtonListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Artur on 15.11.2017.
 */
public class LoginForm extends JFrame {
    private JLabel loginLabel = new JLabel("Логин:");
    private JLabel passwordLabel = new JLabel("Пароль:");
    private JTextField loginTextField = new JTextField(10);
    private JPasswordField passwordField = new JPasswordField(10);
    private JButton loginButton = new JButton("Войти");
    private JButton closeButton = new JButton("Закрыть");

    public LoginForm() {
        super("Вход");
        this.initFrameSettings();
        this.initComponents();
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(230, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        this.initContainerSettings();
        this.addLoginRow();
        this.addPasswordRow();
        this.addButtonsRow();
        this.addButtonListeners();
    }

    private void initContainerSettings() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,1,2,2));
    }

    private void addLoginRow() {
        Container container = this.getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.loginLabel);
        panel.add(this.loginTextField);
        container.add(panel);
    }

    private void addPasswordRow() {
        Container container = this.getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.passwordLabel);
        panel.add(this.passwordField);
        container.add(panel);
    }

    private void addButtonsRow() {
        Container container = this.getContentPane();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.loginButton);
        panel.add(this.closeButton);
        container.add(panel);
    }

    public void addButtonListeners() {
        this.loginButton.addActionListener(new LoginButtonListener(this.loginTextField, this.passwordField));
        this.closeButton.addActionListener(new CloseButtonListener(this));
    }
}
