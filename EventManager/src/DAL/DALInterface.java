package DAL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

// This is where we parse through all our data.

public interface DALInterface {

    List<Person> getAllPerson();

    ObservableList<Event> getAllEvents();

    List<TicketType> getTicketTypes(int eventID) throws SQLServerException;

    void addPerson(String username, String password, String email, int type);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email, int type);


    Admin verifyAdmin(String username, String password, int type);


    void addEvent(String eventname, Date startdate, String eventlocation, double price, String startTime);
}
