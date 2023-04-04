package DAL.Login;

import BE.User;

/**
 * A Facade pattern for the Login package, used to provide a simplified version of the entire package.
 */

public class LoginFacade {
    public User login(String username, String password) throws Exception {
        Login login = new Login();
        return login.login(username, password);
    }
}
