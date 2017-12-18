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
        String text = "Вы правильно ответили на " + result + " вопросов из " + col;
        text += ". Выполнено " + this.roundResult(((double)result)/col*100, 2) + "% теста";
        if (((double)result)/col > 0.5) {
            text += ". Тест сдан успешно.";
        } else {
            text += ". Тест не сдан.";
        }

        this.textLabel = new JLabel(text);
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
        this.setSize(600, 140);
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

    double roundResult(double d, int precise) {
        precise = 10^precise;
        d = d*precise;
        int i = (int) Math.round(d);
        return (double) i/precise;
    }
}
