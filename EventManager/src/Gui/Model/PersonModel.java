package Gui.Model;

import BE.Person;
import BLL.BLLFacade;
import BLL.BLLInterface;
import DAL.PersonDAO;

import java.util.List;

public class PersonModel {
    private BLLInterface bllInterface;

    public PersonModel(){
        bllInterface = new BLLFacade();
    }

    public List<Person> getAllPerson(){
        return bllInterface.getAllPerson();
    }

    public void addPerson(String username, String password, String name){
        bllInterface.addPerson(username, password, name);
    }

    public void deletePerson(Person selectedPerson){
        bllInterface.deletePerson(selectedPerson);
    }

    public void editPerson(Person selectedPerson, String username, String password, String name){
        bllInterface.editPerson(selectedPerson, username, password, name);
    }
}
