package GUI.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExtraTicketViewController extends BaseController{
    @FXML
    private Button btnPrint;
    @FXML
    private ToggleGroup RadioGroup;
    @FXML
    private Button btnCancel;
    @FXML
    private RadioButton rbFreeBeer;
    @FXML
    private RadioButton rb50Off;
    @FXML
    private RadioButton rbFreeEarplugs;

    @Override
    public void setup() throws Exception {

    }


    public void handlePrint(ActionEvent actionEvent) throws Exception {
        Toggle selectedToggle = RadioGroup.getSelectedToggle();
        String toggleString = "";
        if (selectedToggle == rbFreeBeer){
            toggleString = "a free beer";
        } else if (selectedToggle == rb50Off) {
            toggleString = "50% off a drink";
        } else if (selectedToggle == rbFreeEarplugs) {
            toggleString = "free earplugs";
        }


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
        Paragraph title = new Paragraph("This is your bonus ticket to \n" + getModel().getSelectedEvent().getName() + "!", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new com.itextpdf.text.BaseColor(0, 0, 0)); // black
        lineSeparator.setLineWidth(1);
        document.add(lineSeparator);

        Font eventNameFont = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD);
        Paragraph eventName = new Paragraph("This ticket will give you " + toggleString + "!", eventNameFont);
        eventName.setAlignment(Element.ALIGN_CENTER);
        eventName.setSpacingBefore(5);
        document.add(eventName);

        Font personalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        Paragraph details = new Paragraph();
        details.add(new Paragraph("Date of the event: " + String.valueOf(getModel().getSelectedEvent().getDate()) + " at " + getModel().getSelectedEvent().getTime(), personalFont));
        details.add(new Paragraph("This ticket is only valid for this event."));
        document.add(details);

        Barcode128 code128 = new Barcode128();
        code128.setCode(String.valueOf(getModel().getSelectedEvent().getId()));
        code128.setSize(9);
        code128.setX(2);
        code128.setN(60);
        PdfContentByte cb = pdfWriter.getDirectContent();
        Image barcodeImage = code128.createImageWithBarcode(cb, null, null);
        float x = PageSize.A6.rotate().getWidth() / 2 - 30;
        float y = barcodeImage.getScaledHeight() - 13;
        barcodeImage.setAbsolutePosition(x, y);
        document.add(barcodeImage);

        document.close();

    }

    public void handleCancel(ActionEvent actionEvent) {
        closeWindow(btnCancel);
    }
}
