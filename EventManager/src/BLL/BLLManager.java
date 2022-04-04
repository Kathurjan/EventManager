package BLL;

import BE.Admin;
import BE.Event;
import BE.Person;
import BE.TicketType;
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

    public BLLManager() throws SQLServerException {
        dalInterface = new DALManager();
        textConverter = new TextConverter();
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
    public Admin verifyAdmin(String username, String password, int type) {
        return dalInterface.verifyAdmin( username, password,type);
    }

    @Override
    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id) {
        dalInterface.editEvent(eventName,startDate,eventLocation,price,startTime, warningLabel, id);
    }

    @Override
    public double convertTextToDouble(String input) throws BLLException {
        return textConverter.convertStringToDouble(input);
    }

    @Override
    public String convertStartTimeToOneString(String string1, String string2){
        return textConverter.convertStartTimeToOneString(string1,string2);
    }

    @Override
    public void addTicketTypes(List<TicketType> simpleList, int eventID){
        dalInterface.addTicketTypes(simpleList, eventID);
    }

    @Override
    public void creatEvent() throws SQLServerException {
        dalInterface.creatEvent();
    }

    @Override
    public int selectLatest(){
        return dalInterface.selectLatest();
    }

    @Override
    public void deleteEventWithID(int EventID){
        dalInterface.deleteEventWithID(EventID);
    }

}
