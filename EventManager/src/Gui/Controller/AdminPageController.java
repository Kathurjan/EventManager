package Gui.Controller;

import BE.Admin;
import BE.Person;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.assertj.core.internal.Classes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    public Button delBTN;

    @FXML
    private TableColumn<Classes, String> usernameColumn, emailColumn;
    @FXML
    private TableColumn<Classes,Integer> typeColumn;
    @FXML
    private TableView<Person> adminTable;

    private PersonModel personModel;


    public AdminPageController() throws SQLServerException {
        personModel = new PersonModel();
    }

    @FXML
    public void addBTNPress(ActionEvent event) throws IOException, SQLServerException {
        setupPersonWindow(false);
    }

    public void editBTNPress(ActionEvent actionEvent) throws SQLServerException, IOException {
        if(adminTable.getSelectionModel().getSelectedItem() != null){
            setupPersonWindow(true);
        }
        else System.out.println("Please select a user from the list");
    }

    private void setupPersonWindow(boolean edit) throws IOException, SQLServerException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/UserCreationView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        UserCreationController addPersons = fxmlLoader.getController();
        addPersons.setController(this);
        if (edit){
            fxmlLoader.<UserCreationController>getController().setEdit(adminTable.getSelectionModel().getSelectedItem());
        }
        fxmlLoader.<UserCreationController>getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshPersonList(){
        adminTable.setItems(personModel.getAllPerson());

    }

    public void delBTNPress(ActionEvent actionEvent) {
        if (adminTable.getSelectionModel().getSelectedIndex() != -1);
        {
            personModel.deletePerson((Person) adminTable.getSelectionModel().getSelectedItem());
            refreshPersonList();
        }
    }

    public void populateTableView(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        adminTable.setItems(personModel.getAllPerson());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
