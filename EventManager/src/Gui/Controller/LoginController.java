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
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField PassWord;
    @FXML
    private Button closeButton;
    @FXML
    private Label newUserLabel;

    private PersonModel personModel;

    // This is our constructor.
    public LoginController() {
        personModel = new PersonModel();

    }

    // This button runs our login method.
    @FXML
    private void signIn(ActionEvent actionEvent) {
        loginMethod();
    }

    // This method makes sure that the username and password is correct.
    private void loginMethod() {
        if (UserName.getText().isBlank() || PassWord.getText().isBlank()) {
            alertWarning("Wrong username or password.");
            return;
        }
        // Checks of the username and password text field is empty and if the username and password matches the admins
        // It will allow them to log in.

        try {
            if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()) {
                Person person = personModel.verifyAdmin(UserName.getText(), PassWord.getText()); //Get a person object from the Database corresponding to the given info.
                CurrentUserStorage.getInstance().setCurrentPerson(person); // Uses a singleton class to store the user for later use.
                if (person != null && person.getType() == 0) { // Checks if the object is null
                    try {
                        CurrentUserStorage.getInstance().setCurrentPerson(person);
                        Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(part);
                        stage.setScene(scene);
                        stage.show();

                        // Used to close the window if the pass and username is correct
                        Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                        stagebtnwindow.close();

                    } catch (NullPointerException | IOException ex) {
                        System.out.println(ex);

                    }
                } else if (person != null && person.getType() == 1) {
                    try {
                        CurrentUserStorage.getInstance().setCurrentPerson(person);
                        Parent part = FXMLLoader.load(getClass().getResource("../view/EventManagerPage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(part);
                        stage.setScene(scene);
                        stage.show();

                        // Used to close the window if the pass and username is correct
                        Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                        stagebtnwindow.close();

                    } catch (NullPointerException | IOException ex) {
                        System.out.println(ex);
                    }
                } else if (person != null && person.getType() == 2) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("../view/UserPageView.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.show();

                        //used to close window if the password and username is correct
                        Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                        stagebtnwindow.close();
                    } catch (NullPointerException | IOException ex) {
                        System.out.println(ex);
                    }
                } else {
                    // If it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
                    alertWarning("Wrong username or password.");
                }
            }
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }
    }

    // Used to close the stage
    @FXML
    private void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // Here we do a initialize.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // If keystroke ENTER is pressed it will run login method.
    @FXML
    private void loginAfterEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            loginMethod();
        }
    }

    // If label is pressed you move to user creation.
    @FXML
    private void newUserBTN(MouseEvent mouseEvent) {
        try {
            Parent part = FXMLLoader.load(getClass().getResource("../view/NewUserCreationView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();

            // used to close the window if the pass and username is correct
            Stage stagewindow = (Stage) newUserLabel.getScene().getWindow();
            stagewindow.close();
    } catch (IOException e) {
            alertWarning("Failed to set up new User window");
        }
    }

    // This is our template for warnings.
    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }
}
