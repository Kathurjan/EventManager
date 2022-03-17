package BE;

public class Person {
    private final int ID;
    private String Username;
    private String Password;
    private String name;


    public Person(int id,String username, String password, String name) {
        this.ID = id;
        Username = username;
        Password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
