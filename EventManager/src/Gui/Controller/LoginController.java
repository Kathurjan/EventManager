package Gui.Controller;

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
    public LoginController() throws SQLServerException {
        personModel = new PersonModel();

    }

    // This button runs our login method.
    @FXML
    private void signIn(ActionEvent actionEvent) throws IOException {
        loginMethod();
    }

    // This method makes sure that the username and password is correct.
    private void loginMethod() {
        if (UserName.getText().isBlank() || PassWord.getText().isBlank()) {
            alertWarning();
            return;
        }

        // Checks of the username and password text field is empty and if the username and password matches the admins
        // It will allow them to log in.
        if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()) {
            if (personModel.verifyAdmin(UserName.getText(), PassWord.getText(), 0) != null) {
                try {
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
            } else if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()) {
                if (personModel.verifyAdmin(UserName.getText(), PassWord.getText(), 1) != null) {
                    try {
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
                } else {
                    // If it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
                    alertWarning();
                }
            }
        }

            // Check if the user is a normal user, unlock method when we need -- remember to remove comments when we do.
       /* if (mainModel.verifyadmin(UserName.getText(),PassWord.getText(),2)!=null){
            try{
                Parent parent = FXMLLoader.load(getClass().getResource(""));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();

                //used to close window if the password and username is correct
                Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                stagebtnwindow.close();
            }
            catch(NullPointerException ex){
                System.out.println(ex);
            }
        }*/
            // If it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
        //alertWarning();
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
            e.printStackTrace();
        }
    }

    // This is our template for warnings.
    private void alertWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Wrong username or password.");
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }
}
