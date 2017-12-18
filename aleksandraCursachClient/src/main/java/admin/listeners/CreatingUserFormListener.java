package admin.listeners;

import models.User;
import router.MainRouter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 18.11.2017.
 */
public class CreatingUserFormListener implements ActionListener {
    private JTable table;
    private boolean state;

    public CreatingUserFormListener(JTable table, boolean state, User user) {
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        MainRouter.getMainRouter().createUserRoute(state, table);
    }
}
