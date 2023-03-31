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
    private Event selectedEvent;


    public EventModel() throws Exception {
        eventManager = new EventManager();

        eventList = FXCollections.observableArrayList();
    }

    public void createEvent(String name, Date date, Time time, String location, int participants) throws Exception{
        Event e = eventManager.createEvent(name, date, time, location, participants);
        eventList.add(e);
    }

    public void deleteEvent(Event deletedEvent) throws Exception {
        eventManager.deleteEvent(deletedEvent);
        eventList.remove(deletedEvent);
    }

    public ObservableList<Event> getObservableEvents() throws Exception {
        eventList.clear();
        eventList.addAll(eventManager.getEvents());
        return eventList;
    }

    public void editEvent(int idOfEvent, String name, Date convertedDate, Time startTime, String location, int participants) throws Exception {
        Event e = new Event(idOfEvent, name, convertedDate, startTime, location, participants);
        eventManager.editEvent(e);
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        this.selectedEvent = selectedEvent;
        return selectedEvent;
    }

    public void sellTicketEvent(Event e) throws Exception {
        eventManager.sellTicketEvent(e);
    }

}
