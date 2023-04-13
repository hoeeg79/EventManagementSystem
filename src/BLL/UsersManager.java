package BLL;

import BE.User;
import DAL.User.UserFacade;

import java.util.List;

public class UsersManager {

    /**
     * Instantiates the userFacade
     */
    private UserFacade uf = new UserFacade();

    /**
     * A method used to send the createUser method through the layers to the GUI
     */
    public User createUser(String username, String password, boolean isAdmin) throws Exception {
        return uf.createUser(username, password, isAdmin);
    }

    /**
     * A method used to send the deleteUser method through the layers to the GUI
     */
    public void deleteUser(User deletedUser) throws Exception {
        uf.deleteUser(deletedUser);
    }

    /**
     * A method used to send the getUsers method through the layers to the GUI
     */
    public List<User> getUsers() throws Exception {
        return uf.getUsers();
    }
}
