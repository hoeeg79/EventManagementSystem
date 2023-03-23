package GUI.Controller;

import BE.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;


public class CreateEventViewController extends BaseController {
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

    int hours = Integer.parseInt(txtHours.getText());
    int minutes = Integer.parseInt(txtMinutes.getText());
    Time startTime = new Time(hours,minutes,0);

    if (isEdit) {
        super.getModel().editEvent(idOfEvent, name, convertedDate, startTime,location);
        getModel().getObservableEvents();
    } else {
        super.getModel().createEvent(name, convertedDate, startTime, location);
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
        selectedDate.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        idOfEvent = event.getId();
        isEdit = true;
    }
}
