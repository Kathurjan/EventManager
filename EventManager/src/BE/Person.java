package BE;

public class Person {
    private final int ID;
    private String Username;
    private String Password;
    private String email;
    private int type;




    public Person(int id,String username, String password, String email) {
        this.ID = id;
        Username = username;
        Password = password;
        this.email = email;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
