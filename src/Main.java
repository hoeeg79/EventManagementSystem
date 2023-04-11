import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    /**
     * Main is used as the startup for our application.
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Launches the stage for our LoginView, starting the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LoginView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event System");
        primaryStage.show();
    }
}