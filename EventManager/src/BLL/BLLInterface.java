package BLL;

import BE.*;
import DAL.DALException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

// This is where we parse through all our data.

public interface BLLInterface {
    List<Person> getAllPerson() throws DALException;

    ObservableList<Event> getAllEvents() throws DALException;

    List<TicketType> getTicketTypes(int eventID) throws SQLServerException, DALException;

    void addPerson(String username, String password, String email, int type) throws DALException;


    void deletePerson(Person selectedPerson) throws DALException;

    void editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException;

    Admin verifyAdmin(String username, String password, int type) throws DALException;

    void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id);

    double convertTextToDouble(String input) throws BLLException;

    String convertStartTimeToOneString(String string1, String string2);

    void addTicketTypes(List<TicketType> simpleList, int eventID) throws DALException;

    void creatEvent() throws SQLServerException, DALException;

    int selectLatest() throws DALException;

    void deleteEventWithID(int EventID) throws DALException;

    String[] convertStartTimeToTwoString(String string);

    void deleteTicketType(TicketType ticketType) throws DALException;

    List<Participant> getAllParticipants(int EventID) throws DALException;

    List<Person> getPersonsInEvent(int EventID) throws DALException;

    List<Person> getPersonsNotInEvent(int EventID) throws DALException;
}