package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MainViewController {

    @FXML
    private Button createEvent;
    @FXML
    private Button deleteEvent;
    @FXML
    private Button sellTickets;
    @FXML
    private Button editEvent;

    @FXML
    private TableView<Event> eventBordet;

    private EventModel eventModel;


    @FXML
    private void handleCreateEvent(ActionEvent actionEvent) {
    }

    @FXML
    private void handleDeleteEvent(ActionEvent actionEvent) throws Exception {
        try{
            Event deletedEvent = eventBordet.getSelectionModel().getSelectedItem();
            eventModel.deleteEvent(deletedEvent);
        }catch (Exception e){
            throw new Exception(e);
        }

    }

    @FXML
    private void handleSellTickets(ActionEvent actionEvent) {
    }

    @FXML
    private void handleEditEvent(ActionEvent actionEvent) {
    }
}
