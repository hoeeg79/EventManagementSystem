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

    @FXML
    private void handleSaveEvent(ActionEvent actionEvent) throws Exception {
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
    }

    @FXML
    private void handleCancelEvent(ActionEvent actionEvent) {
        closeWindow(cancelEvent);
    }

    private void lockToIntOnly(){
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
    }

    public void setFields(Event event) {
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
    }
    public void notEdit() {
        isEdit = false;
    }
}
