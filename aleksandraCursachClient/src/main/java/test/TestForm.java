package test;

import models.Test;
import remote.ClientConnector;
import router.MainRouter;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by Artur on 03.12.2017.
 */
public class TestForm extends JFrame {
    private Test test = null;
    private QuestionPanel questionPanel = null;

    public TestForm() throws RemoteException {
        super(MainRouter.getMainRouter().getName() + " " + MainRouter.getMainRouter().getPosition().getName() + " " + MainRouter.getMainRouter().getLevel().getName());
        this.initTestValue();
        this.initFrameSettings();
        this.initComponents();
    }

    private void initTestValue() throws RemoteException {
        MainRouter router = MainRouter.getMainRouter();
        this.test = ClientConnector.getTestRemote().getTest(router.getPosition(), router.getLevel());
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        this.setLayout(new GridLayout(1,1));
        this.questionPanel = new QuestionPanel(this.test);
        this.add(questionPanel);
    }

}
