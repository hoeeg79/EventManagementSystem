package GUI.Controller;

import BE.Event;
import BE.User;
import GUI.Model.EventModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainViewController extends BaseController {
    public VBox vbButtons;
    @FXML
    private Button btnExtraTicket;
    @FXML
    private TableColumn clnParticipants;
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
    private boolean itemSelected;

    @Override
    public void setup() {
        try{
        eventModel = super.getModel();
        fillEventList();
        if (user.isAdmin()){
            enableAdmin();
        } else {
            enableCoordinator();
        }
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }

    }

    public void setUser(User user){
        this.user = user;
    }

    /**
     * Opens the CreateEventView, to create a new event.
     */
    @FXML
    private void handleCreateEvent(ActionEvent actionEvent) {
        try{
        openEventView(actionEvent, "Create an Event", false);
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Deletes the selected event
     */
    @FXML
    private void handleDeleteEvent(ActionEvent actionEvent){
        if(itemSelected) {
            try {
                Event deletedEvent = eventBordet.getSelectionModel().getSelectedItem();
                eventModel.deleteEvent(deletedEvent);
            } catch (Exception e) {
                displayError(e);
            }
        }
    }

    /**
     * Opens the TicketView, where you can sell tickets from
     */
    @FXML
    private void handleSellTickets(ActionEvent actionEvent) {
        try{
        Event selectedEvent = eventBordet.getSelectionModel().getSelectedItem();
        if (selectedEvent != null && selectedEvent.getParticipants() > 0) {

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
            fillEventList();
        } else {
            participantWarning();
        }
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * A warning about reaching the max limit of participants.
     */
    private void participantWarning(){
        try{
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No tickets left!");
        alert.setHeaderText("This event have hit its limit of available tickets.");
        alert.showAndWait();
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * A button used to open the event view and edit events.
     */
    @FXML
    private void handleEditEvent(ActionEvent actionEvent) {
        try{
        openEventView(actionEvent, "Edit Event", true);
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
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
            clnParticipants.setCellValueFactory(new PropertyValueFactory<>("participants"));


            //Add the Movies to the list
            eventBordet.getColumns().addAll();
            eventBordet.setItems(eventModel.getObservableEvents());
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * a method used to open the CreateEventView
     */
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

    /**
     * Gets the chosen event.
     */
    private Event getChosenEvent() {
            int id = eventBordet.getSelectionModel().getSelectedItem().getId();
            String name = eventBordet.getSelectionModel().getSelectedItem().getName();
            Date date = eventBordet.getSelectionModel().getSelectedItem().getDate();
            Time time = eventBordet.getSelectionModel().getSelectedItem().getTime();
            String location = eventBordet.getSelectionModel().getSelectedItem().getLocation();
            int participants = eventBordet.getSelectionModel().getSelectedItem().getParticipants();
            boolean VIP = eventBordet.getSelectionModel().getSelectedItem().isVIP();
            boolean food = eventBordet.getSelectionModel().getSelectedItem().isFood();
            boolean frontRow = eventBordet.getSelectionModel().getSelectedItem().isFrontRow();
            boolean beer = eventBordet.getSelectionModel().getSelectedItem().isBeer();

            return new Event(id, name, date, time, location, participants, VIP, food, frontRow, beer);
        }

    /**
     * Opens the users view, to add or delete users
     */
    @FXML
    private void handleManageUsers(ActionEvent actionEvent) {
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

    /**
     * Disables buttons the admin user does not need.
     */
    private void enableAdmin(){
        try{
        createEvent.setVisible(false);
        sellTickets.setVisible(false);
        editEvent.setVisible(false);
        deleteEvent.setVisible(true);
        btnManageUsers.setVisible(true);
        btnExtraTicket.setVisible(false);

        Comparator<Node> byVisibility = (Node b1, Node b2) -> Boolean.compare(b2.isVisible(), b1.isVisible());

        FXCollections.sort(vbButtons.getChildren(), byVisibility);
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Disables buttons the coordinator does not need.
     */
    private void enableCoordinator(){
        try{
        createEvent.setVisible(true);
        sellTickets.setVisible(true);
        editEvent.setVisible(true);
        deleteEvent.setVisible(true);
        btnManageUsers.setVisible(false);
        btnExtraTicket.setVisible(true);
        eventListListener();
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Logs the admin or coordinator out, so you can log onto a different account
     */
    @FXML
    private void handleLogout(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) btnManageUsers.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/View/LoginView.fxml"));

            stage.setScene(new Scene(root));
            stage.setTitle("Event System");
            stage.show();
        } catch (Exception e) {
            displayError(e);
        }
    }
    /**
     * Opens the extra ticket view
     */
    @FXML
    private void handleExtraTicket(ActionEvent actionEvent) throws Exception {
        try{
        Event selectedEvent = eventBordet.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {

            eventModel.setSelectedEvent(selectedEvent);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/ExtraTicketView.fxml"));
            Parent root = loader.load();

            ExtraTicketViewController controller = loader.getController();
            controller.setModel(super.getModel());
            controller.setup();

            stage.setScene(new Scene(root));
            stage.setTitle("Extra Ticket");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();

        }
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    private void eventListListener() {

        editEvent.setDisable(true);
        sellTickets.setDisable(true);
        btnExtraTicket.setDisable(true);
        eventBordet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                if (newValue != null) {
                    sellTickets.setDisable(false);
                    editEvent.setDisable(false);
                    btnExtraTicket.setDisable(false);
                    itemSelected = true;

                } else {
                    sellTickets.setDisable(true);
                    editEvent.setDisable(true);
                    btnExtraTicket.setDisable(true);
                    itemSelected = false;
                }
            }
        });
    }
}
