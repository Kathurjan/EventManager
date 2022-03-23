package BLL;

import BE.Admin;
import BE.Person;
import DAL.DALFacade;
import DAL.DALInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.Date;
import java.util.List;

public class BLLFacade implements BLLInterface{

    private DALInterface dalInterface;

    public BLLFacade() throws SQLServerException {
        dalInterface = new DALFacade();
    }

    @Override
    public List<Person> getAllPerson() {
        return dalInterface.getAllPerson();
    }

    @Override
    public void addPerson(String username, String password, String email) {
        dalInterface.addPerson(username, password, email);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        dalInterface.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email) {
        dalInterface.editPerson(selectedPerson, username, password, email);
    }

    @Override
    public Admin verifyadmin(String username,String password, int type) {
        return dalInterface.verifyadmin( username, password,type);
    }

    @Override
    public void addEvent(int eventID, Date startdate, String eventlocation, double price) {
        dalInterface.addEvent(eventID,startdate,eventlocation,price);
    }


}
