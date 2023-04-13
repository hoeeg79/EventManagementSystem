package DAL.Event;

import BE.Customer;
import DAL.DatabaseConnector.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCustomer {
    private final DBConnector dbc;

    /**
     * Constructor of the CreateCustomer class, used to instantiate the DBConnector.
     */
    public CreateCustomer() throws Exception {
        dbc = new DBConnector();
    }

    /**
     * creatCustomer is a method that first uses an SQL string to count the amount of customers with a specified phoneNumber
     * If the phoneNumber already exists it does nothing. If the phoneNumber does not exist, it runs a new SQL STRING
     * that adds a persons information into our database.
     */
    public Customer createCustomer(String firstName, String lastName, String email, int phoneNumber) {
        // Checks if a customer with the specified phone number exists in our Customer table
        try (Connection conn = dbc.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Customer Where phoneNumber = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, phoneNumber);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = dbc.getConnection()) {
            // If the customer does not exist in the database, it will add the customer.
            String sql = "INSERT INTO Customer (firstName, lastName, email, phoneNumber) VALUES (?,?,?,?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, phoneNumber);

            pstmt.executeUpdate();

            return new Customer(firstName, lastName, email, phoneNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
