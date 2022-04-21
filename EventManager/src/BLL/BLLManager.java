package BLL;

import BE.*;
import DAL.DALException;
import DAL.DALManager;
import DAL.DALInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

// This is where we parse through all our data.

public class BLLManager implements BLLInterface {

    private DALInterface dalInterface;
    private TextConverter textConverter;

    public BLLManager() {
        dalInterface = new DALManager();
        textConverter = new TextConverter();
    }

    @Override
    public List<Person> getAllPerson() throws DALException {
        return dalInterface.getAllPerson();
    }

    @Override
    public ObservableList<Event> getAllEvents() throws DALException {
        return dalInterface.getAllEvents();
    }

    @Override
    public List<TicketType> getTicketTypes(int eventID) throws DALException {
        return dalInterface.getTicketTypes(eventID);
    }

    @Override
    public void addPerson(String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        dalInterface.addPerson(username, password, email, type, firstName, lastName);
    }

    @Override
    public void deletePerson(Person selectedPerson) throws DALException {
        dalInterface.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        dalInterface.editPerson(selectedPerson, username, password, email, type, firstName, lastName);
    }

    @Override
    public Person verifyAdmin(String username, String password) throws DALException {
        return dalInterface.verifyAdmin(username, password);
    }

    @Override
    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id) {
        dalInterface.editEvent(eventName, startDate, eventLocation, price, startTime, warningLabel, id);
    }

    @Override
    public double convertTextToDouble(String input) throws BLLException {
        return textConverter.convertStringToDouble(input);
    }

    @Override
    public String convertStartTimeToOneString(String string1, String string2) {
        return textConverter.convertStartTimeToOneString(string1, string2);
    }

    @Override
    public String[] convertStartTimeToTwoString(String string) {
        return textConverter.convertStartTimeToTwoString(string);
    }

    @Override
    public void deleteTicketType(TicketType ticketType) throws DALException {
        dalInterface.deleteTicketType(ticketType);
    }

    @Override
    public void addTicketTypes(List<TicketType> simpleList, int eventID) throws DALException {
        dalInterface.addTicketTypes(simpleList, eventID);
    }

    @Override
    public void creatEvent() throws DALException {
        dalInterface.creatEvent();
    }

    @Override
    public int selectLatest() throws DALException {
        return dalInterface.selectLatest();
    }

    @Override
    public void deleteEventWithID(int EventID) throws DALException {
        dalInterface.deleteEventWithID(EventID);
    }

    @Override
    public List<Participant> getAllParticipants(int EventID) throws DALException {
        return dalInterface.getAllParticipants(EventID);
    }

    @Override
    public List<Participant> getPersonsNotInEvent(int EventID) throws DALException {
        return dalInterface.getPersonsNotInEvent(EventID);
    }

    @Override
    public List<Participant> getPersonsInEvent(int EventID) throws DALException {
        return dalInterface.getPersonsInEvent(EventID);
    }

    @Override
    public String convertDoubleToString(Double doub) {
        return textConverter.convertDoubleToString(doub);
    }

    @Override
    public int addTempTicket(int ticketTypeID) throws DALException {
        return dalInterface.addTempTicket(ticketTypeID);
    }

    @Override
    public void deleteSingleTicket(int id) throws DALException {
        dalInterface.deleteSingleTicket(id);
    }

    @Override
    public void deleteTickets(List<Ticket> ticketList) throws DALException {
        dalInterface.deleteTickets(ticketList);
    }

    @Override
    public void addParticipant(Participant participant) throws DALException {
        dalInterface.addParticipant(participant);
    }

    @Override
    public void deleteParticipant(int personID, int eventID) throws DALException {
        dalInterface.deleteParticipant(personID, eventID);
    }

    @Override
    public void editParticipant(int personID, int eventID, boolean hasPayed) throws DALException {
        dalInterface.editParticipant(personID, eventID, hasPayed);
    }

    @Override
    public List<Ticket> getAllTicketPerType(List<TicketType> ticketTypes) throws DALException {
        return dalInterface.getAllTicketPerType(ticketTypes);
    }
}
