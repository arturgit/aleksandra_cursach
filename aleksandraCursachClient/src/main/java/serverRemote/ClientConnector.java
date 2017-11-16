package serverRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Artur on 15.11.2017.
 */
public class ClientConnector {
    private static final String serverRemotePath = "//localhost:4200/ServerRemote";
    private static final String adminRemotePath = "//localhost:4200/AdminRemote";

    private static ServerRemote serverRemote = null;
    private static AdminRemote adminRemote = null;

    public static ServerRemote getServerRemote() {
        ClientConnector.checkRemote(ClientConnector.serverRemote, "serverRemote is null");
        return ClientConnector.serverRemote;
    }

    public static AdminRemote getAdminRemote() {
        ClientConnector.checkRemote(ClientConnector.serverRemote, "adminRemote is null");
        return ClientConnector.adminRemote;
    }

    private static void checkRemote(Remote remote, String errorText) {
        if (remote == null) {
            throw new NullPointerException(errorText);
        }
    }

    public static void initServerRemote()  throws RemoteException, NotBoundException, MalformedURLException {
        ClientConnector.serverRemote = (ServerRemote) Naming.lookup(ClientConnector.serverRemotePath);
        ClientConnector.adminRemote = (AdminRemote) Naming.lookup(ClientConnector.adminRemotePath);
    }

    private ClientConnector() { }
}

