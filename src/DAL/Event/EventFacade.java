package DAL.Event;

import BE.Customer;
import BE.Event;
import BE.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventFacade {
    public Event createEvent(String name, Date date, Time time, String location, int participants) throws Exception {
        CreateEvent ce = new CreateEvent();

        return ce.createEvent(name,date,time,location,participants);
    }

    public void deleteEvent(Event deletedEvent) throws Exception{
        DeleteEvent de = new DeleteEvent();

        de.deleteEvent(deletedEvent);
    }

    public List<Event> getEvents() throws Exception {
        GetEvents events = new GetEvents();

        return events.returnEvents();
    }

    public void editEvent(Event e) throws Exception {
        EditEvent editE = new EditEvent();

        editE.editE(e);
    }

    public void sellTicketEvent(Event e) throws Exception {
        SellTicketEvent sellT = new SellTicketEvent();

        sellT.sellTicketEvent(e);
    }

    public Customer createCustomer(String firstName, String lastName, String email, int phoneNumber) throws Exception {
        CreateCustomer cc = new CreateCustomer();

        return cc.createCustomer(firstName, lastName, email, phoneNumber);
    }

    public Ticket createTicket(int eventId, int phoneNumber) throws Exception {
        CreateTicket ct = new CreateTicket();

        return ct.createTicket(eventId, phoneNumber);
    }

}
