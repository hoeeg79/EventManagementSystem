package GUI.Controller;

import BE.User;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersController extends BaseController{
    public TableColumn clnAdmin;
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
        }
    }

    public void handleCreateUsers(ActionEvent actionEvent) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String confirmPassword = tfConfirmPassword.getText();
        boolean isAdmin = cbIsAdmin.isSelected();
        try{

        if(password.equals(confirmPassword)){
            userModel.createUsers(username, password, isAdmin);
            cancel(btnCreate);
        }
        }catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }


    public void handleDeleteUsers(ActionEvent actionEvent) {
        try {
            User deletedUser = userList.getSelectionModel().getSelectedItem();
            userModel.deleteUsers(deletedUser);
        } catch (Exception e) {
            displayError(e);
        }
    }

    public void fillList(){
        try {
            clnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            clnAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));

            userList.getColumns().addAll();
            userList.setItems(userModel.getObservableUsers());
        }catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }
}
