package GUI.Controller;

import BE.Event;
import BE.User;
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
import java.sql.Date;
import java.sql.Time;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainViewController extends BaseController {
    @FXML
    private Button btnManageUsers;
    @FXML
    private TableColumn clnLocation;
    @FXML
    private TableColumn<Event, Date> clnDate;
    @FXML
    private TableColumn<Event, Time> clnTime;
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
    private User user;

    @Override
    public void setup() {
        eventModel = super.getModel();
        fillEventList();
        if (user.isAdmin()){
            enableAdmin();
        } else {
            enableCoordinator();
        }

    }

    public void setUser(User user){
        this.user = user;
    }

    @FXML
    private void handleCreateEvent(ActionEvent actionEvent) throws IOException {
        openEventView(actionEvent, "Create an Event", false);
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
    private void handleSellTickets(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = eventBordet.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {

            eventModel.setSelectedEvent(selectedEvent);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/TicketView.fxml"));
            Parent root = loader.load();

            TicketViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(root));
            stage.setTitle("Ticket");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        }
    }

    @FXML
    private void handleEditEvent(ActionEvent actionEvent) {
        openEventView(actionEvent, "Edit Event", true);
    }

    /**
     * Method to fill the main table when view is opened
     */
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

    private void openEventView(ActionEvent actionEvent, String title, boolean editView) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventView.fxml"));
            Parent root = loader.load();

            CreateEventViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            if (editView) {
                Event chosenEvent = getChosenEvent();
                controller.setFields(chosenEvent);
            } else {
                controller.notEdit();
            }

            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            displayError(e);
        }
    }

    private Event getChosenEvent() {
            int id = eventBordet.getSelectionModel().getSelectedItem().getId();
            String name = eventBordet.getSelectionModel().getSelectedItem().getName();
            Date date = eventBordet.getSelectionModel().getSelectedItem().getDate();
            Time time = eventBordet.getSelectionModel().getSelectedItem().getTime();
            String location = eventBordet.getSelectionModel().getSelectedItem().getLocation();

            return new Event(id, name, date, time, location);
        }

    public void handleManageUsers(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/UsersView.fxml"));
            Parent root = loader.load();

            UsersController controller = loader.getController();
            controller.setUModel(super.getUModel());
            controller.setup();

            stage.setScene(new Scene(root));
            stage.setTitle("Create a User");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }catch (Exception e){
            displayError(e);
        }
    }

    public void enableAdmin(){
        createEvent.setVisible(false);
        sellTickets.setVisible(false);
        editEvent.setVisible(false);
        deleteEvent.setVisible(true);
        btnManageUsers.setVisible(true);
    }

    public void enableCoordinator(){
        createEvent.setVisible(true);
        sellTickets.setVisible(true);
        editEvent.setVisible(true);
        deleteEvent.setVisible(true);
        btnManageUsers.setVisible(false);
    }
}
