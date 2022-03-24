package Gui.Controller;

import BE.Admin;
import BE.Person;
import Gui.Model.MainModel;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.assertj.core.internal.Classes;

import java.io.IOException;

public class AdminPageController {
    public Button delBTN;

    @FXML
    private TableColumn<Classes, String> usernameColumn, emailColumn, typeColumn;
    @FXML
    private TableView<Person> adminTable;

    private MainModel mainModel;
    private Person testAdminOBJ;
    private final PersonModel model;

    public void initialize() {
        try {
            populateTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminPageController() throws SQLServerException {
        mainModel = new MainModel();
        testAdminOBJ = new Admin(1, "Test", "Test", "Test");
        this.model = new PersonModel();
    }

    @FXML
    public void addBTNPress(ActionEvent event) throws IOException, SQLServerException {
        setupPersonWindow(false);
    }

    public void editBTNPress(ActionEvent actionEvent) throws SQLServerException, IOException {
        setupPersonWindow(true);
    }

    private void setupPersonWindow(boolean edit) throws IOException, SQLServerException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/UserCreationView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        UserCreationController addPersons = fxmlLoader.getController();
        addPersons.setController(this);
        if (edit){
            fxmlLoader.<UserCreationController>getController().setEdit(testAdminOBJ);
        }
        fxmlLoader.<UserCreationController>getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshPersonList(){
        adminTable.setItems((ObservableList) model.getAllPerson());

    }

    public void delBTNPress(ActionEvent actionEvent) {
        if (adminTable.getSelectionModel().getSelectedIndex() != -1);
        {
            model.deletePerson((Person) adminTable.getSelectionModel().getSelectedItem());
            refreshPersonList();
        }
    }

    public void populateTableView(){
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        adminTable.setItems(mainModel.getAllPerson());
    }
}
