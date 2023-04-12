package BLL;

import BE.User;
import BLL.util.BCrypt;
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
        User user = loginFacade.login(username);

        String hashedPassword = user.getPassword();
        if(BCrypt.checkpw(password, hashedPassword)) {
            return user;
        } else {
            System.out.println("Invalid username or password.");
        }
        return null;
    }
}
