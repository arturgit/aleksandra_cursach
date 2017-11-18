package remote;

import models.User;
import services.AdminService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 16.11.2017.
 */
public class Admin extends UnicastRemoteObject implements AdminRemote {
    private AdminService adminService = null;

    public Admin() throws RemoteException {
        this.adminService = new AdminService();
    }

    public List<User> getUsers() throws RemoteException {
        try {
            return this.adminService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }
}
