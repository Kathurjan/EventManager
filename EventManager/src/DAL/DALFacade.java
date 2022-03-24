package DAL;

import BE.Admin;
import BE.Person;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.Date;
import java.util.List;

public class DALFacade implements DALInterface{

    private AdminDAO adminDAO;
    private PersonDAO personDAO;
    private EventDAO eventDAO;
    private DatabaseConnector db = new DatabaseConnector();

    public DALFacade() throws SQLServerException {
        adminDAO = new AdminDAO(db.getConnection());
        personDAO = new PersonDAO();
        eventDAO = new EventDAO();
    }

    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAllPerson();
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
    public void editPerson(Person selectedPerson, String username, String password, String email) {
        personDAO.editPerson(selectedPerson, username, password, email);
    }


    @Override
    public Admin verifyadmin(String username,String password,int type) {
        return adminDAO.verifyAdmin(username, password,type);
    }

    @Override
    public void addEvent(int eventID, String eventname, Date startdate, String eventlocation, double price, String startTime) {
        eventDAO.addEvent(eventID,eventname,startdate,eventlocation,price,startTime);
    }


}
