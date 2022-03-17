package BLL;

import BE.Person;
import DAL.DALFacade;
import DAL.DALInterface;

import java.util.List;

public class BLLFacade implements BLLInterface{

    private DALInterface dalInterface;

    public BLLFacade(){
        dalInterface = new DALFacade();
    }

    @Override
    public List<Person> getAllPerson() {
        return dalInterface.getAllPerson();
    }

    @Override
    public void addPerson(String username, String password, String name) {
        dalInterface.addPerson(username, password, name);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        dalInterface.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String name) {
        dalInterface.editPerson(selectedPerson, username, password, name);
    }
}
