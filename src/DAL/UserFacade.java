package DAL;

import BE.User;

public class UserFacade {
    public User createUser(String username, String password, boolean isAdmin) throws Exception {
        CreateUser cu = new CreateUser();
        return cu.createUser(username, password, isAdmin);
    }

    public void deleteUser(User deletedUser) throws Exception {
        DeleteUser du = new DeleteUser();

        du.deleteUser(deletedUser);
    }
}
