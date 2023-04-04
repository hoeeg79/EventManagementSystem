package GUI.Controller;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketViewController extends BaseController{

    @FXML
    private CheckBox cbVIP;
    @FXML
    private CheckBox cbFood;
    @FXML
    private CheckBox cbFrontRow;
    @FXML
    private CheckBox cbFreeBeer;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtPhone;


    @Override
    public void setup() throws Exception {
        setExtra();

        addAlphabeticListener(txtFirstName);
        addAlphabeticListener(txtLastName);

        TextField textField2 = txtPhone;
        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField2.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 8) {
                textField2.setText(newValue.substring(0, 8));
            }
        });

        TextField textField3 = txtEmail;
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);
        UnaryOperator<TextFormatter.Change> emailFilter = c -> {
            String newText = c.getControlNewText();
            if (emailPattern.matcher(newText).matches()) {
                return c;
            } else {
                return null;
            }
        };
        textField3.setTextFormatter(new TextFormatter<>(emailFilter));
        textField3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                textField3.setText(newValue.substring(0, 50));
            }
            if (emailPattern.matcher(newValue).matches()) {
                textField3.setStyle("");
            } else {
                textField3.setStyle("-fx-border-color: red;");
            }
        });
    }

    private void addAlphabeticListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                textField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
            if (newValue.length() > 15) {
                textField.setText(newValue.substring(0, 15));
            }
        });
    }


    @FXML
    private void handlePrintTicket(ActionEvent actionEvent) throws Exception {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        int phone = Integer.parseInt(txtPhone.getText());

        Customer cus = new Customer(firstName, lastName, email, phone);
        //-1 participants
        Ticket t = getModel().sellTicketEvent(getModel().getSelectedEvent(),cus);

        FileChooser fileChooser = new FileChooser();
        File fileToSave = fileChooser.showSaveDialog(btnPrint.getScene().getWindow());
        Document document = new Document(PageSize.A6.rotate());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileToSave.getAbsoluteFile()));
        document.open();

        Rectangle background = new Rectangle(0, 0, PageSize.A6.rotate().getWidth(), PageSize.A6.rotate().getHeight());
        background.setBackgroundColor(new com.itextpdf.text.BaseColor(240, 230, 199));
        document.add(background);

        Rectangle border = new Rectangle(10, 10, PageSize.A6.rotate().getWidth() - 10, PageSize.A6.rotate().getHeight() - 10);
        border.setBorder(Rectangle.BOX);
        border.setBorderWidth(2);
        border.setBorderColor(new com.itextpdf.text.BaseColor(0, 0, 0)); // black
        document.add(border);

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
        Paragraph title = new Paragraph("This is your ticket to " + getModel().getSelectedEvent().getName() + "!", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new com.itextpdf.text.BaseColor(0, 0, 0)); // black
        lineSeparator.setLineWidth(1);
        document.add(lineSeparator);

        Font eventNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD);
        Paragraph eventName = new Paragraph("Let's meet at the " + getModel().getSelectedEvent().getLocation() + "!", eventNameFont);
        eventName.setAlignment(Element.ALIGN_CENTER);
        eventName.setSpacingBefore(5);
        document.add(eventName);

        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Paragraph ticketDetails = new Paragraph();
        ticketDetails.add(new Paragraph("This is your information and what you need to know about the event:", normalFont));
        ticketDetails.setSpacingBefore(10);
        ticketDetails.setAlignment(Element.ALIGN_MIDDLE);
        document.add(ticketDetails);

        Font personalFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);
        Paragraph personalDetails = new Paragraph();
        personalDetails.add(new Paragraph(txtFirstName.getText() + " " + txtLastName.getText(), personalFont));
        personalDetails.add(new Paragraph(txtEmail.getText(), personalFont));
        personalDetails.add(new Paragraph(txtPhone.getText(), personalFont));
        personalDetails.add(new Paragraph(cbString(),  personalFont));
        personalDetails.add(new Paragraph(String.valueOf(getModel().getSelectedEvent().getDate()) + " at " + getModel().getSelectedEvent().getTime(), personalFont));
        personalDetails.setSpacingBefore(2);
        document.add(personalDetails);

        Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 7);
        Paragraph descriptionDetails = new Paragraph();
        descriptionDetails.add(new Paragraph("Description: On the backside of this ticket, you will find a map of the parkingspace.", descriptionFont));
        descriptionDetails.add(new Paragraph("Erhvervsakademi Sydvest, Spangsbjerg Kirkevej 103, 6700 Esbjerg", descriptionFont));
        descriptionDetails.add(new Paragraph("55.488626, 8.445813", descriptionFont));
        descriptionDetails.setSpacingBefore(9);
        document.add(descriptionDetails);
        //getModel().getSelectedEvent().getId()))
        Barcode128 code128 = new Barcode128();
        code128.setCode(String.valueOf(t.getTicketId()));
        code128.setSize(9);
        code128.setX(2);
        code128.setN(60);
        PdfContentByte cb = pdfWriter.getDirectContent();
        Image barcodeImage = code128.createImageWithBarcode(cb, null, null);
        float x = PageSize.A6.rotate().getWidth() - barcodeImage.getScaledHeight() - 15;
        float y = barcodeImage.getScaledHeight() + 10;
        barcodeImage.setAbsolutePosition(x, y);
        barcodeImage.setRotationDegrees(90);
        document.add(barcodeImage);

        document.setMargins(0, 0, -9, 0);
        document.newPage();
        Image image = Image.getInstance("resources/maps3.png");
        float scaleFactor = Math.max(document.getPageSize().getWidth() / image.getWidth(), document.getPageSize().getHeight() / image.getHeight());
        image.scaleAbsolute(image.getWidth() * scaleFactor, image.getHeight() * scaleFactor);
        document.add(image);


        document.close();
        System.out.println("Ticket generated successfully");

    }

    @FXML
    private void handleClose(ActionEvent actionEvent) {
        closeWindow(btnClose);
    }

    private void setExtra(){
        Event e = getModel().getSelectedEvent();
        if(e.isVIP()){
            cbVIP.setVisible(true);
        }else {
            cbVIP.setVisible(false);
        }

        if(e.isBeer()){
            cbFreeBeer.setVisible(true);
        }else{
            cbFreeBeer.setVisible(false);
        }

        if(e.isFood()){
            cbFood.setVisible(true);
        }else{
            cbFood.setVisible(false);
        }

        if(e.isFrontRow()){
            cbFrontRow.setVisible(true);
        }else{
            cbFrontRow.setVisible(false);
        }
    }

    private String cbString(){

        String s = "This ticket includes:" ;
        if(cbVIP.isSelected()){
            s = s + " vip";
        }
        if(cbFood.isSelected()){
            s = s + " free food";
        }
        if(cbFreeBeer.isSelected()){
            s = s + " free beer";
        }
        if(cbFrontRow.isSelected()){
            s = s + " front row seats";
        }
        if(s.equals("This ticket includes:")){
            s = "";
        }
        return s;
    }

}
