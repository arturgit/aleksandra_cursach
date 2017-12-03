package remote;

import models.Test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Artur on 30.11.2017.
 */
public interface TestRemote extends Remote {
    public Test getTest() throws RemoteException;
}
