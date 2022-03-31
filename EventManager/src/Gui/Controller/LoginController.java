package Gui.Controller;

import Gui.Model.MainModel;
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

import javax.swing.*;
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

    private MainModel mainModel;

    public LoginController() throws SQLServerException {
        mainModel = new MainModel();

    }

    public void signIn(ActionEvent actionEvent) throws IOException {
        loginMethod();

    }


    public void loginMethod() {
        if (UserName.getText().isBlank() || PassWord.getText().isBlank()) {
            alertWarning();
            return;
        }

        // checks of the username and password text field is empty and if the username and password matches the admins
        // it will allow them to log in.
        if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()) {
            if (mainModel.verifyadmin(UserName.getText(), PassWord.getText(), 0) != null) {
                try {
                    Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(part);
                    stage.setScene(scene);
                    stage.show();

                    // used to close the window if the pass and username is correct
                    Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                    stagebtnwindow.close();

                } catch (NullPointerException | IOException ex) {
                    System.out.println(ex);

                }
            } else if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()) {
                if (mainModel.verifyadmin(UserName.getText(), PassWord.getText(), 1) != null) {
                    try {
                        Parent part = FXMLLoader.load(getClass().getResource("../view/EventManagerPage.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(part);
                        stage.setScene(scene);
                        stage.show();

                        // used to close the window if the pass and username is correct
                        Stage stagebtnwindow = (Stage) loginBtn.getScene().getWindow();
                        stagebtnwindow.close();

                    } catch (NullPointerException | IOException ex) {
                        System.out.println(ex);

                    }
                } else {
                    // if it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
                    alertWarning();
                }
            }
        }

            // check if the user is a normal user, unlock method when we need -- remember to remove comments when we do.
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
            // if it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
        //alertWarning();
        }
    // used to close the stage
    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void skip(ActionEvent event) {
        try {
            Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException | IOException ex) {
            System.out.println(ex);

        }
    }

    public void loginAfterEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            loginMethod();
        }
    }

    public void newUserBTN(MouseEvent mouseEvent) {
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

    private void alertWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Wrong username or password.");
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }
}
