package Gui.Model;

import BE.Admin;
import BE.Participant;
import BE.Person;
import BLL.BLLManager;
import DAL.DALException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PersonModel {
    private BLLManager BLLInterface;
    private ObservableList<Person> personObservableList;

    public PersonModel() {
        BLLInterface = new BLLManager();
    }

    public ObservableList<Person> getAllPerson() throws DALException {
        personObservableList = FXCollections.observableArrayList();
        personObservableList.setAll(BLLInterface.getAllPerson());
        return personObservableList;
    }

    public Admin verifyAdmin(String username, String password, int type) throws DALException {
        return BLLInterface.verifyAdmin(username,password,type);
    }

    public void addPerson(String username, String password, String email, int type) throws DALException {
        BLLInterface.addPerson(username, password, email, type);
    }

    public void deletePerson(Person selectedPerson) throws DALException {
        BLLInterface.deletePerson(selectedPerson);
    }

    public void editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException {
        BLLInterface.editPerson(selectedPerson, username, password, email, type);
    }

    public List<Participant> getAllParticipants(int EventID) throws DALException{
        return BLLInterface.getAllParticipants(EventID);
    }
}
