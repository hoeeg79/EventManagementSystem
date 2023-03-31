package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditEvent {
    private DBConnector DBCon;

    public EditEvent() throws Exception {
        DBCon = new DBConnector();
    }

    public void editE(Event event) throws Exception {
        try (Connection con = DBCon.getConnection()) {

            String sql = "UPDATE Event SET name=?, date=?, time=?, location=?, participants=? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, event.getName());
            stmt.setDate(2, event.getDate());
            stmt.setTime(3, event.getTime());
            stmt.setString(4, event.getLocation());
            stmt.setInt(5, event.getParticipants());
            stmt.setInt(6, event.getId());

            stmt.executeUpdate();

        } catch (Exception ex) {
            throw new Exception("Could not edit event", ex);
        }
    }
}
