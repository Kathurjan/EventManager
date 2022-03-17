package Gui.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    public TextField UserName;
    public PasswordField PassWord;


    public void signIn(ActionEvent actionEvent) throws IOException {

        Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
    }


}
