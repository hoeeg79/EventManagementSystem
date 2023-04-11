package BLL;

import BE.User;
import DAL.Login.LoginFacade;

public class LoginManager {

    /**
     * Instantiates the loginFacade
     */
    private LoginFacade loginFacade = new LoginFacade();

    /**
     * A method used to send the login method through the layers to the GUI
     */
    public User login(String username, String password) throws Exception {
        return loginFacade.login(username, password);
    }
}
