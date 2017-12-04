package result;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Artur on 04.12.2017.
 */
public class ResultFrame extends JFrame {
    private JLabel textLabel = null;

    public ResultFrame(int result) throws HeadlessException {
        this.initFrameSettings();
        this.textLabel = new JLabel("Вы правильно ответили на " + result + " вопросов.");
        this.add(this.textLabel);
    }

    private void initFrameSettings() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 70);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
