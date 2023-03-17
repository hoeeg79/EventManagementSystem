package GUI.Controller;

import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController extends BaseController {

    public Button createEvent;
    public Button deleteEvent;
    public Button sellTickets;
    public Button editEvent;
    
    private EventModel model;


    public void handleCreateEvent(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventView.fxml"));
        Parent root = loader.load();

        CreateEventViewController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("Create an Event");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void handleDeleteEvent(ActionEvent actionEvent) {
    }

    public void handleSellTickets(ActionEvent actionEvent) {
    }

    public void handleEditEvent(ActionEvent actionEvent) {
    }

    @Override
    public void setup() {

    }
}
