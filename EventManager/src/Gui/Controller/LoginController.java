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

    private MainModel mainModel;

    public LoginController() throws SQLServerException {
        mainModel = new MainModel();

    }

    public void signIn(ActionEvent actionEvent) {
        System.out.println("sign in");
        loginMethod();
    }
    public void loginMethod(){
        if (UserName.getText().isBlank() || PassWord.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Wrong username or password.");
            alert.setContentText("Please try again.");
            alert.getOwner();
            alert.showAndWait();
        }
        // checks of the username and password text field is empty and if the username and password matches the admins
        // it will allow them to log in.
        if (!UserName.getText().isBlank() && !PassWord.getText().isBlank()){
            if (mainModel.verifyadmin(UserName.getText(), PassWord.getText(),1) != null) {
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
            }else {
                // if it doesn't  match it will open up the below alert window alerting the user to incorrect username and password.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Wrong username or password.");
                alert.setContentText("Please try again.");
                alert.getOwner();
                alert.showAndWait();
            }
        }
    }
    // used to close the stage
    public void close(ActionEvent actionEvent) {
        Stage stage =  (Stage) closeButton.getScene().getWindow();
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
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            loginMethod();
        }
    }
}
