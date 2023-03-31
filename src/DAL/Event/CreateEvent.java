package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class CreateEvent {

    private DBConnector DBCon;

    protected CreateEvent() throws Exception {
        DBCon = new DBConnector();
    }

    protected Event createEvent(String name, Date date, Time time, String location, int participants) throws SQLException {
        String sql = "INSERT INTO Event (name, date, time, location, participants)VALUES (?,?,?,?,?);";

        try (Connection connection = DBCon.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setTime(3, time);
            stmt.setString(4, location);
            stmt.setInt(5, participants);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            Event event = new Event(id, name, date, time, location, participants);
            return event;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
