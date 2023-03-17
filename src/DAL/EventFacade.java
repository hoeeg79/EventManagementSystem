package DAL;

import BE.Event;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class EventFacade {
    private Event createEvent(String name, Date date, Time time, String location) throws SQLException {
        CreateEvent ce = new CreateEvent();

        return ce.createEvent(name,date,time,location);
    }
}
