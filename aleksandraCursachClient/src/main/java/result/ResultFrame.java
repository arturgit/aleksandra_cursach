package result;

import remote.ClientConnector;
import remote.TestRemote;
import result.listeners.SaveInFileListener;
import router.MainRouter;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by Artur on 04.12.2017.
 */
public class ResultFrame extends JFrame {
    private JLabel textLabel = null;
    private JButton saveButton = null;

    public ResultFrame(int testId, int col, int result) throws HeadlessException {
        this.initFrameSettings();
        this.textLabel = new JLabel("Вы правильно ответили на " + result + " вопросов из " + col);
        this.add(this.textLabel);

        this.initButtons(col, result);

        try {
            this.saveResult(testId, result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 140);
        this.setLayout(new GridLayout(2, 1));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void saveResult(int testId, int result) throws RemoteException {
        TestRemote testRemote = ClientConnector.getTestRemote();
        testRemote.saveTestResult((int)MainRouter.getMainRouter().getId(), testId, result);
    }

    private void initButtons(int col, int result) {
        this.saveButton = new JButton("Сохранить в файл");
        this.saveButton.addActionListener(new SaveInFileListener(col, result));
        this.add(this.saveButton);
    }
}
