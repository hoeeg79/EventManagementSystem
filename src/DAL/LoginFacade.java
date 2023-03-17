package DAL;

import BE.User;

public class LoginFacade {
    public User login(String username, String password) throws Exception {
        Login login = new Login();
        return login.login(username, password);
    }
}
