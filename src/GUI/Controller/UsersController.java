package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UsersController extends BaseController{
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnDelete;
    @FXML
    private ListView userList;
    @FXML
    private CheckBox cbIsAdmin;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfConfirmPassword;
    private UserModel userModel;


    @Override
    public void setup() {
        userModel = new UserModel();
    }

    public void handleCreateCoordinator(ActionEvent actionEvent) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPassword.getText();
        boolean isAdmin = cbIsAdmin.isSelected();
        try{

        if(password == confirmPassword)
            userModel.createUsers(username, password, isAdmin);
        }catch(Exception e){
            displayError(e);
        }
    }


    public void handleDeleteCoordinator(ActionEvent actionEvent) {
        try {
            User deletedUser = (User) userList.getSelectionModel().getSelectedItem();
            userModel.deleteUsers(deletedUser);
        } catch (Exception e) {
            displayError(e);
        }
    }


}
