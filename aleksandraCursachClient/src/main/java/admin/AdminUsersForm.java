package admin;

import admin.panels.ResultsPanel;
import admin.panels.UsersPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Artur on 15.11.2017.
 */
public class AdminUsersForm extends JFrame {
    private UsersPanel usersPanel = null;
    private ResultsPanel resultsPanel = null;

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
        this.setLayout(new GridLayout(2,1));
        this.createComponents();
    }

    private void createComponents() {
        this.usersPanel = new UsersPanel();
        this.resultsPanel = new ResultsPanel();
        getContentPane().add(this.usersPanel);
        getContentPane().add(this.resultsPanel);
    }

}
