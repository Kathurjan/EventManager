package Gui.Controller;

import Gui.Model.MainModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Label wrongPassOrName;
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


    public void signIn(ActionEvent actionEvent) throws IOException {
        if (!UserName.getText().isBlank())
            if( UserName.getText().contains(mainModel.verifyUserName()))
                if (!PassWord.getText().isBlank())
                    if( PassWord.getText().contains(mainModel.verifyUserPassWord()))
        try{Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();}
        catch (NullPointerException ex){
            System.out.println(ex);

        }
        else {
            wrongPassOrName.setText("Wrong user name or password please try again.");

        }
    }


    public void close(ActionEvent actionEvent) {
        Stage stage =  (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void skip(ActionEvent event) {
        try{Parent part = FXMLLoader.load(getClass().getResource("../view/AdminPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();}
        catch (NullPointerException | IOException ex){
            System.out.println(ex);

        }
    }
}
