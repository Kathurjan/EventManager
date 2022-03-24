package DAL;

import BE.Admin;
import BE.Person;

import java.util.Date;
import java.util.List;

public interface DALInterface {

    List<Person> getAllPerson();

    void addPerson(String username, String password, String email, int type);

    void deletePerson(Person selectedPerson);

    void editPerson(Person selectedPerson, String username, String password, String email);


    public  Admin verifyadmin(String username,String password,int type);


    void addEvent(int eventID, String eventname, Date startdate, String eventlocation, double price, String startTime);
}
