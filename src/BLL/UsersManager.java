package BLL;

import BE.User;
import DAL.User.UserFacade;

import java.util.List;

public class UsersManager {
    private UserFacade uf = new UserFacade();

    public User createUser(String username, String password, boolean isAdmin) throws Exception {
        return uf.createUser(username, password, isAdmin);
    }

    public void deleteUser(User deletedUser) throws Exception {
        uf.deleteUser(deletedUser);
    }

    public List<User> getUsers() throws Exception{
        return uf.getUsers();
    }
}
