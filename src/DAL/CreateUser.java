package DAL;

import BE.User;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class CreateUser {
    private final DBConnector dbc;

    public CreateUser() throws Exception {
        dbc = new DBConnector();
    }

    public User createUser(String username, String password, boolean isAdmin){
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

            return new User(username, id, isAdmin);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
