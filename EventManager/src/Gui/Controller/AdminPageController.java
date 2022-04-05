package Gui.Controller;

import BE.Person;
import DAL.DALException;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.assertj.core.internal.Classes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private TableColumn<Classes, String> usernameColumn, emailColumn;
    @FXML
    private TableColumn<Classes,Integer> typeColumn;
    @FXML
    private TableView<Person> adminTable;

    private PersonModel personModel;

    // Here we do a initialize for our tableview.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateTableView();
        } catch (Exception e) {
            alertWarning("Failed to initialize the window");
        }
    }

    // This is our constructor.
    public AdminPageController() {
        personModel = new PersonModel();
    }

    // This is opens our person window. In edit mode.
    @FXML
    private void addBTNPress(ActionEvent event) {
        setupPersonWindow(false);
    }

    // This is opens our person window. In edit mode.
    @FXML
    private void editBTNPress(ActionEvent actionEvent) {
        if(adminTable.getSelectionModel().getSelectedItem() != null){
            setupPersonWindow(true);
        }
        else System.out.println("Please select a user from the list");
    }

    // This is our how we open our person window and make it editable.
    private void setupPersonWindow(boolean edit) {
        try {
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
        catch (IOException e){
            alertWarning("Failed to load up User creation window");
        }
    }

    // This refreshes our person list.
    private void refreshPersonList(){
        try {
            adminTable.setItems(personModel.getAllPerson());
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }
    }

    // This deletes a person from our table.
    @FXML
    private void delBTNPress(ActionEvent actionEvent) {
        try {
            if (adminTable.getSelectionModel().getSelectedIndex() != -1);
            {
                personModel.deletePerson((Person) adminTable.getSelectionModel().getSelectedItem());
                refreshPersonList();
            }
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }

    }

    // This populates our tableview.
    @FXML
    private void populateTableView(){
        try {
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            adminTable.setItems(personModel.getAllPerson());
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }
    }

    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }
}
