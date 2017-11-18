import router.MainRouter;
import remote.ClientConnector;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Artur on 14.11.2017.
 */
public class Main {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        ClientConnector.initServerRemote();
        MainRouter router = MainRouter.getMainRouter();
    }
}