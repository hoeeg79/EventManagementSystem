package DAL.Event;

import BE.User;
import DAL.DatabaseConnector.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetUsers {

    private DBConnector DBCon;

    public GetUsers() throws Exception {
        DBCon = new DBConnector();
    }

    public List<User> returnUsers() throws Exception{

        ArrayList<User> allUsers = new ArrayList<>();

        try(Connection conn = DBCon.getConnection()){

            String sql = "SELECT * FROM User_credentials;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){

                String username = rs.getString("username");
                int id = rs.getInt("id");
                Boolean admin = rs.getBoolean("admin");

                User users = new User(username, id, admin);
                allUsers.add(users);}
            }catch(Exception e){
                throw new Exception("Could not get Users from database", e);
            }
        return allUsers;
    }
}
