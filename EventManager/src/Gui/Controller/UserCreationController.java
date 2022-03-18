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

import java.awt.*;
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


    public void setController(AdminPageController adminPageController) throws SQLServerException {
         this.controller = adminPageController;
         personModel = new PersonModel();
    }

    public void setEdit(Person person){

    }

    public void onAddUserBTNPress(ActionEvent event) {
        if (userNameTxt.getText().length() > 3 && userNameTxt.getText().length() < 21) {
            if (emailTxt.getText() != null) {
                if (verifyPassword()) {
                    personModel.addPerson(userNameTxt.getText(), password2ndTxt.getText(), emailTxt.getText());
                    Stage stage =  (Stage) emailTxt.getScene().getWindow();
                    stage.close();
                }
                else{
                    System.out.println("Lmao pls fix password");
                }
            }
            else {
                System.out.println("Lmao pls fix email");
            }
        }
        else {
            System.out.println("Lmao pls fix name");
        }
    }

    public void onCancelBTNPress(ActionEvent event) {
        Stage stage =  (Stage) emailTxt.getScene().getWindow();
        stage.close();
    }

    public boolean verifyPassword(){
        return Objects.equals(password1stTxt.getText(), password2ndTxt.getText());
    }
}
