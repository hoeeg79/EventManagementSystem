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

            pstmt.executeUpdate();
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




//        try(Connection conn = dbc.getConnection()){
//            String sql = "SELECT e.name, e.date, e.time, e.location, c.FirstName, c.LastName, c.Email, t.TicketID " +
//                    "FROM Event e " +
//                    "INNER JOIN Ticket t ON e.id = t.id " +
//                    "INNER JOIN Customer c ON t.CustomerID = c.CustomerID";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            while(rs.next()){
//                String eventName = rs.getString("name");
//                Date eventDate = rs.getDate("date");
//                Time eventTime = rs.getTime("time");
//                String eventLocation = rs.getString("location");
//                String customerFirstName = rs.getString("FirstName");
//                String customerLastName = rs.getString("LastName");
//                String customerEmail = rs.getString("Email");
//                int ticketID = rs.getInt("TicketID");
//
//                Tickets _ticket = new Tickets(ticketID, eventName, eventDate, eventTime, eventLocation);
//                return _ticket;
//            }
//
//        }catch (SQLException e){
//            throw new SQLException();
//        }
