package BLL;

import BE.Admin;
import BE.Person;

import java.util.List;

public interface BLLInterface {

    List getAdmins();

    List<Person> getAllPerson();

    void addPerson(String username, String password, String name);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String name);

   public String verifyUserName();




    public String verifyUserPassWord();
}
