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
import javafx.scene.control.*;
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
        if (UserName.getText().isBlank()){
            wrongPassOrName.setText("Please type in a username and password");
        }

        else if (!UserName.getText().equals(mainModel.verifyUserName()) && !UserName.getText().equals(mainModel.verifyUserPassWord())){
            wrongPassOrName.setText("Incorrect Password");
        }

        //System.out.println("userName: "+mainModel.verifyUserName());
        if (!UserName.getText().isBlank())
            if( UserName.getText().equals(mainModel.verifyUserName()))
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
    }


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

    public void testButton(ActionEvent actionEvent) {
        System.out.println(mainModel.verifyUserName());
        System.out.println(mainModel.verifyUserPassWord());
    }
}
