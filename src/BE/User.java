package BE;

public class User {
    private String username;
    private int id;
    private boolean admin;

    public User(String username, int id, boolean admin) {
        this.username = username;
        this.admin = admin;
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", admin=" + admin +
                '}';
    }
}
