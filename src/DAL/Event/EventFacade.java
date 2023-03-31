package DAL.Event;

import BE.Event;
import DAL.User.GetEvents;

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
}
