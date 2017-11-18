package router;

import admin.AdminUsersForm;
import admin.CreateUserForm;
import login.LoginForm;
import models.Role;

import javax.swing.*;

/**
 * Created by Artur on 15.11.2017.
 */
public class MainRouter {
    private static Role role = null;
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
        MainRouter.role = role;
    }

    public boolean loginRoute() {
        return this.usersRoute();
    }

    public boolean usersRoute() {
        if (MainRouter.role != null && MainRouter.role.getName().equals("Admin")) {
            this.currentFrame.dispose();
            this.currentFrame = new AdminUsersForm();
            this.currentFrame.setVisible(true);
            return true;
        }
        return false;
    }

    public boolean createUserRoute() {
        if (MainRouter.role != null && MainRouter.role.getName().equals("Admin")) {
            this.currentFrame.dispose();
            this.currentFrame = new CreateUserForm();
            this.currentFrame.setVisible(true);
            return true;
        }
        return false;
    }
}
