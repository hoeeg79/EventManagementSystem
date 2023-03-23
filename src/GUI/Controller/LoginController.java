package GUI.Controller;

import GUI.Model.EventModel;
import BE.User;
import GUI.Model.LoginModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private Label lblWarning;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    private LoginModel loginModel = new LoginModel();

    private void login(){

        try {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
            User user = loginModel.login(username, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/MainView.fxml"));

            if (user == null){
                lblWarning.setText("Username or password is invalid.");
            }else if (user.isAdmin()){
                Parent root = loader.load();
                Scene scene = new Scene(root);

                MainViewController controller = loader.getController();
                controller.setModel(new EventModel());
                controller.setUModel(new UserModel());
                controller.setup();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System.");
                primaryStage.show();
            } else if (!user.isAdmin()) {
                Parent root = loader.load();
                Scene scene = new Scene(root);

                MainViewController controller = loader.getController();
                controller.setModel(new EventModel());
                controller.setup();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Event System.");
                primaryStage.show();
            }
        } catch (Exception e) {
            System.out.println("something went fuck");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        login();
    }
}
