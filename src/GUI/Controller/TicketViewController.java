package GUI.Controller;

import BE.Customer;
import BE.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;

public class TicketViewController extends BaseController{
    public TextField txtLastName;
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

        /*Image image = Image.getInstance("resources/—Pngtree—3 golden stars_4523648.png");
        image.scaleAbsolute(PageSize.A6.rotate().getWidth() - image.getScaledWidth() - 10, PageSize.A6.rotate().getHeight() - image.getScaledHeight() - 10);
        document.add(image);*/

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
        personalDetails.add(new Paragraph(getModel().getSelectedEvent().getLocation(), personalFont));
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

}
