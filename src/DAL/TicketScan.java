package DAL;


import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketScan {
    private final DBConnector dbc;

    public TicketScan() throws Exception{
        dbc = new DBConnector();
    }

    public void scanTicket(int ticketID){
        String sql = "UPDATE Tickets SET Scanned = 1 WHERE TicketID = ?";

        try(Connection conn = dbc.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ticketID);

            int ticketUpdated = stmt.executeUpdate();

            if(ticketUpdated > 0){
                System.out.println("Ticket has been scanned!");
            } else {
                System.out.println("Ticket invalid!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
