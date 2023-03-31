package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GetEvents {

    private DBConnector DBCon;

    public GetEvents() throws Exception {
        DBCon = new DBConnector();
    }

    public List<Event> returnEvents() throws Exception{
        //Make a list called allMovies, to store movies in, and return in the end
        ArrayList<Event> allEvents = new ArrayList<>();

        //Try with resources to connect to DB
        try (Connection conn = DBCon.getConnection()){

            //SQL string, selects all movies from DB
            String sql = "SELECT * FROM Event;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //Loop through rows from database result set
            while (rs.next()){
                //Map DB row to Movie object
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date date = rs.getDate("date");
                Time startTime = rs.getTime("time");
                int participants = rs.getInt("participants");

                //Create Movie and add to list created in the beginning
                Event event = new Event(id,name,date,startTime,location,participants);
                allEvents.add(event);
            }
        } catch (Exception e){
            throw new Exception("Could not get Events from database", e);
        }
        return allEvents;
    }
}
