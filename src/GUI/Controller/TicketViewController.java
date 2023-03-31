package GUI.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class TicketViewController extends BaseController{
    public TextField fxName;
    public TextField fxEmail;
    public Button btnPrint;
    public Button btnCancel;
    public TableColumn clnEvent;
    public TableView tblEvent;
    public TextField fxPhone;

    @Override
    public String toString() {
        return "TicketViewController{" +
                "fxName=" + fxName +
                ", fxEmail=" + fxEmail +
                '}';
    }

    @Override
    public void setup() throws Exception {

        eventList();

    }

    public void handlePrintTicket(ActionEvent actionEvent) throws IOException, DocumentException {
        Document document = new Document(PageSize.A6.rotate());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fxName.getText() + "_Ticket.pdf"));
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
        Paragraph eventName = new Paragraph("Let's meet at the " + getModel().getSelectedEvent().getLocation() + "!",  eventNameFont);
        eventName.setAlignment(Element.ALIGN_CENTER);
        eventName.setSpacingBefore(5);
        document.add(eventName);

        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Paragraph ticketDetails = new Paragraph();
        ticketDetails.add(new Paragraph("This is your information and what you need to know about the event:", normalFont));
        ticketDetails.setSpacingBefore(10);
        ticketDetails.setAlignment(Element.ALIGN_MIDDLE);
        document.add(ticketDetails);

        Time time = new Time(14, 30, 0); // Replace with your Time object
        Date date = new Date(time.getTime()); // Convert Time to Date
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // Format for hours and minutes
        String formattedTime = formatter.format(date); // Convert date to string

        Font personalFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);
        Paragraph personalDetails = new Paragraph();
        personalDetails.add(new Paragraph(fxName.getText(), personalFont));
        personalDetails.add(new Paragraph(fxEmail.getText(), personalFont));
        personalDetails.add(new Paragraph(fxPhone.getText(), personalFont));
        personalDetails.add(new Paragraph(getModel().getSelectedEvent().getLocation(), personalFont));
        personalDetails.add(new Paragraph(String.valueOf(getModel().getSelectedEvent().getDate()) + " at " + getModel().getSelectedEvent().getTime(), personalFont));
        personalDetails.setSpacingBefore(2);
        document.add(personalDetails);

        Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
        Paragraph descriptionDetails = new Paragraph();
        descriptionDetails.add(new Paragraph("Description: On the backside of this ticket, you will find information about parking.", descriptionFont));
        descriptionDetails.setSpacingBefore(20);
        document.add(descriptionDetails);

        /*Random random = new Random();
        int codeNumber = random.nextInt(1000000);
        String codeString = Integer.toString(codeNumber);*/

        Barcode128 code128 = new Barcode128();
        code128.setCode(String.valueOf(getModel().getSelectedEvent().getId()));
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

        document.close();
        System.out.println("Ticket generated successfully");
    }

    public void handleCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }

    public void eventList(){
        try {
            clnEvent.setCellValueFactory(new PropertyValueFactory<>("event"));

            tblEvent.getColumns().addAll();
            //tblEvent.setItems();
        }catch (Exception e){
            displayError(e);
            e.printStackTrace();
        }

    }


}
