package Gui.Model;

import BE.Person;
import BLL.BLLFacade;
import BLL.BLLInterface;
import DAL.PersonDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class PersonModel {
    private BLLInterface bllInterface;

    public PersonModel() throws SQLServerException {
        bllInterface = new BLLFacade();
    }

    public List<Person> getAllPerson(){
        return bllInterface.getAllPerson();
    }

    public void addPerson(String username, String password, String email, int type){
        bllInterface.addPerson(username, password, email, type);
    }

    public void deletePerson(Person selectedPerson){
        bllInterface.deletePerson(selectedPerson);
    }

    public void editPerson(Person selectedPerson, String username, String password, String email){
        bllInterface.editPerson(selectedPerson, username, password, email);
    }
}
