package remote;

import models.Role;
import models.User;
import services.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Artur on 14.11.2017.
 */
public class Server extends UnicastRemoteObject implements ServerRemote {
    private LoginService loginService = null;

    public Server() throws RemoteException {
        this.loginService = new LoginService();
    }

    public User login(String login, String password) throws RemoteException {
        try {
            return this.loginService.login(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
