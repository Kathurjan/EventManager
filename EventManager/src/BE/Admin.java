package BE;

// This is the create object for admins.

public class Admin extends Person {
    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    int type;

    public Admin(int id, String username, String password, String email, int type) {
        super(id, username, password, email, type);
    }

}
