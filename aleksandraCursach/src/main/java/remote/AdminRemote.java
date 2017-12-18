package remote;

import models.Result;
import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Artur on 16.11.2017.
 */
public interface AdminRemote extends Remote {
    List<User> getUsers() throws RemoteException;

    boolean saveUser(User user) throws RemoteException;

    List<User> deleteUser(long id) throws RemoteException;

    List<Result> getResults() throws RemoteException;
}
