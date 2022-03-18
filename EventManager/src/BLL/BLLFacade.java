package BLL;

import BE.Admin;
import BE.Person;
import DAL.DALFacade;
import DAL.DALInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class BLLFacade implements BLLInterface{

    private DALInterface dalInterface;

    public BLLFacade() throws SQLServerException {
        dalInterface = new DALFacade();
    }

    @Override
    public List<Person> getAllPerson() {
        return dalInterface.getAllPerson();
    }

    @Override
    public void addPerson(String username, String password, String email) {
        dalInterface.addPerson(username, password, email);
    }

    @Override
    public void deletePerson(Person selectedPerson) {
        dalInterface.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email) {
        dalInterface.editPerson(selectedPerson, username, password, email);
    }

    @Override
    public String verifyUserName() {
        return dalInterface.verifyUserName();
    }



    @Override
    public String verifyUserPassWord() {
        return dalInterface.verifyUserPassWord();
    }


    @Override
    public List<Admin> getAdmins(){
        return dalInterface.getallAdmins();
    }

}
