package finalTasks.books.auth;

/**
 * Immutable class for user
 */

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final boolean admin;
    private final String password;

    public User(int id, String name, String email, boolean admin, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }
}
