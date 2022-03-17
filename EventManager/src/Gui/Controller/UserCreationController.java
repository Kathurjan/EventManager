package Gui.Controller;

import BE.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;

public class UserCreationController {

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

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


    public void setController(AdminPageController adminPageController) {
         this.controller = adminPageController;
    }

    public void setEdit(Person person){

    }

    public void onAddUserBTNPress(ActionEvent event) {
    }

    public void onCancelBTNPress(ActionEvent event) {
    }
}
