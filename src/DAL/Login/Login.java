package DAL.Login;

import BE.User;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private final DBConnector dbc;

    protected Login() throws Exception {
        dbc = new DBConnector();
    }

    protected User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM User_credentials WHERE username=? AND password=?;";

        try(Connection conn = dbc.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return new User(rs.getString(2),rs.getInt(1),rs.getBoolean(3));
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
