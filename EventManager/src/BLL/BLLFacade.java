package BLL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public interface BLLFacade {

    List<Person> getAllPerson();
    ObservableList<Event> getAllEvents();
    List<TicketType> getTicketTypes(int eventID);

    void addPerson(String username, String password, String email, int type);


    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email, int type);

    Admin verifyadmin(String username,String password, int type);
    void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime);
}