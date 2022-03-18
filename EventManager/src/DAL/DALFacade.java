package DAL;

import BE.Admin;
import BE.Person;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class DALFacade implements DALInterface{

    private AdminDAO adminDAO;
    private PersonDAO personDAO;
    private DatabaseConnector db = new DatabaseConnector();

    public DALFacade() throws SQLServerException {
        adminDAO = new AdminDAO(db.getConnection());
    }

    @Override
    public void getallusers() {
        adminDAO.getAdmins();
    }

    @Override
    public List<Admin> getallAdmins() {
        return adminDAO.getAdmins();
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

    @Override
    public String verifyUserName() {
        return adminDAO.verifyUserName();
    }


    @Override
    public String verifyUserPassWord() {
       return adminDAO.verifyUserPassWord();
    }


}
