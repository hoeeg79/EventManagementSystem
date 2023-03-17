package GUI.Model;

import BE.Event;
import BLL.EventManager;

import java.sql.Date;
import java.sql.Time;

public class EventModel {

    private EventManager eventManager;

    public EventModel(){
        eventManager = new EventManager();

    }



    public void createEvent(String name, Date date, Time time, String location) throws Exception{
        Event e = eventManager.createEvent(name, date, time, location);


    }

}
