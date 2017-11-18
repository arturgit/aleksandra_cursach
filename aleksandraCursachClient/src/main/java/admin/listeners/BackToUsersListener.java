package admin.listeners;

import router.MainRouter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 18.11.2017.
 */
public class BackToUsersListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        MainRouter.getMainRouter().usersRoute();
    }
}
