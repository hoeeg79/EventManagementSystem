package DAL.User;

import BE.User;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    private final DBConnector dbc;

    /**
     * Constructor of the DeleteUser class, used to instantiate the DBConnector.
     */
    public DeleteUser() throws Exception {
        this.dbc = new DBConnector();
    }

    /**
     * deleteUser is a method that uses an SQL string to delete specified id from the User_credentials table.
     *
     * @param user
     */
    protected void deleteUser(User user) {
        String sql = "DELETE FROM User_credentials WHERE id = ?;";

        try (Connection conn = dbc.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
