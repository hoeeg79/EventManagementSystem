package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateEventViewController {
    @FXML
    private Button saveEvent;
    @FXML
    private Button cancelEvent;
    @FXML
    private TextField txtNameOfEvent;
    @FXML
    private TextField txtNumberOfParticipants;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtTimeOfEvent;


    @FXML
    private void handleSaveEvent(ActionEvent actionEvent) {

    }

    @FXML
    private void handleCancelEvent(ActionEvent actionEvent) {
    }
}
