package GUI.Model;

import BE.Event;
import BLL.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Time;

public class EventModel {

    private EventManager eventManager;
    private ObservableList<Event> eventList;


    public EventModel() throws Exception {
        eventManager = new EventManager();

        eventList = FXCollections.observableArrayList();
        eventList.addAll(eventManager.getEvents());
    }



    public void createEvent(String name, Date date, Time time, String location) throws Exception{
        Event e = eventManager.createEvent(name, date, time, location);
        eventList.add(e);
    }

    public void deleteEvent(Event deletedEvent) throws Exception {
        eventManager.deleteEvent(deletedEvent);
        eventList.remove(deletedEvent);
    }

    public ObservableList<Event> getObservableEvents() {
        return eventList;
    }
}
