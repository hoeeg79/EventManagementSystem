package GUI.Model;

import BE.Customer;
import BE.Event;
import BE.Ticket;
import BLL.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Time;

public class EventModel {

    private EventManager eventManager;
    private ObservableList<Event> eventList;
    private Event selectedEvent;


    /**
     * Constructor of the eventModel class
     */
    public EventModel() {
        eventManager = new EventManager();
        eventList = FXCollections.observableArrayList();
    }

    /**
     * Makes a variable of createEvent, and adds it to the ObservableList<Event>
     */
    public void createEvent(String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws Exception {
        Event e = eventManager.createEvent(name, date, time, location, participants, VIP, food, frontRow, beer);
        eventList.add(e);
    }

    /**
     * Removes an event from the observableList
     */
    public void deleteEvent(Event deletedEvent) throws Exception {
        eventManager.deleteEvent(deletedEvent);
        eventList.remove(deletedEvent);
    }

    /**
     * Clears the observableList and adds all the events that are currently on it.
     */
    public ObservableList<Event> getObservableEvents() throws Exception {
        eventList.clear();
        eventList.addAll(eventManager.getEvents());
        return eventList;
    }

    /**
     * Makes a variable of editEvent, and adds it to the eventManager
     */
    public void editEvent(int idOfEvent, String name, Date convertedDate, Time startTime, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws Exception {
        Event e = new Event(idOfEvent, name, convertedDate, startTime, location, participants, VIP, food, frontRow, beer);
        eventManager.editEvent(e);
    }

    /**
     * a setter for selectedEvent
     */
    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    /**
     * a getter for the selected event
     */
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    /**
     * returns a ticket with an event and a customer, used for sellTicket in the view.
     */
    public Ticket sellTicketEvent(Event e, Customer c) throws Exception {
        return eventManager.sellTicketEvent(e, c);
    }

}
