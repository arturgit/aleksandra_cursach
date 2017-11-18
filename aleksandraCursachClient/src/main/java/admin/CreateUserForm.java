package admin;

import admin.listeners.BackToUsersListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Artur on 18.11.2017.
 */
public class CreateUserForm extends JFrame {
    private JButton backButton = null;
    private JButton createButton = null;

    private JLabel loginLabel = null;
    private JTextField loginField = null;

    private JLabel passwordLabel = null;
    private JTextField passwordField = null;

    private JLabel nameLabel = null;
    private JTextField nameField = null;

    private JLabel positionLabel = null;
    private JComboBox positionBox = null;

    private JLabel levelLabel = null;
    private JComboBox levelBox = null;

    public CreateUserForm() {
        this.initFrameSettings();
        this.initComponents();
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 350);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.createComponents();
        this.initRow(this.loginLabel, this.loginField, FlowLayout.LEFT);
        this.initRow(this.passwordLabel, this.passwordField, FlowLayout.LEFT);
        this.initRow(this.nameLabel, this.nameField, FlowLayout.LEFT);
        this.initRow(this.positionLabel, this.positionBox, FlowLayout.LEFT);
        this.initRow(this.levelLabel, this.levelBox, FlowLayout.LEFT);
        this.initRow(this.createButton, this.backButton, FlowLayout.CENTER);
        this.initButtonListeners();
    }

    private void createComponents() {
        this.backButton = new JButton("Back");
        this.createButton = new JButton("Create");
        this.loginLabel = new JLabel("Login:");
        this.loginField = new JTextField(10);
        this.passwordLabel = new JLabel("Password:");
        this.passwordField = new JTextField(10);
        this.nameLabel = new JLabel("Name:");
        this.nameField = new JTextField(10);
        this.positionLabel = new JLabel("Position:");
        String[] positions = { "tester", "webDev", "javaDev" };
        this.positionBox = new JComboBox(positions);
        this.levelLabel = new JLabel("Level");
        String[] levels = { "junior", "middle", "senior" };
        this.levelBox = new JComboBox(levels);
    }

    private void initRow(JComponent col1, JComponent col2, int flowLayoutPosition) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(flowLayoutPosition));
        panel.add(this.wrapInPanel(col1));
        panel.add(this.wrapInPanel(col2));
        this.add(panel);
    }

    private JPanel wrapInPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(130, 40));
        panel.add(component);
        return panel;
    }

    private void initButtonListeners() {
        this.backButton.addActionListener(new BackToUsersListener());
    }
}
