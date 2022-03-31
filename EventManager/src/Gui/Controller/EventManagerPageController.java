package Gui.Controller;

import BE.Event;
import Gui.Model.EventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private void populateEventTableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
        eventTableView.setItems(eventModel.getAllEvents());
        System.out.println(eventModel.getAllEvents());

    }
}
