package Gui.Controller;

import BE.Admin;
import BE.Person;
import Gui.Model.MainModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageController {
    private MainModel mainModel;
    private Person testAdminOBJ;

    public AdminPageController() throws SQLServerException {
        mainModel = new MainModel();
        testAdminOBJ = new Admin(1, "Test", "Test", "Test");
    }


    @FXML
    public void adduserBTNPress(ActionEvent event) throws IOException, SQLServerException {
        setupPersonWindow(false);
    }


    private void setupPersonWindow(boolean edit) throws IOException, SQLServerException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/UserCreationView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        UserCreationController addPersons = fxmlLoader.getController();
        addPersons.setController(this);
        if (edit){
            fxmlLoader.<UserCreationController>getController().setEdit(testAdminOBJ);
        }
        fxmlLoader.<UserCreationController>getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
