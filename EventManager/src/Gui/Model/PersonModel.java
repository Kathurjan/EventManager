package Gui.Model;

import BE.Person;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class PersonModel {
    private BLLInterface BLLInterface;

    public PersonModel() throws SQLServerException {
        BLLInterface = new BLLInterface();
    }

    public List<Person> getAllPerson(){
        return BLLInterface.getAllPerson();
    }

    public void addPerson(String username, String password, String email, int type){
        BLLInterface.addPerson(username, password, email, type);
    }

    public void deletePerson(Person selectedPerson){
        BLLInterface.deletePerson(selectedPerson);
    }

    public void editPerson(Person selectedPerson, String username, String password, String email, int type){
        BLLInterface.editPerson(selectedPerson, username, password, email, type);
    }
}
