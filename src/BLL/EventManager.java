package BLL;

import BE.Event;
import DAL.CreateEvent;
import DAL.DeleteEvent;

import java.sql.Date;
import java.sql.Time;

public class EventManager {

    private CreateEvent createEvent = new CreateEvent();
    private DeleteEvent deleteEvent = new DeleteEvent();

    public Event createEvent(String name, Date date, Time time, String location) throws Exception{
        return createEvent.createEvent(name, date, time, location);
    }

    public void deleteEvent(Event deletedEvent) throws Exception {
        deleteEvent.deleteEvent(deletedEvent);
    }
}
