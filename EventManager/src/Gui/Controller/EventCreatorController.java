package Gui.Controller;

import BE.Event;
import BE.TicketType;
import BLL.BLLException;
import Gui.Model.EventModel;
import Gui.Model.TicketTypeModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class EventCreatorController implements Initializable {

    @FXML
    private TextField eventNameTxt;

    @FXML
    private TextField extraFeeTxt;

    @FXML
    private TextField hourTxt;

    @FXML
    private TextField minuteTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField ticketNameTxt;

    @FXML
    private TextArea ticketDescInputTxt;

    @FXML
    private Button buttonAddTicket;

    @FXML
    private Button buttonRemoveTicket;

    @FXML
    private TextArea warningLabelTxt;

    @FXML
    private TableView<TicketType> ticketTypeTable;

    @FXML
    private TableColumn<TicketType, String> ticketTypeColumn, ticketDescColumn;

    @FXML
    private TextArea ticketDescDisplayTxt;

    private TicketTypeModel ticketTypeModel;
    private EventModel eventModel;
    private ObservableList<TicketType> ticketTypeObservableList;
    private EventManagerPageController eventManagerPageController;
    private int eventID;

    public void setController(EventManagerPageController eventManagerPageController) {
        this.eventManagerPageController = eventManagerPageController;
    }
;
    public EventCreatorController() {
        try {
            eventModel = new EventModel();
            ticketTypeModel = new TicketTypeModel();
            ticketTypeObservableList = FXCollections.observableArrayList();
            eventModel.creatEvent(); // Creates a temp event with null values to fill in when event is finalised
        }
        catch (SQLServerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Exception occured");
            alert.setContentText("Please try again.");
            alert.getOwner();
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTableview();
    }

    @FXML
    private void buttonAddTicketPress(ActionEvent event){
        TicketType tempticket = null;
        try {
            if (ticketNameTxt.getText().length() > 2 || ticketNameTxt.getText().length() < 50) {
                tempticket = new TicketType(ticketNameTxt.getText(), ticketDescInputTxt.getText(), ticketTypeModel.convertTxtToDouble(extraFeeTxt.getText()), eventID);
            }
        } catch (BLLException e) {
            //Alert
            alertWarning(e.getMessage());

            // Actual exception
            System.out.println(e.getCause());
        }
        ticketTypeObservableList.add(tempticket);
        populateTableview();
    }

    @FXML
    private void buttonRemoveTicketPress(ActionEvent event) {
        if (ticketTypeTable.getSelectionModel().getSelectedItem() != null) {
            ticketTypeObservableList.remove(ticketTypeTable.getSelectionModel().getSelectedItem());
            populateTableview();
        }
        else alertWarning("No ticket selected");
    }

    @FXML
    private void createEventButtonPress(ActionEvent event) {
        try {
            java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue()); // Converts the date of the datepicker to sql Date
            eventID = eventModel.selectLatest(); // Fetches the latest created event (this one)
            if (hourTxt.getText().matches("[0-9]+") && minuteTxt.getText().matches("[0-9]+")) {
                if (hourTxt.getText().length() <= 2 && minuteTxt.getText().length() <= 2) {
                    String starttime = eventModel.convertStartTimeToOneString(hourTxt.getText(), minuteTxt.getText());
                    if (eventNameTxt.getText().length() > 2 || eventNameTxt.getText().length() < 50) {
                        if (locationTxt.getText().length() > 1) {
                            eventModel.editEvent(eventNameTxt.getText(), date, locationTxt.getText(), eventModel.convertStringToDouble(priceTxt.getText()), starttime, warningLabelTxt.getText(), eventID);
                            ticketTypeModel.addTicketTypes(ticketTypeObservableList, eventID);
                        }
                        else {
                            alertWarning("Input a location");
                        }
                    }
                    else {
                        alertWarning("Event name needs to be between 2 and 50 characters");
                    }
                }
                else {
                    alertWarning("Start time hour and minute can not be greater than 2");
                }
            }
            else {
                alertWarning("Start time can only contain number");
            }
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelButtonPress(ActionEvent event) {
        eventID = eventModel.selectLatest();
        eventModel.deleteEventWithID(eventID);
        Stage stage =  (Stage) eventNameTxt.getScene().getWindow();
        stage.close();
    }

    private void populateTableview(){
        // This dot, method populates the ticket type table view (Space comma here) dot mads (Non dash captilized) this is where you rage & try to fix this exclamantion mark dot
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketName"));
        ticketDescColumn.setCellValueFactory(new PropertyValueFactory<>("extraFee"));
        ticketTypeTable.setItems(ticketTypeObservableList);
    }

    public void setEdit(Event event){

    }

    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }
}
