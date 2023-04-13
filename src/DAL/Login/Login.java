package DAL.Login;

import BE.User;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private final DBConnector dbc;

    /**
     * Constructor of the Login class, used to instantiate the DBConnector.
     */
    protected Login() throws Exception {
        dbc = new DBConnector();
    }

    /**
     * login is a method that uses and SQL string to select all from the User_credentials table where
     * username is what's specified, and password is what is specified
     * its functionality is to log onto the application
     */
    protected User login(String username) throws SQLException {
        String sql = "SELECT * FROM User_credentials WHERE username=?;";

        try (Connection conn = dbc.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return new User(rs.getString(2), rs.getInt(1),
                        rs.getBoolean("admin"), rs.getString(3));
            } else {
                System.out.println("Invalid username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return null;
    }
}
