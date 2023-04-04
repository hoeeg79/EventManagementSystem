package DAL.Event;

import BE.Ticket;
import DAL.DatabaseConnector.DBConnector;

import java.sql.*;

public class CreateTicket {
    private DBConnector dbc;

    public CreateTicket() throws Exception{
        dbc = new DBConnector();
    }

    public Ticket createTicket(int eventId, int phoneNumber)throws SQLException {
        String sql = "INSERT INTO Ticket (EventID, PhoneNumber) VALUES (?,?)";
        try (Connection conn = dbc.getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, eventId);
            pstmt.setInt(2, phoneNumber);

            ResultSet rs = pstmt.executeQuery();

            int ticketId = 0;
            if(rs.next()){
                ticketId = rs.getInt(1);
            }
            return new Ticket(ticketId,eventId,phoneNumber);

        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}