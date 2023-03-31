package BE;

import java.sql.Date;
import java.sql.Time;

public class Ticket{
    private int ticketId;

    private boolean isScanned;
    private int eventId;
    private int phoneNumber;



    public Ticket(int ticketId, int eventId, int phoneNumber) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.phoneNumber = phoneNumber;
        isScanned = false;

    }

    public boolean getScanned() {
        return isScanned;
    }

    public void setScanned(boolean isScanned) {
        this.isScanned = isScanned;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}



// QR code - id of the ticket

