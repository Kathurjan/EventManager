package Gui.Controller;

import BE.TicketType;
import Gui.Model.TicketTypeModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.SQLException;


public class EventCreatorController {

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
    private TextArea ticketDescDisplayTxt;

    private TicketTypeModel ticketTypeModel;

    public EventCreatorController() {
        try {
            ticketTypeModel = new TicketTypeModel();
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

    @FXML
    public void buttonAddTicketPress(ActionEvent event){
    }







}
