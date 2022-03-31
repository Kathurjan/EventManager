package Gui.Controller;

import BE.Event;
import BE.TicketType;
import Gui.Model.TicketTypeModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ResourceBundle;


public class EventCreatorController implements Initializable {

    @FXML
    private TextField eventNameTxt;

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
    private ObservableList<TicketType> ticketTypeObservableList;
    private EventManagerPageController eventManagerPageController;

    public void setController(EventManagerPageController eventManagerPageController) {
        this.eventManagerPageController = eventManagerPageController;
    }
;
    public EventCreatorController() {
        try {
            ticketTypeModel = new TicketTypeModel();
            ticketTypeObservableList = FXCollections.observableArrayList();
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
        TicketType tempticket = new TicketType(ticketNameTxt.getText(), ticketDescInputTxt.getText(), 1);
        ticketTypeObservableList.add(tempticket);
        populateTableview();
    }

    @FXML
    private void buttonRemoveTicketPress(ActionEvent event) {
        if (ticketTypeTable.getSelectionModel().getSelectedItem() != null) {
            ticketTypeObservableList.remove(ticketTypeTable.getSelectionModel().getSelectedItem());
            populateTableview();
        }
        else System.out.println("Put in error label (Remove button)");
    }




    private void populateTableview(){
        // This dot, method populates the ticket type table view (Space comma here) dot mads (Non dash captilized) this is where you rage & try to fix this exclamantion mark dot
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketName"));
        ticketDescColumn.setCellValueFactory(new PropertyValueFactory<>("ticketDescription"));
        ticketTypeTable.setItems(ticketTypeObservableList);
    }

    public void setEdit(Event event){

    }
}
