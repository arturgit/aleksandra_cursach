package admin.listeners;

import admin.helpers.TableUser;
import admin.helpers.UsersTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 18.11.2017.
 */
public class EditUserListener implements ActionListener {
    private JTable table;

    public EditUserListener(JTable table) {
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        TableUser user = ((UsersTableModel)this.table.getModel()).getRow(this.table.getSelectedRow());
    }
}
