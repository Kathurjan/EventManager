package Gui.Controller;

import BE.Event;
import Gui.Model.EventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.fxml.FXML;
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

    public UserPageController() throws SQLServerException {
        eventModel = new EventModel();
    }

    public void initialize() {
        try {
            populateEventTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateEventTableView(){

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
        eventTableView.setItems(eventModel.getAllEvents());
        System.out.println(eventModel.getAllEvents());

    }
}
