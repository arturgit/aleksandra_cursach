package test;

import router.MainRouter;

import javax.swing.*;

/**
 * Created by Artur on 03.12.2017.
 */
public class TestForm extends JFrame {
    private JLabel titleLabel = null;

    public TestForm() {
        super(MainRouter.getMainRouter().getName() + " " + MainRouter.getMainRouter().getPosition().getName() + " " + MainRouter.getMainRouter().getLevel().getName());
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
        this.titleLabel = new JLabel();
        this.add(titleLabel);
    }

}
