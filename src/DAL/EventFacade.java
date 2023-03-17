package DAL;

import BE.Event;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class EventFacade {
    public Event createEvent(String name, Date date, Time time, String location) throws Exception {
        CreateEvent ce = new CreateEvent();

        return ce.createEvent(name,date,time,location);
    }

    public void deleteEvent(Event deletedEvent) throws Exception{
        DeleteEvent de = new DeleteEvent();

        de.deleteEvent(deletedEvent);
    }
}
