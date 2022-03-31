package Gui.Model;

import BE.Admin;
import BE.Person;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PersonModel {
    private BLLInterface BLLInterface;
    private ObservableList<Person> personObservableList;

    public PersonModel() throws SQLServerException {
        BLLInterface = new BLLInterface();
    }

    public ObservableList<Person> getAllPerson(){
        personObservableList = FXCollections.observableArrayList();
        personObservableList.setAll(BLLInterface.getAllPerson());
        return personObservableList;
    }

    public Admin verifyadmin(String username, String password, int type) {
        return BLLInterface.verifyadmin(username,password,type);
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
