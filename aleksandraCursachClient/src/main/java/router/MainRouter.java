package router;

import adminUsers.AdminUsersGUI;
import login.LoginGUI;
import models.Role;

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

    private LoginGUI loginGUI = null;
    private AdminUsersGUI adminUsersGUI = null;

    private MainRouter() {
        this.loginGUI = new LoginGUI();
        this.clear();
        this.loginGUI.setVisible(true);
    }

    public void setRole(Role role) {
        MainRouter.role = role;
    }

    public void loginRoute() {
        if (MainRouter.role != null && MainRouter.role.getName().equals("Admin")) {
            this.clear();
            this.adminUsersGUI = new AdminUsersGUI();
            this.adminUsersGUI.setVisible(true);
        }
    }

    private void clear() {
        this.loginGUI.setVisible(false);
        if (this.adminUsersGUI != null) this.adminUsersGUI.setVisible(false);
    }
}
