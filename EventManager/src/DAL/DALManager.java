package DAL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

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
    public Admin verifyadmin(String username,String password,int type) {
        return personDAO.verifyAdmin(username, password,type);
    }

    @Override
    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime) {
        eventDAO.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }


}
