package BLL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
import DAL.DALFacade;
import DAL.DALInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public class BLLInterface implements BLLFacade{

    private DALInterface dalInterface;

    public BLLInterface() throws SQLServerException {
        dalInterface = new DALFacade();
    }

    @Override
    public List<Person> getAllPerson() {
        return dalInterface.getAllPerson();
    }

    @Override
    public ObservableList<Event> getAllEvents() {
        return dalInterface.getAllEvents();
    }

    @Override
    public List<TicketType> getTicketTypes(int eventID) throws SQLServerException {return dalInterface.getTicketTypes(eventID);}

    @Override
    public void addPerson(String username, String password, String email, int type) {
        dalInterface.addPerson(username, password, email, type);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        dalInterface.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email, int type) {
        dalInterface.editPerson(selectedPerson, username, password, email, type);
    }

    @Override
    public Admin verifyadmin(String username,String password, int type) {
        return dalInterface.verifyadmin( username, password,type);
    }

    @Override
    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime) {
        dalInterface.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }


}
