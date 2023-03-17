package BE;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private int id;
    private String name;
    private Date date;
    private Time time;
    private String location;

    public Event(int id, String name, Date date, Time time, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
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
}
