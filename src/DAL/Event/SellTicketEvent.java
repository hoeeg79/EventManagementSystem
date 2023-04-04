package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SellTicketEvent {
    public DBConnector dbc;
    /**
     * Constructor of the SellTicketEvent class, used to instantiate the DBConnector.
     */
    public SellTicketEvent() throws Exception {
        dbc = new DBConnector();
    }

    /**
     * sellTicketEvent is a method that uses an SQL string to update amount of participants on a specified id.
     * Decreasing it with 1, every time a ticket is sold
     * @param event
     */
    public void sellTicketEvent(Event event) {
        try (Connection con = dbc.getConnection()){
            String sql = "UPDATE Event SET participants=? WHERE id=?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            int newParticipants = event.getParticipants();
            newParticipants--;

            stmt.setInt(1, newParticipants);
            stmt.setInt(2, event.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
