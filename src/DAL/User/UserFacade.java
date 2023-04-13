package DAL.User;

import BE.User;


import java.util.List;

/**
 * A Facade pattern for the User package, used to provide a simplified version of the entire package.
 */
public class UserFacade {
    public User createUser(String username, String password, boolean isAdmin) throws Exception {
        CreateUser cu = new CreateUser();
        return cu.createUser(username, password, isAdmin);
    }

    public void deleteUser(User deletedUser) throws Exception {
        DeleteUser du = new DeleteUser();
        du.deleteUser(deletedUser);
    }

    public List<User> getUsers() throws Exception {
        GetUsers users = new GetUsers();
        return users.returnUsers();
    }
}
