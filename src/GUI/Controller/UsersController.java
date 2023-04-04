package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersController extends BaseController{
    @FXML
    private TableColumn clnAdmin;
    @FXML
    private TableColumn clnUsername;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<User> userList;
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
        try{
        userModel = super.getUModel();
        fillList();
        }
        catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Creates a new user for the application.
     */
    public void handleCreateUsers(ActionEvent actionEvent) throws Exception {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPassword.getText();
        boolean isAdmin = cbIsAdmin.isSelected();
        try {
        if(password.equals(confirmPassword)){
            userModel.createUsers(username, password, isAdmin);
            closeWindow(btnCreate);
        }
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }


    /**
     * Deletes a user from the application.
     */
    public void handleDeleteUsers(ActionEvent actionEvent) throws Exception {
        try{
            User deletedUser = userList.getSelectionModel().getSelectedItem();
            userModel.deleteUsers(deletedUser);
        } catch (Exception e) {
            displayError(e);
        }
    }

    /**
     * Fills the column with the specified information
     */
    public void fillList(){
        clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        clnAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));

        userList.getColumns().addAll();
        userList.setItems(userModel.getObservableUsers());
    }
}
