package BLL;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import DAL.Event.EventFacade;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventManager {


    private EventFacade eventFacade = new EventFacade();

    public Event createEvent(String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws Exception {
        return eventFacade.createEvent(name, date, time, location, participants, VIP, food, frontRow, beer);
    }

    public void deleteEvent(Event deletedEvent) throws Exception {
        eventFacade.deleteEvent(deletedEvent);
    }

    public List<Event> getEvents() throws Exception {
        return eventFacade.getEvents();
    }

    public void editEvent(Event e) throws Exception {
        eventFacade.editEvent(e);
    }

    public Ticket sellTicketEvent(Event e, Customer c) throws Exception {
        return eventFacade.sellTicketEvent(e, c);
    }
}

