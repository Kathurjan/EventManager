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
    private ObservableList<Participant> participantObservableList;

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

    public ObservableList<Participant> getAllParticipants(int EventID) throws DALException{
        participantObservableList = FXCollections.observableArrayList();
        participantObservableList.setAll(BLLInterface.getAllParticipants(EventID));
        return participantObservableList;
    }

    public void addParticipant(Participant participant) throws DALException{
        BLLInterface.addParticipant(participant);
    }

    public void deleteParticipant(int personID, int eventID) throws DALException{
        BLLInterface.deleteParticipant(personID,eventID);
    }

    public void editParticipant(int personID, int eventID, boolean hasPayed) throws DALException{
        BLLInterface.editParticipant(personID,eventID,hasPayed);
    }

    public ObservableList<Participant> getPersonsNotInEvent(int EventID) throws DALException{
        participantObservableList = FXCollections.observableArrayList();
        participantObservableList.setAll(BLLInterface.getPersonsNotInEvent(EventID));
        return participantObservableList;
    }

    public ObservableList<Participant> getPersonsInEvent(int EventID) throws DALException{
        participantObservableList = FXCollections.observableArrayList();
        participantObservableList.setAll(BLLInterface.getPersonsInEvent(EventID));
        return participantObservableList;
    }
}
