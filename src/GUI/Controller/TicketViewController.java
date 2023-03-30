package GUI.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TicketViewController extends BaseController{
    public TextField fxName;
    public TextField fxEmail;
    public Button btnPrint;
    public Button btnCancel;
    public TableColumn clnEvent;
    public TableView tblEvent;

    @Override
    public void setup() throws Exception {

        eventList();

    }

    public void handlePrintTicket(ActionEvent actionEvent) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A6.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(fxName.getText() + "_Ticket.pdf"));
        document.open();

        Rectangle background = new Rectangle(0, 0, PageSize.A6.rotate().getWidth(), PageSize.A6.rotate().getHeight());
        background.setBackgroundColor(new com.itextpdf.text.BaseColor(240, 230, 199));
        document.add(background);

        Rectangle border = new Rectangle(10, 10, PageSize.A6.rotate().getWidth() - 10, PageSize.A6.rotate().getHeight() - 10);
        border.setBorder(Rectangle.BOX);
        border.setBorderWidth(2);
        border.setBorderColor(new com.itextpdf.text.BaseColor(0, 0, 0)); // black
        document.add(border);

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph(getModel().getSelectedEvent().getName() + "!!!", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new com.itextpdf.text.BaseColor(0, 0, 0)); // black
        lineSeparator.setLineWidth(1);
        document.add(lineSeparator);

        Font eventNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD);
        Paragraph eventName = new Paragraph("Let's meet at the: " + getModel().getSelectedEvent().getLocation() + "!",  eventNameFont);
        eventName.setAlignment(Element.ALIGN_CENTER);
        eventName.setSpacingBefore(5);
        document.add(eventName);

        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Paragraph ticketDetails = new Paragraph();
        ticketDetails.add(new Paragraph(getModel().getSelectedEvent().getLocation() + " / " + getModel().getSelectedEvent().getTime(), normalFont));
        ticketDetails.add(new Paragraph(String.valueOf(getModel().getSelectedEvent().getId()), normalFont)); //Gonna be a barcode someday
        ticketDetails.add(new Paragraph("Description: This is a test ticket", normalFont));
        document.add(ticketDetails);

        document.close();
        System.out.println("Ticket generated successfully");
    }

    public void handleCancel(ActionEvent actionEvent) {
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
