package GUI.Model;

import BE.User;
import BLL.LoginManager;

import java.sql.SQLException;

public class LoginModel {
    private LoginManager loginManager;

    /**
     * Constructor of loginModel
     */
    public LoginModel() {
        loginManager = new LoginManager();
    }

    /**
     * returns login from the loginManager, used in the view.
     */
    public User login(String username, String password) throws Exception {
        return loginManager.login(username, password);
    }
}
