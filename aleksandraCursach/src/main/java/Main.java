import remote.Admin;
import remote.Server;
import remote.TestRemoteImpl;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 * Created by Artur on 14.11.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Registry registry = LocateRegistry.createRegistry(4200);
        registry.rebind("ServerRemote", new Server());
        registry.rebind("AdminRemote", new Admin());
        registry.rebind("TestRemote", new TestRemoteImpl());
        System.err.println("ServerRemote ready");
    }
}
