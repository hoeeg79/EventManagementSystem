package GUI.Controller;

import GUI.Model.EventModel;
import GUI.Model.UserModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class BaseController {

    private EventModel model;
    private UserModel uModel;
    public abstract void setup() throws Exception;

    public void setModel(EventModel model) {
        this.model = model;
    }

    public EventModel getModel() {
        return model;
    }

    public void setUModel(UserModel model){
        this.uModel = uModel;
    }

    public UserModel getUModel(){
        return uModel;
    }

    /**
     * Method used to display errors in the other controller classes
     * @param t - the error that have been caught
     */
    public void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    /**
     * Method called in other controller classes, used to close windows in case of cancel button used.
     * @param btn - the button pressed in the method.
     */
    public void closeWindow(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
