package GUI.Controller;

import BE.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class CreateEventViewController extends BaseController {
    @FXML
    private CheckBox cbFood;
    @FXML
    private CheckBox cbFrontRow;
    @FXML
    private CheckBox cbBeer;
    @FXML
    private CheckBox cbVIP;
    @FXML
    private TextField txtMinutes;
    @FXML
    private DatePicker selectedDate;
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
    private TextField txtHours;
    private boolean isEdit;
    private int idOfEvent;

    @Override
    public void setup() {
        lockToIntOnly();
    }

    /**
     * A method used to save an event in the event management system.
     */
    @FXML
    private void handleSaveEvent(ActionEvent actionEvent) throws Exception {
        try{
            String name = txtNameOfEvent.getText();
            LocalDate unconvertedDate = selectedDate.getValue();
            Date convertedDate = Date.valueOf(unconvertedDate);
            String location = txtLocation.getText();
            int participants = Integer.parseInt(txtNumberOfParticipants.getText());
            int hours = Integer.parseInt(txtHours.getText());
            int minutes = Integer.parseInt(txtMinutes.getText());
            Time startTime = new Time(hours,minutes,0);
            boolean VIP = cbVIP.isSelected();
            boolean food = cbFood.isSelected();
            boolean frontRow = cbFrontRow.isSelected();
            boolean beer = cbBeer.isSelected();

            if (isEdit) {
                super.getModel().editEvent(idOfEvent, name, convertedDate, startTime, location, participants, VIP, food, frontRow, beer);
                getModel().getObservableEvents();
            } else {
                super.getModel().createEvent(name, convertedDate, startTime, location, participants, VIP, food, frontRow, beer);
            }
            closeWindow(saveEvent);
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * A method used to close the event window, in case you do not want to save your current event.
     * @param actionEvent
     */
    @FXML
    private void handleCancelEvent(ActionEvent actionEvent) {
        try{
        closeWindow(cancelEvent);
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * TextFormatter that makes us able to only type in int values in time, when creating an event.
     */
    private void lockToIntOnly(){
        try{
        txtHours.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, c -> {
            if (c.getControlNewText().matches("\\d*") && c.getControlNewText().length() <= 2) {
                return c;
            } else {
                return null;
            }
        }));
        txtMinutes.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, c -> {
            if (c.getControlNewText().matches("\\d*") && c.getControlNewText().length() <= 2) {
                return c;
            } else {
                return null;
            }
        }));
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Sets the event information.
     */
    public void setFields(Event event) {
        try{
        txtNameOfEvent.setText(event.getName());
        txtHours.setText(String.valueOf(event.getTime().getHours()));
        txtMinutes.setText(String.valueOf(event.getTime().getMinutes()));
        txtLocation.setText(event.getLocation());
        txtNumberOfParticipants.setText(String.valueOf(event.getParticipants()));
        selectedDate.setValue(event.getDate().toLocalDate());
        idOfEvent = event.getId();
        isEdit = true;
        cbVIP.setSelected(event.isVIP());
        cbBeer.setSelected(event.isBeer());
        cbFood.setSelected(event.isFood());
        cbFrontRow.setSelected(event.isFrontRow());
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }

    /**
     * Sets the isEdit boolean to false
     */
    public void notEdit() {
        try{
        isEdit = false;
        } catch(Exception e){
            displayError(e);
            e.printStackTrace();
        }
    }
}
