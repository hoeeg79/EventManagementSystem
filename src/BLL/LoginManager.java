package BLL;

import BE.User;
import DAL.LoginFacade;

public class LoginManager {
    private LoginFacade loginFacade = new LoginFacade();

    public User login(String username, String password) throws Exception {
        return loginFacade.login(username, password);
    }
}
