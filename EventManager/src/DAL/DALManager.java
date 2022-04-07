package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

// This is where we parse through all our data.

public class DALManager implements DALInterface{

    private PersonDAO personDAO;
    private EventDAO eventDAO;
    private TicketTypeDAO ticketDAO;
    private DatabaseConnector db = new DatabaseConnector();

    public DALManager() {
        personDAO = new PersonDAO();
        eventDAO = new EventDAO();
        ticketDAO = new TicketTypeDAO();
    }

    @Override
    public List<Person> getAllPerson() throws DALException {
        return personDAO.getAllPerson();
    }

    @Override
    public ObservableList<Event> getAllEvents() throws DALException {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<TicketType> getTicketTypes(int eventID) throws DALException {
        return ticketDAO.getTicketTypes(eventID);
    }

    @Override
    public void addPerson(String username, String password, String email, int type) throws DALException {
        personDAO.addPerson(username, password, email, type);
    }

    @Override
    public void deletePerson(Person selectedPerson) throws DALException {
        personDAO.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException {
        personDAO.editPerson(selectedPerson, username, password, email, type);
    }

    @Override
    public Admin verifyAdmin(String username, String password, int type) throws DALException {
        return personDAO.verifyAdmin(username, password,type);
    }

    @Override
    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id) {
        eventDAO.editEvent(eventName,startDate,eventLocation,price,startTime,warningLabel,id);
    }

    @Override
    public void addTicketTypes(List<TicketType> simpleList, int eventID) throws DALException{
        ticketDAO.addTicketType(simpleList, eventID);
    }

    @Override
    public void creatEvent() throws DALException {
        eventDAO.creatEvent();
    }

    @Override
    public void deleteTicketType(TicketType ticketType) throws DALException {
        ticketDAO.deleteTicketType(ticketType);
    }

    @Override
    public int selectLatest() throws DALException {
        return eventDAO.selectLastest();
    }

    @Override
    public void deleteEventWithID(int EventID) throws DALException {
        eventDAO.deleteEventWithID(EventID);
    }

    @Override
    public List<Participant> getAllParticipants(int EventID) throws DALException{
        return personDAO.getAllParticipants(EventID);
    }

}
