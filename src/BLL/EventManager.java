package BLL;

import BE.Event;
import DAL.Event.EventFacade;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventManager {


    private EventFacade eventFacade = new EventFacade();

    public Event createEvent(String name, Date date, Time time, String location) throws Exception{
        return eventFacade.createEvent(name, date, time, location);
    }

    public void deleteEvent(Event deletedEvent) throws Exception {
        eventFacade.deleteEvent(deletedEvent);
    }

    public List<Event> getEvents() throws Exception{
        return eventFacade.getEvents();
    }

    public void editEvent(Event e) throws Exception {
        eventFacade.editEvent(e);
    }
}
