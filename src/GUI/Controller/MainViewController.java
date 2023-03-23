package GUI.Controller;

import BE.Event;
import GUI.Model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainViewController extends BaseController {
    @FXML
    private TableColumn clnLocation;
    @FXML
    private TableColumn clnDate;
    @FXML
    private TableColumn clnTime;
    @FXML
    private TableColumn clnTitle;
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

    @Override
    public void setup() {
        eventModel = super.getModel();
        fillEventList();

    }

    @FXML
    private void handleCreateEvent(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventView.fxml"));
            Parent root = loader.load();

            CreateEventViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(root));
            stage.setTitle("Create an Event");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e){
            displayError(e);
        }
    }

    @FXML
    private void handleDeleteEvent(ActionEvent actionEvent){
        try{
            Event deletedEvent = eventBordet.getSelectionModel().getSelectedItem();
            eventModel.deleteEvent(deletedEvent);
        }catch (Exception e){
            displayError(e);
        }
    }

    @FXML
    private void handleSellTickets(ActionEvent actionEvent) {
    }

    @FXML
    private void handleEditEvent(ActionEvent actionEvent) {
    }

    private void fillEventList() {
        try {
            //Gives every column a property to look for in given object
            // It uses the getters from the object, to retrieve the values
            clnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
            clnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            clnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            clnTime.setCellValueFactory(new PropertyValueFactory<>("time"));

            //Add the Movies to the list
            eventBordet.getColumns().addAll();
            eventBordet.setItems(eventModel.getObservableEvents());
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    public void handleManageUsers(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/UsersView.fxml"));
            Parent root = loader.load();

            UsersController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(root));
            stage.setTitle("Create an Event");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }catch (Exception e){
            displayError(e);
        }

    }
}
