package BE;

public class Admin {
    private String Username;
    private String Password;
    private String name;


    public Admin(String username, String password, String name) {
        Username = username;
        Password = password;
        this.name = name;
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
