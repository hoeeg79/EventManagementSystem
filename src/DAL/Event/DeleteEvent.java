package DAL.Event;

import BE.Event;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteEvent {

    private DBConnector DBCon;

    protected DeleteEvent() throws Exception {
        DBCon = new DBConnector();
    }

    protected void deleteEvent(Event event) throws Exception{
        try(Connection conn = DBCon.getConnection()){

            String sql = "DELETE FROM Event WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, event.getId());

            stmt.executeUpdate();
        }catch (Exception e){
            throw new Exception(e);
        }

    }
}
