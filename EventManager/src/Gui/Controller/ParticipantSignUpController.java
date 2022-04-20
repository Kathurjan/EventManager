package Gui.Controller;

import BE.Event;
import BE.TicketType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ParticipantSignUpController {

    @FXML
    private TextField eventName;
    @FXML
    private TextField eventLocation;
    @FXML
    private DatePicker eventStartDate;
    @FXML
    private TextField hourStartTime;
    @FXML
    private TextField minStartTime;
    @FXML
    private TextField basePrice;
    @FXML
    private TextField extraFee;
    @FXML
    private TextField totalPric;
    @FXML
    private TextArea extraInfo;
    @FXML
    private TableView<TicketType> ticketTable;
    @FXML
    private TableColumn<TicketType, String> ticketTypeColumn;
    @FXML
    private TableColumn<TicketType, String> ticketExtraFeeColumn;
    @FXML
    private TextArea ticketInfo;

    private UserPageController controller;

    public ParticipantSignUpController(){

    }

    public void setController(UserPageController userPageController) {
        this.controller = userPageController;
    }

    public void setItems(Event event){

    }


}
