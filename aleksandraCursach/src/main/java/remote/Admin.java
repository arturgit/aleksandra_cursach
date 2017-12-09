package remote;

import models.Result;
import models.User;
import repository.ResultRepository;
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
    private ResultRepository resultRepository = null;

    public Admin() throws RemoteException {
        this.adminService = new AdminService();
        this.resultRepository = ResultRepository.getResultRepository();
    }

    public List<User> getUsers() throws RemoteException {
        try {
            return this.adminService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    public List<User> saveUser(User user) throws RemoteException {
        try {
            this.adminService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUsers();
    }

    public List<User> deleteUser(long id) throws RemoteException {
        try {
            this.adminService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUsers();
    }

    @Override
    public List<Result> getResults() throws RemoteException {
        try {
            return this.resultRepository.getAllResults();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
