package Gui.Controller;

import BE.Person;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Objects;

public class UserCreationController {

    Stage stage;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField password1stTxt;

    @FXML
    private TextField password2ndTxt;

    @FXML
    private Label errorLabel;

    @FXML
    private CheckComboBox checkComboBox;


    private AdminPageController controller;
    private PersonModel personModel;
    private Person selectedPerson;
    private boolean isEditing = false;


    public void setController(AdminPageController adminPageController) throws SQLServerException {
         this.controller = adminPageController;
         personModel = new PersonModel();
    }

    public void setEdit(Person person){
        selectedPerson = person;
        isEditing = true;
        userNameTxt.setText(person.getUsername());
        emailTxt.setText(person.getEmail());
    }

    public void onAddUserBTNPress(ActionEvent event) {
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) {
            if (verifyEmail() | isEditing) {
                if (verifyPassword()) {
                    if(!isEditing){
                        personModel.addPerson(userNameTxt.getText(), password2ndTxt.getText(), emailTxt.getText());
                        Stage stage =  (Stage) emailTxt.getScene().getWindow();
                        stage.close();
                    }
                    else personModel.editPerson(selectedPerson, userNameTxt.getText(), password1stTxt.getText(), emailTxt.getText());
                    Stage stage =  (Stage) emailTxt.getScene().getWindow();
                    stage.close();
                }
            }
            else {
                errorLabel.setText("You must provide an email");
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

    private boolean verifyEmail() {
        List<Person> personList = personModel.getAllPerson();
        for (Person person: personList) {
            if(Objects.equals(person.getEmail(), emailTxt.getText())){
                return true;
            }
        }
        errorLabel.setText("The email is already in use, check the persons list");
        return false;
    }

    private boolean verifyPassword(){
        if(Objects.equals(password1stTxt.getText(), password2ndTxt.getText())){
            if(password1stTxt.getText().length() > 4 && password1stTxt.getText().length() < 21){
                for (int i = 0; i < password1stTxt.getText().length(); i++) {
                    if(Character.isDigit(password1stTxt.getText().charAt(i))){
                        System.out.println(password1stTxt.getText().charAt(i));
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
