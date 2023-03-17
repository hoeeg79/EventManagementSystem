package GUI.Model;

import BE.User;
import BLL.LoginManager;

import java.sql.SQLException;

public class LoginModel {
    private LoginManager loginManager;

    public LoginModel() {
        loginManager = new LoginManager();
    }

    public User login(String username, String password) throws Exception {
        return loginManager.login(username, password);
    }
}
