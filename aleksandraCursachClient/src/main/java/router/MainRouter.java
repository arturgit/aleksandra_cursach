package router;

import admin.AdminUsersForm;
import admin.CreateUserForm;
import login.LoginForm;
import models.Level;
import models.Position;
import models.Role;
import models.User;
import result.ResultFrame;
import test.TestForm;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 * Created by Artur on 15.11.2017.
 */
public class MainRouter {
    private static User user = User.empty();
    private static MainRouter mainRouter = null;

    public static MainRouter getMainRouter() {
        if (MainRouter.mainRouter == null) {
            MainRouter.mainRouter = new MainRouter();
        }
        return MainRouter.mainRouter;
    }

    JFrame currentFrame = null;

    private MainRouter() {
        this.currentFrame = new LoginForm();
        this.currentFrame.setVisible(true);
    }

    public void setRole(Role role) {
        MainRouter.user.setRole(role);
    }


    public long getId() {
        return this.user.getId();
    }

    public void setId(long id) {
        MainRouter.user.setId(id);
    }

    public Level getLevel() {
        return this.user.getLevel();
    }

    public void setLevel(Level level) {
        MainRouter.user.setLevel(level);
    }

    public Position getPosition() {
        return this.user.getPosition();
    }

    public void setPosition(Position position) {
        MainRouter.user.setPosition(position);
    }

    public String getName() {
        return MainRouter.user.getName();
    }

    public void setName(String name) {
        MainRouter.user.setName(name);
    }

    public boolean loginRoute() {
        return this.usersRoute();
    }

    public boolean usersRoute() {
        if (MainRouter.user.getRole() != null && MainRouter.user.getRole().getName().equals("Admin")) {
            this.toAdminRoute();
            return true;
        } else {
            this.toTestRoute();
            return false;
        }
    }

    private void toAdminRoute() {
        this.currentFrame.dispose();
        this.currentFrame = new AdminUsersForm();
        this.currentFrame.setVisible(true);
    }

    private void toTestRoute() {
        try {
            this.currentFrame.dispose();
            this.currentFrame = new TestForm();
            this.currentFrame.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean createUserRoute() {
        if (MainRouter.user.getRole() != null && MainRouter.user.getRole().getName().equals("Admin")) {
            this.currentFrame.dispose();
            this.currentFrame = new CreateUserForm();
            this.currentFrame.setVisible(true);
            return true;
        }
        return false;
    }

    public void resultRoute(int testId, int result) {
        this.currentFrame.dispose();
        this.currentFrame = new ResultFrame(testId, result);
        this.currentFrame.setVisible(true);
    }
}
