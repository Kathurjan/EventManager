package BE;

public class Person {
    private final int ID;
    private String Username;
    private String Password;
    private String email;


    public Person(int id,String username, String password, String email) {
        this.ID = id;
        Username = username;
        Password = password;
        this.email = email;
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
