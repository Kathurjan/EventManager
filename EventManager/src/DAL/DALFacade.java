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
        personDAO = new PersonDAO(db.getConnection());
    }




    @Override
    public List<Person> getAllPerson() {
        return personDAO.getAllPerson();
    }

    @Override
    public void addPerson(String username, String password, String email) {
        personDAO.addPerson(username, password, email);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        personDAO.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email) {
        personDAO.editPerson(selectedPerson, username, password, email);
    }


    @Override
    public Admin verifyadmin(String username,String password, int type) {
        return adminDAO.verifyAdmin(username, password,type);
    }


}
