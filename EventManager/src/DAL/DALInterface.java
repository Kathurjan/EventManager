package DAL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

// This is where we parse through all our data.

public interface DALInterface {

    List<Person> getAllPerson() throws DALException;

    ObservableList<Event> getAllEvents() throws DALException;

    List<TicketType> getTicketTypes(int eventID) throws DALException;

    void addPerson(String username, String password, String email, int type) throws DALException;

    void deletePerson(Person selectedPerson) throws DALException;

    void editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException;


    Admin verifyAdmin(String username, String password, int type) throws DALException;


    void editEvent(String eventname, Date startdate, String eventlocation, double price, String startTime, String warningLabel, int id);

    void addTicketTypes(List<TicketType> simpleList, int eventID) throws DALException;

    void creatEvent() throws DALException;

    int selectLatest() throws DALException;

    void deleteEventWithID(int EventID) throws DALException;

    void deleteTicketType(TicketType ticketType) throws DALException;
}
