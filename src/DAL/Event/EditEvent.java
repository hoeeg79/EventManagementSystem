package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditEvent {
    private DBConnector DBCon;

    /**
     * Constructor of the EditEvent class, used to instantiate the DBConnector.
     */
    public EditEvent() throws Exception {
        DBCon = new DBConnector();
    }

    /**
     * editE is a method that uses an SQL string to update an event with new specifications.
     * @param event
     * @throws Exception
     */
    public void editE(Event event) throws Exception {
        try (Connection con = DBCon.getConnection()) {

            String sql = "UPDATE Event SET name=?, date=?, time=?, location=?, participants=?, VIP = ?, food = ?, frontRow = ?, beer = ? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, event.getName());
            stmt.setDate(2, event.getDate());
            stmt.setTime(3, event.getTime());
            stmt.setString(4, event.getLocation());
            stmt.setInt(5, event.getParticipants());
            stmt.setBoolean(6, event.isVIP());
            stmt.setBoolean(7, event.isFood());
            stmt.setBoolean(8, event.isFrontRow());
            stmt.setBoolean(9, event.isBeer());
            stmt.setInt(10, event.getId());

            stmt.executeUpdate();

        } catch (Exception ex) {
            throw new Exception("Could not edit event", ex);
        }
    }
}
