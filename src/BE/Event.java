package BE;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private int id;
    private String name;
    private Date date;
    private Time time;
    private String location;
    private int participants;
    private boolean VIP;
    private boolean food;
    private boolean frontRow;
    private boolean beer;

    /**
     * This class contains setters and getters used for the Event, which is necessary in other layers of the application
     */

    public Event(int id, String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.participants = participants;
        this.VIP = VIP;
        this.food = food;
        this.frontRow = frontRow;
        this.beer = beer;
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFrontRow() {
        return frontRow;
    }

    public void setFrontRow(boolean frontRow) {
        this.frontRow = frontRow;
    }

    public boolean isBeer() {
        return beer;
    }

    public void setBeer(boolean beer) {
        this.beer = beer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location=" + location + '\'' +
                ", participants=" + participants +
                '}';
    }
}
