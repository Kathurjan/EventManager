package DAL;

import BE.Person;

import java.util.List;

public interface DALInterface {

    List<Person> getAllPerson();

    void addPerson(String username, String password, String name);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String name);

}
