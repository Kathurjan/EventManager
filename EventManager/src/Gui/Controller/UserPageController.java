package Gui.Controller;

import BE.Event;
import DAL.DALException;
import Gui.Model.EventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class UserPageController {

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
    public UserPageController() {
        eventModel = new EventModel();
    }

    // Here we do a initialize for our tableview.
    public void initialize() {
        try {
            populateEventTableView();
        } catch (Exception e) {
            alertWarning("Failed to initialize User page");
        }
    }

    // Here we populate our event table view.
    public void populateEventTableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
        try {
            eventTableView.setItems(eventModel.getAllEvents());
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }

    }

    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }

    public void ticketsBTN(ActionEvent actionEvent) {
    }
}
