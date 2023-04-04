package GUI.Controller;

import GUI.Model.EventModel;
import BE.User;
import GUI.Model.LoginModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController extends BaseController{
    @FXML
    private Label lblWarning;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    private LoginModel loginModel = new LoginModel();

    /**
     * Used to log into the application. Checks if the user is an admin or a coordinator.
     */
    private void login(){

        try {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
            User user = loginModel.login(username, password);
            System.out.println(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/MainView.fxml"));

            if (user == null){
                lblWarning.setText("Username or password is invalid.");
            }else if (user.isAdmin()){
                Parent root = loader.load();
                Scene scene = new Scene(root);

                MainViewController controller = loader.getController();
                controller.setModel(new EventModel());
                controller.setUModel(new UserModel());
                controller.setUser(user);
                controller.setup();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System");
                primaryStage.show();
            } else if (!user.isAdmin()) {
                Parent root = loader.load();
                Scene scene = new Scene(root);

                MainViewController controller = loader.getController();
                controller.setModel(new EventModel());
                controller.setUser(user);
                controller.setup();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System.");
                primaryStage.show();
            }
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Calls the login(); method, making you log into the application on click.
     */
    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        login();
    }

    /**
     * Clicks the login button, by a press of the enter key.
     */
    @FXML
    private void handlePasswordCheckKey(KeyEvent keyEvent) {
        checkIfEnter(keyEvent);
    }

    /**
     * Clicks the login button, by a press of the enter key.
     */
    @FXML
    private void handleUsernameCheckKey(KeyEvent keyEvent) {
        checkIfEnter(keyEvent);
    }

    /**
     * Clicks the login button, by a press of the enter key.
     */
    private void checkIfEnter(KeyEvent event) {
        try{
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }} catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Does nothing, it is however required, to extend BaseController.
     * Try catch is there for fun
     */
    @Override
    public void setup() throws Exception {
        try{} catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }

    }
}
