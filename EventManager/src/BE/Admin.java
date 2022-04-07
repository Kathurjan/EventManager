package BE;

// This is the create object for admins.

public class Admin extends Person {

    private String password;

    public Admin(int id, String username, String email, int type, String firstName, String lastName, String password) {
        super(id, username, email, type, lastName, password);
        this.password = password;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    int type;

}
