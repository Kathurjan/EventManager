package Gui.Controller;

import BE.Admin;
import BE.Event;
import BE.Person;
import Gui.Model.EventModel;
import Gui.Model.MainModel;
import Gui.Model.PersonModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.assertj.core.internal.Classes;

import java.util.Date;

public class EventManagerPageController {

    @FXML
    private TableColumn<Classes, String> nameColumn, locationColumn, startTimeColumn;
    @FXML
    private TableColumn<Classes,Double> priceColumn;
    @FXML
    private TableColumn<Classes, Date> dateColumn;
    @FXML
    private TableView<Event> eventTableview;

    private EventModel eventModel;

    public EventManagerPageController() throws SQLServerException {
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
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("EventPrice"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        eventTableview.setItems(eventModel.getAllEvents());
        System.out.println(eventModel.getAllEvents());

    }
}
