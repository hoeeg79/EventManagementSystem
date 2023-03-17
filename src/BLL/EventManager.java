package BLL;

import BE.Event;
import DAL.CreateEvent;

import java.sql.Date;
import java.sql.Time;

public class EventManager {

    private CreateEvent createEvent = new CreateEvent();

    public Event createEvent(String name, Date date, Time time, String location) throws Exception{
        return createEvent.createEvent(name, date, time, location);
    }
}
