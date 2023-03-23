package BLL;

import BE.User;
import DAL.UserFacade;

public class UsersManager {
    private final UserFacade uf = new UserFacade();

    public User createUser(String username, String password, boolean isAdmin) throws Exception {
        return uf.createUser(username, password, isAdmin);
    }

    public void deleteUser(User deletedUser) throws Exception {
        uf.deleteUser(deletedUser);
    }

}
