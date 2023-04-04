package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class CreateEvent {

    private DBConnector DBCon;

    /**
     * Constructor of the CreateEvent class, used to instantiate the DBConnector.
     */
    protected CreateEvent() throws Exception {
        DBCon = new DBConnector();
    }

    /**
     * createEvent is a method which uses an SQL string to insert information required on an event, into our Event table, to create a new event.
     */
    protected Event createEvent(String name, Date date, Time time, String location, int participants, boolean VIP, boolean food, boolean frontRow, boolean beer) throws SQLException {
        String sql = "INSERT INTO Event (name, date, time, location, participants, VIP, food, frontRow, beer)VALUES (?,?,?,?,?,?,?,?,?);";

        try (Connection connection = DBCon.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setTime(3, time);
            stmt.setString(4, location);
            stmt.setInt(5, participants);
            stmt.setBoolean(6, VIP);
            stmt.setBoolean(7, food);
            stmt.setBoolean(8, frontRow);
            stmt.setBoolean(9, beer);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            Event event = new Event(id, name, date, time, location, participants, VIP, food, frontRow, beer);
            return event;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
