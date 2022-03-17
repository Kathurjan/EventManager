package DAL;

import BE.Person;

import java.util.List;

public class DALFacade implements DALInterface{

    private PersonDAO personDAO;

    public DALFacade(){
        personDAO = new PersonDAO();
    }

    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAllPerson();
    }

    @Override
    public void addPerson(String username, String password, String name) {
        personDAO.addPerson(username, password, name);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        personDAO.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String name) {
        personDAO.editPerson(selectedPerson, username, password, name);
    }
}
