package BLL;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import DAL.Event.EventFacade;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventManager {


    /**
     * Instantiates the eventFacade
     */
    private EventFacade eventFacade = new EventFacade();

    /**
     * A method used to send the createEvent method through the layers to the GUI
     */
    public Event createEvent(String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws Exception {
        return eventFacade.createEvent(name, date, time, location, participants, VIP, food, frontRow, beer);
    }

    /**
     * A method used to send the deleteEvent method through the layers to the GUI
     */
    public void deleteEvent(Event deletedEvent) throws Exception {
        eventFacade.deleteEvent(deletedEvent);
    }

    /**
     * A method used to send the getEvents method through the layers to the GUI
     */
    public List<Event> getEvents() throws Exception {
        return eventFacade.getEvents();
    }

    /**
     * A method used to send the editEvent method through the layers to the GUI
     */
    public void editEvent(Event e) throws Exception {
        eventFacade.editEvent(e);
    }

    /**
     * A method used to send the sellTicketEvent method through the layers to the GUI
     */
    public Ticket sellTicketEvent(Event e, Customer c) throws Exception {
        return eventFacade.sellTicketEvent(e, c);
    }
}

