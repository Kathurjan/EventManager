package DAL;

import BE.Admin;
import BE.Person;

import java.util.List;

public interface DALInterface {


    void getallusers();

    List<Admin> getallAdmins();

    List<Person> getAllPerson();

    void addPerson(String username, String password, String email);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email);

    public String verifyUserName();




    public String verifyUserPassWord();
}
