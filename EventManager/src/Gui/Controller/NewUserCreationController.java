package Gui.Controller;

import BE.Person;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewUserCreationController {


    @FXML
    private TextField userNameTxt, emailTxt, password1stTxt, password2ndTxt;
    @FXML
    private Label errorLabel;
    private PersonModel personModel;
    private List<Person> personList;
    public NewUserCreationController() throws SQLServerException {
        personModel = new PersonModel();
        personList = new ArrayList<>();
    }

    public void onAddUserBTNPress(ActionEvent actionEvent) {
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) { // checks the length of the input to ensure minimum safety
            if (verifyEmail()) { // if controller is in editing mode -> skip email verification step
                if (verifyPassword()) {
                            personModel.addPerson(userNameTxt.getText(), password2ndTxt.getText(), emailTxt.getText(), 2);
                            Stage stage =  (Stage) emailTxt.getScene().getWindow();
                            stage.close();
                    }
                    else {
                        errorLabel.setText("Please choose a user type");
                    }
            }
        }
        else {
            errorLabel.setText("You must provide a username with between 4 and 20 characters");
        }
    }



    public void onCancelBTNPress(ActionEvent actionEvent) {
    }
    private boolean verifyEmail() { // Checks for null input and gets a list of all Persons in the DB to check it against the input
        if(personList.isEmpty()){
            personList = personModel.getAllPerson();
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
}
