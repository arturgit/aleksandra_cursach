package admin.listeners;

import router.MainRouter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 18.11.2017.
 */
public class CreatingUserFormListener implements ActionListener {
    private JTable table;

    public CreatingUserFormListener(JTable table) {
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        MainRouter.getMainRouter().createUserRoute();
    }
}
