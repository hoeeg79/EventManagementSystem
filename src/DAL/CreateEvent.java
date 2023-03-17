package DAL;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class CreateEvent {

    private DBConnector DBCon;
    public Event createEvent(String name, Date date, Time time, String location) throws SQLException {
        String sql = "INSERT INTO Event (name, date, time, location)VALUES (?,?,?,?);";

        try (Connection connection = DBCon.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setTime(3, time);
            stmt.setString(4, location);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            Event event = new Event(name, date, time, location);
            return event;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
