package Gui.Controller;

import BE.Event;
import Gui.Model.EventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventManagerPageController implements Initializable{

    @FXML
    private TableColumn<Event, String> nameColumn, locationColumn, startTimeColumn;
    @FXML
    private TableColumn<Event,Double> priceColumn;
    @FXML
    private TableColumn<Event, Date> dateColumn;
    @FXML
    private TableView<Event> eventTableView;

    private EventModel eventModel;

    // This is our constructor.
    public EventManagerPageController() throws SQLServerException {
        eventModel = new EventModel();
    }


    // Here we do a initialize for our tableview.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateEventTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Here we populate our event table view.
    private void populateEventTableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
        eventTableView.setItems(eventModel.getAllEvents());
        System.out.println(eventModel.getAllEvents());

    }


    @FXML
    private void editEventPress(ActionEvent actionEvent){
        if(eventTableView.getSelectionModel().getSelectedItem() != null){
            try {
                setupEventCreator(true);
            }
            catch (SQLServerException | IOException ex){
                System.out.println("Put this in an error label or something (edit event button)");
            }
        }
        else System.out.println("Put this in an error label or something (edit event missing selected item)");
    }

    @FXML
    private void addEventPress(ActionEvent actionEvent){
        try {
            setupEventCreator(false);
        }
        catch (SQLServerException | IOException ex){
            System.out.println("Put this in an error label or something (Create event button)");
        }
    }

    private void setupEventCreator(boolean edit) throws IOException, SQLServerException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/EventCreator.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        EventCreatorController addEvent = fxmlLoader.getController();
        addEvent.setController(this);
        if (edit){
            fxmlLoader.<UserCreationController>getController().setEditEventCreator(eventTableView.getSelectionModel().getSelectedItem());
        }
        fxmlLoader.<UserCreationController>getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
