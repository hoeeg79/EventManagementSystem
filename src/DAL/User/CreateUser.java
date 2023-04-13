package DAL.User;

import BE.User;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class CreateUser {
    private final DBConnector dbc;

    /**
     * Constructor of the CreateUser class, used to instantiate the DBConnector.
     */
    public CreateUser() throws Exception {
        dbc = new DBConnector();
    }

    /**
     * createUser is a method that uses an SQL string to insert specified information into the User_credentials table.
     */
    protected User createUser(String username, String password, boolean isAdmin){
        String sql = "INSERT INTO User_credentials (username, password, admin) VALUES (?,?,?);";

        try (Connection conn = dbc.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.setBoolean(3,isAdmin);

            stmt.executeUpdate();

            int id = 0;

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                id = rs.getInt(1);
            }

            return new User(username, id, isAdmin, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
