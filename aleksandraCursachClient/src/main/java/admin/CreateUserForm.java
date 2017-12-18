package admin;

import admin.listeners.BackToUsersListener;
import admin.listeners.CreateUserListener;
import admin.listeners.UpdateUserListener;
import models.Level;
import models.Position;
import models.User;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

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

    public CreateUserForm(boolean state, JTable table) {
        this.init(state, table);
        this.setUser(this.createUserModel(table));
    }

    private void init(boolean state, JTable table) {
        this.initFrameSettings();
        this.initComponents();
        int column = 0;
        int row = table.getSelectedRow();
        TableModel model = table.getModel();
        Long id = new Long(model.getValueAt(row, column).toString());
        ActionListener listener = state
                ? new CreateUserListener(this.loginField, this.passwordField, this.nameField, this.positionBox, this.levelBox)
                : new UpdateUserListener(id, this.loginField, this.passwordField, this.nameField, this.positionBox, this.levelBox);
        String labelValue = state
                ? "Создать"
                : "Редактировать";
        this.initButtonListeners(listener, labelValue);
    }

    public void setUser(User user) {
        this.loginField.setText(user.getLogin());
        this.passwordField.setText(user.getPassword());
        this.nameField.setText(user.getName());
        this.positionBox.setSelectedItem(user.getPosition());
        this.levelBox.setSelectedItem(user.getLevel());
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
    }

    private void createComponents() {
        this.backButton = new JButton("Назад");
        this.createButton = new JButton("Создать");
        this.loginLabel = new JLabel("Логин:");
        this.loginField = new JTextField(10);
        this.passwordLabel = new JLabel("Пароль:");
        this.passwordField = new JTextField(10);
        this.nameLabel = new JLabel("Имя:");
        this.nameField = new JTextField(10);
        this.positionLabel = new JLabel("Позиция:");
        String[] positions = { "Tester", "BA", "WebDev", "JavaDev" };
        this.positionBox = new JComboBox(positions);
        this.levelLabel = new JLabel("Уровень");
        String[] levels = { "Junior", "Middle", "Senior" };
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

    private void initButtonListeners(ActionListener listener, String labelValue) {
        this.backButton.addActionListener(new BackToUsersListener());
        this.createButton.addActionListener(listener);
        this.createButton.setText(labelValue);
    }

    private User createUserModel(JTable table) {
        int column = 0;
        int row = table.getSelectedRow();
        TableModel model = table.getModel();
        String value = model.getValueAt(row, column).toString();

        return new User(
                new Long(model.getValueAt(row, 0).toString()),
                model.getValueAt(row, 1).toString(),
                model.getValueAt(row, 2).toString(),
                model.getValueAt(row, 3).toString(),
                null,
                new Position(null, model.getValueAt(row, 4).toString()),
                new Level(null, model.getValueAt(row, 5).toString()));
    }
}
