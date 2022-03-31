package Gui.Controller;

import BE.Person;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

    // This is our constructor.
    public NewUserCreationController() throws SQLServerException {
        personModel = new PersonModel();
        personList = new ArrayList<>();
    }

<<<<<<< Updated upstream
    @FXML
    private void onAddUserBTNPress(ActionEvent actionEvent) throws IOException {
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) { // checks the length of the input to ensure minimum safety
            if (verifyEmail()) { // if controller is in editing mode -> skip email verification step
=======
    // This button lets you signup or not depending if you do it correctly.
    @FXML
    private void onAddUserBTNPress(ActionEvent actionEvent) throws IOException {
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) { // Checks the length of the input to ensure minimum safety
            if (verifyEmail()) { // If controller is in editing mode -> skip email verification step
>>>>>>> Stashed changes
                if (verifyPassword()) {
                            personModel.addPerson(userNameTxt.getText(), password2ndTxt.getText(), emailTxt.getText(), 2);
                            Stage stage =  (Stage) emailTxt.getScene().getWindow();
                            stage.close();
                    Parent part = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    Stage stage1= new Stage();
                    Scene scene = new Scene(part);
                    stage1.setScene(scene);
                    stage1.show();
                    }
            }
        }
        else {
            errorLabel.setText("You must provide a username with between 4 and 20 characters");
        }
    }

<<<<<<< Updated upstream
=======
    // This button cancels and leads you back to the login screen.
>>>>>>> Stashed changes
    @FXML
    private void onCancelBTNPress(ActionEvent actionEvent) throws IOException {
        Stage stage =  (Stage) emailTxt.getScene().getWindow();
        stage.close();
        Parent part = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Stage stage1= new Stage();
        Scene scene = new Scene(part);
        stage1.setScene(scene);
        stage1.show();
    }

    // This method checks if you have verified your mail.
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

    // This method verifies if the two passwords are the same.
    private boolean verifyPassword(){
        if(Objects.equals(password1stTxt.getText(), password2ndTxt.getText())){ // Compares the repeated password to ensure input
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
        errorLabel.setText("Your password needs at least one number");
        return false;
    }
}
