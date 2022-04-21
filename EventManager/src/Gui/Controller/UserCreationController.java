package Gui.Controller;

import BE.Participant;
import BE.Person;
import DAL.DALException;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserCreationController implements Initializable {

    Stage stage;

    @FXML
    private ChoiceBox userCheckBox;

    @FXML
    private Label userCheckBoxLabel;

    @FXML
    private TextField userNameTxt, emailTxt, firstNameTxt, lastNameTxt;

    @FXML
    private TextField password1stTxt;

    @FXML
    private TextField password2ndTxt;

    @FXML
    private Label errorLabel;

    ObservableList<String> TypeofUser = FXCollections.observableArrayList("Admin","EventManager","User");
    private AdminPageController controller;
    private PersonModel personModel;
    private Person selectedPerson;
    private int type;
    private boolean isEditing = false;
    private List<Person> personList;


    public UserCreationController() {
        personModel = new PersonModel();
        personList = new ArrayList<>();
    }


    public void setController(AdminPageController adminPageController) {
         this.controller = adminPageController;
    }

    public void setEdit(Person person){ //Sets the isEditing variable for use in addUser method and gets the info on the selected person
        selectedPerson = person;
        isEditing = true;
        userNameTxt.setText(person.getUsername());
        emailTxt.setText(person.getEmail());
        if(selectedPerson.getType() != 0) {
            userCheckBox.getSelectionModel().select(selectedPerson.getType());
        }
        else {
            userCheckBox.setVisible(false);
            userCheckBoxLabel.setVisible(false);
        }

        // Password is omitted to ensure protection. (Up for discussion)
    }

    public void onAddUserBTNPress(ActionEvent event) {
        type = userCheckBox.getSelectionModel().getSelectedIndex();
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) { // checks the length of the input to ensure minimum safety
            if (verifyEmail() | isEditing) { // if controller is in editing mode -> skip email verification step
                if(verifyNames()){
                if (verifyPassword()) {
                    if (type != -1) {
                        try {
                            if (!isEditing) { // does the check of the isEditing variable
                                personModel.addPerson(userNameTxt.getText(), password2ndTxt.getText(), emailTxt.getText(), type, firstNameTxt.getText(), lastNameTxt.getText());
                                Stage stage = (Stage) emailTxt.getScene().getWindow();
                                stage.close();
                            } else
                                personModel.editPerson(selectedPerson, userNameTxt.getText(), password1stTxt.getText(), emailTxt.getText(), 0);
                            Stage stage = (Stage) emailTxt.getScene().getWindow();
                            stage.close();
                        } catch (DALException e) {
                            alertWarning(e.getMessage());
                        }
                    } else {
                        errorLabel.setText("Please choose a user type");
                    }
                }
                }
            }
        }
        else {
            errorLabel.setText("You must provide a username with between 4 and 20 characters");
        }
    }

    public void onCancelBTNPress(ActionEvent event) {
        Stage stage =  (Stage) emailTxt.getScene().getWindow();
        stage.close();
    }

    private boolean verifyEmail() { // Checks for null input and gets a list of all Persons in the DB to check it against the input
        if(personList.isEmpty()){
            try {
                personList = personModel.getAllPerson();
            }
            catch (DALException e){
                alertWarning(e.getMessage());
            }
        }
        if (emailTxt.getText() != null) {
            for (Person person : personList) {
                if (Objects.equals(person.getEmail(), emailTxt.getText())) {
                    errorLabel.setText("The email is already in use, check the persons list");
                    return false;
                }
            }
            return true;
        }
        errorLabel.setText("You must provide an email");
        return false;
    }

    private boolean verifyPassword(){
        if(Objects.equals(password1stTxt.getText(), password2ndTxt.getText())){ // compares the repeated password to ensure input
            if(password1stTxt.getText().length() > 4 && password1stTxt.getText().length() < 21){ // Checks against minimum and maximum input length.
                for (int i = 0; i < password1stTxt.getText().length(); i++) { // Loops through the length of the input
                    if(Character.isDigit(password1stTxt.getText().charAt(i))){ // If a char in the input is a digit it returns true.
                        return true;
                    }
                }
            }
            else{
                errorLabel.setText("Your password needs to be between 5 and 20 characters");
            }
        }
        else {
            errorLabel.setText("Passwords do not match");
            return false;
        }
        errorLabel.setText("Your password needs atleast one number");
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userCheckBox.setItems(TypeofUser);
    }

    private boolean verifyNames()
    {
        if(firstNameTxt.getText() == null && lastNameTxt.getText() == null){
            errorLabel.setText("Insert name");
            return false;}
        else
            return true;
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
