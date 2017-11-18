package remote;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Artur on 16.11.2017.
 */
public interface AdminRemote extends Remote {
    List<User> getUsers() throws RemoteException;
}
