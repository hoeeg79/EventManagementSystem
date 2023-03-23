package DAL.User;

import BE.User;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    private final DBConnector dbc;

    public DeleteUser() throws Exception {
        this.dbc = new DBConnector();
    }

    protected void deleteUser(User user){
        String sql = "DELETE FROM User_credentials WHERE id = ?;";

        try (Connection conn = dbc.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
