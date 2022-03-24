package BLL;

import BE.Admin;
import BE.Person;

import java.util.Date;
import java.util.List;

public interface BLLFacade {

    List<Person> getAllPerson();

    void addPerson(String username, String password, String email, int type);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email);

    Admin verifyadmin(String username,String password, int type);
    void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime);
}