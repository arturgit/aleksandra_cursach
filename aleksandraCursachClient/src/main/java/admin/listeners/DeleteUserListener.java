package admin.listeners;

import admin.helpers.TableUser;
import admin.helpers.UsersTableModel;
import remote.AdminRemote;
import remote.ClientConnector;
import router.MainRouter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by Artur on 18.11.2017.
 */
public class DeleteUserListener implements ActionListener {
    private JTable table;
    private UsersTableModel tableModel;

    public DeleteUserListener(JTable table, UsersTableModel tableModel) {
        this.table = table;
        this.tableModel = tableModel;
    }

    public void actionPerformed(ActionEvent e) {
        int selectedRow = this.table.getSelectedRow();
        if (selectedRow != -1) {
            TableUser user = this.tableModel.getRow(selectedRow);
            AdminRemote remote = ClientConnector.getAdminRemote();
            try {
                remote.deleteUser(user.getId());
                MainRouter.getMainRouter().usersRoute();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }
}
