package GUI.Model;

import BE.User;
import BLL.UsersManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {

    private UsersManager usersManager;
    private ObservableList<User> usersList;

    /**
     * Constructor of the UserModel class
     */

    public UserModel() throws Exception {
        usersManager = new UsersManager();

        usersList = FXCollections.observableArrayList();
        usersList.addAll(usersManager.getUsers());
    }

    /**
     * Creates a user and adds it to the observableList<User>
     */
    public void createUsers(String username, String password, boolean isAdmin) throws Exception {
        User au = usersManager.createUser(username, password, isAdmin);
        usersList.add(au);

    }

    /**
     * Deletes a user from the observableList<User>
     */
    public void deleteUsers(User deletedUsers) throws Exception{
        usersManager.deleteUser(deletedUsers);
        usersList.remove(deletedUsers);
    }

    /**
     * a getter for the observableUsers list
     */
    public ObservableList<User> getObservableUsers(){
        return usersList;
    }
}
