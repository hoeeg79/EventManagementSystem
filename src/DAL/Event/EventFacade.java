package DAL.Event;

import BE.Customer;
import BE.Event;
import BE.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * A Facade pattern for the Event package, used to provide a simplified version of the entire package.
 */
public class EventFacade {

    public Event createEvent(String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws Exception {
        CreateEvent ce = new CreateEvent();
        return ce.createEvent(name,date,time,location,participants, VIP, food, frontRow, beer);
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

    public Ticket sellTicketEvent(Event e,Customer c) throws Exception {
        CreateCustomer cC = new CreateCustomer();
        CreateTicket cT = new CreateTicket();
        SellTicketEvent sT = new SellTicketEvent();

        cC.createCustomer(c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhoneNumber());
        sT.sellTicketEvent(e);
        return cT.createTicket(e.getId(), c.getPhoneNumber());
    }
}
