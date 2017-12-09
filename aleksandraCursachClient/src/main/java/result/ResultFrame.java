package result;

import remote.ClientConnector;
import remote.TestRemote;
import router.MainRouter;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by Artur on 04.12.2017.
 */
public class ResultFrame extends JFrame {
    private JLabel textLabel = null;

    public ResultFrame(int testId, int result) throws HeadlessException {
        this.initFrameSettings();
        this.textLabel = new JLabel("Вы правильно ответили на " + result + " вопросов.");
        this.add(this.textLabel);
        try {
            this.saveResult(testId, result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 70);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void saveResult(int testId, int result) throws RemoteException {
        TestRemote testRemote = ClientConnector.getTestRemote();
        testRemote.saveTestResult((int)MainRouter.getMainRouter().getId(), testId, result);
    }
}
