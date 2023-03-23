package GUI.Model;

import BE.User;
import BLL.UsersManager;
import javafx.collections.ObservableList;

public class UserModel {

    private UsersManager usersManager;
    private ObservableList<User> usersList;

    public UserModel() {
        usersManager = new UsersManager();
    }

    public void createUsers(String username, String password, boolean isAdmin) throws Exception {
        User au = usersManager.createUser(username, password, isAdmin);
        usersList.add(au);
    }

    public void deleteUsers(User deletedUsers) throws Exception{
        usersManager.deleteUser(deletedUsers);
        usersList.remove(deletedUsers);
    }

    public ObservableList<User> getObservableUsers(){
        return usersList;
    }
}
