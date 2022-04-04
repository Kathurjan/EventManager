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

public class DALManager implements DALInterface{

    private PersonDAO personDAO;
    private EventDAO eventDAO;
    private TicketTypeDAO ticketDAO;
    private DatabaseConnector db = new DatabaseConnector();

    public DALManager() throws SQLServerException {
        personDAO = new PersonDAO();
        eventDAO = new EventDAO();
        ticketDAO = new TicketTypeDAO();
    }

    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAllPerson();
    }

    @Override
    public ObservableList<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<TicketType> getTicketTypes(int eventID) throws SQLServerException {
        return ticketDAO.getTicketTypes(eventID);
    }

    @Override
    public void addPerson(String username, String password, String email, int type) {
        personDAO.addPerson(username, password, email, type);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        personDAO.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email, int type) {
        personDAO.editPerson(selectedPerson, username, password, email, type);
    }

    @Override
    public Admin verifyAdmin(String username, String password, int type) {
        return personDAO.verifyAdmin(username, password,type);
    }

    @Override
    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id) {
        eventDAO.editEvent(eventName,startDate,eventLocation,price,startTime,warningLabel,id);
    }

    @Override
    public void addTicketTypes(List<TicketType> simpleList, int eventID){
        ticketDAO.addTicketType(simpleList, eventID);
    }

    @Override
    public void creatEvent() throws SQLServerException {
        eventDAO.creatEvent();
    }

    @Override
    public int selectLatest(){
        return eventDAO.selectLastest();
    }

    @Override
    public void deleteEventWithID(int EventID){
        eventDAO.deleteEventWithID(EventID);
    }

}
