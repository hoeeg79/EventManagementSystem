package BE;

public class User {
    private String username;
    private int id;
    private boolean admin;
    private String password;

    /**
     * This class contains setters and getters used for the User, which is necessary in other layers of the application
     */

    public User(String username, int id, boolean admin, String password) {
        this.username = username;
        this.admin = admin;
        this.id = id;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", admin=" + admin +
                '}';
    }
}
