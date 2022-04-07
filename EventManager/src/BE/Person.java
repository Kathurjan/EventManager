package BE;

public class Person {
    private final int ID;
    private String Username;
    private String email;
    private int type;
    private String firstName;
    private String lastName;

// This is the create object for person's.

    public Person(int id, String username, String email, int type, String firstName, String lastName) {
        this.ID = id;
        Username = username;
        this.email = email;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getID(){
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
