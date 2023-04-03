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

    public Event createEvent(String name, Date date, Time time, String location, int participants) throws Exception {
        return eventFacade.createEvent(name, date, time, location, participants);
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

    public void sellTicketEvent(Event e) throws Exception {
        eventFacade.sellTicketEvent(e);
    }

    public Customer createCustomer (String firstName, String lastName, String email,int phoneNumber) throws Exception {
        return eventFacade.createCustomer(firstName, lastName, email, phoneNumber);
    }

    public Ticket createTicket ( int eventId, int phoneNumber) throws Exception {
        return eventFacade.createTicket(eventId, phoneNumber);
    }
}

