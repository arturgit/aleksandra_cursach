package serverRemote;

import models.Role;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Artur on 14.11.2017.
 */
public interface ServerRemote extends Remote {
    Role login(String login, String password) throws RemoteException;
}