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
     * Compares the password of the parameter with the user from the database.
     * If they match a user is returned.
     */
    public User login(String username, String password) throws Exception {
        User user = loginFacade.login(username);

        String hashedPassword = user.getPassword();
        if (BCrypt.checkpw(password, hashedPassword)) {
            return user;
        } else {
            System.out.println("Invalid username or password.");
        }
        return null;
    }
}
