package GUI.Controller;

import GUI.Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    private LoginModel loginModel = new LoginModel();

    private void login(){
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();

        try {
            if (loginModel.login(username, password).isAdmin()){
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/MainView.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System.");
                primaryStage.show();
            } else if (!loginModel.login(username, password).isAdmin()) {
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/MainView.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System.");
                primaryStage.show();
            }
        } catch (Exception e) {
            System.out.println("something went fuck");
            e.printStackTrace();
        }
    }

    public void handleLogin(ActionEvent actionEvent) {
        login();
    }
}
