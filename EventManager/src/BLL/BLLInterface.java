package BLL;

import BE.Admin;
import BE.Person;

import java.util.List;

public interface BLLInterface {



    List<Person> getAllPerson();

    void addPerson(String username, String password, String email);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email);



    public Admin verifyadmin(String username,String password);
}
