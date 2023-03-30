package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class TicketView extends BaseController {
    public TableColumn fxEventColumn;
    public TextField fxName;
    public TextField fxEmail;
    public Button btnPrint;
    public Button btnCancel;

    public void handlePrintTicket(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    @Override
    public void setup() throws Exception {

    }
}
