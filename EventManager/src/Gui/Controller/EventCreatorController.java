package Gui.Controller;

import BE.TicketType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;


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



    public EventCreatorController(){

    }



}
