package DAL;

import BE.User;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private final DBConnector dbc;

    public Login() throws Exception {
        dbc = new DBConnector();
    }

    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM User_credentials WHERE username=? AND password=?;";

        try(Connection conn = dbc.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return new User(rs.getString(1),rs.getBoolean(3));
            } else {
                System.out.println("Invalid username or password.");
            }

        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }
}
