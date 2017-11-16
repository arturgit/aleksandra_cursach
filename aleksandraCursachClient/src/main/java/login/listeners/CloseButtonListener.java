package login.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 15.11.2017.
 */
public class CloseButtonListener implements ActionListener {
    private JFrame frame;

    public CloseButtonListener(JFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        this.frame.dispose();
    }
}
