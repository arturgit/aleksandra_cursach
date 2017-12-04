package remote;

import models.Level;
import models.Position;
import models.Test;
import services.TestService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Artur on 04.12.2017.
 */
public class TestRemoteImpl extends UnicastRemoteObject implements TestRemote {
    private TestService testService = null;

    public TestRemoteImpl() throws RemoteException {
        this.testService = TestService.getInstance();
    }

    @Override
    public Test getTest(Position position, Level level) throws RemoteException {
        try {
            return this.testService.getTest(level.getId(), position.getId());
        } catch (Exception e) {
            return null;
        }
    }
}