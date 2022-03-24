package Gui.Model;

import BE.Admin;
import BE.Person;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel {

    private BLLInterface BLLInterface;

    private ObservableList<Person> personObservableList;

    public MainModel() throws SQLServerException {
        BLLInterface = new BLLInterface();
    }

    public ObservableList getAllPerson() {
        personObservableList = FXCollections.observableArrayList();
        personObservableList.setAll(BLLInterface.getAllPerson());
        return personObservableList;
    }


    public Admin verifyadmin(String username, String password, int type) {

    return BLLInterface.verifyadmin(username,password,type);
    }
}