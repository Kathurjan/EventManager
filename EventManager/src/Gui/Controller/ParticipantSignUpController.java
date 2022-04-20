package Gui.Controller;

import BE.*;
import DAL.DALException;
import Gui.Model.EventModel;
import Gui.Model.PersonModel;
import Gui.Model.TicketModel;
import Gui.Model.TicketTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TextField totalPrice;
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
    @FXML
    private CheckBox signedUpCheck;

    private UserPageController controller;
    private EventModel eventModel;
    private TicketTypeModel ticketTypeModel;
    private PersonModel personModel;
    private Event selectedEvent;
    private Person currentPerson;
    private TicketModel ticketModel;
    private ObservableList<TicketType> ticketTypeObservableList;
    private ObservableList<Participant> checkList;

    public ParticipantSignUpController() {
        eventModel = new EventModel();
        ticketTypeModel = new TicketTypeModel();
        personModel = new PersonModel();
        ticketModel = new TicketModel();
        ticketTypeObservableList = FXCollections.observableArrayList();
        checkList = FXCollections.observableArrayList();
        currentPerson = CurrentUserStorage.getInstance().getCurrentPerson();
    }

    public void setController(UserPageController userPageController) {
        this.controller = userPageController;
    }

    public void setItems(Event event) { //Set up all the information regarding the event
        try {
            selectedEvent = event;
            checkIfInEvent();
            String[] arr = eventModel.convertStartTimeToTwoString(selectedEvent.getStateTime()); // Converts startTime to 2 strings.
            eventName.setText(selectedEvent.getEventName());
            eventLocation.setText(selectedEvent.getEventLocation());
            eventStartDate.setValue(selectedEvent.getStartDate().toLocalDate());
            ticketTypeObservableList = ticketTypeModel.getTicketTypes(selectedEvent.getEventID());
            basePrice.setText(selectedEvent.getPrice().toString());
            hourStartTime.setText(arr[0]);
            minStartTime.setText(arr[1]);
            extraInfo.setText(selectedEvent.getWarningLabel());
            populateTableview();
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
    }

    private void populateTableview() {
        // This dot, method populates the ticket type table view (Space comma here) dot mads (Non dash captilized) this is where you rage & try to fix this exclamantion mark dot
        ticketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("ticketName"));
        ticketExtraFeeColumn.setCellValueFactory(new PropertyValueFactory<>("extraFee"));
        ticketTable.setItems(ticketTypeObservableList);
    }

    private void checkIfInEvent() {
        try {
            checkList = personModel.getPersonsInEvent(selectedEvent.getEventID());
            for (Participant part : checkList) {
                if (part.getID() == currentPerson.getID()) {
                    signedUpCheck.setSelected(true);
                }
            }
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }

    }

    @FXML
    public void selectTickeType(MouseEvent mouseEvent) { //When selecting ticketype it sets the appropriate fields.
        TicketType ticketType = ticketTable.getSelectionModel().getSelectedItem();
        ticketInfo.setText(ticketType.getTicketDescription());
        extraFee.setText(Double.toString(ticketType.getExtraFee()));
        totalPrice.setText(Double.toString(ticketType.getExtraFee() + selectedEvent.getPrice()));
    }

    @FXML
    private void YesBTNPress(ActionEvent event) {
        try {
            if (!signedUpCheck.isSelected()) {
                if (ticketTable.getSelectionModel().getSelectedItem() != null) {
                    int tickedid = ticketModel.addTempTicket(ticketTable.getSelectionModel().getSelectedItem().getTicketTypeID());
                    Participant participant = new Participant(currentPerson.getID(), currentPerson.getUsername(),
                            currentPerson.getEmail(), currentPerson.getType(),
                            currentPerson.getFirstName(), currentPerson.getLastName(),
                            selectedEvent.getEventID(),tickedid,
                            false);

                    personModel.addParticipant(participant);

                    Stage stagebtnwindow = (Stage) basePrice.getScene().getWindow();
                    stagebtnwindow.close();
                } else alertWarning("You need to select a ticketType");
            } else alertWarning("You are already signed up");
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
    }

    @FXML
    private void NoBTNPress(ActionEvent event) {
        Stage stagebtnwindow = (Stage) basePrice.getScene().getWindow();
        stagebtnwindow.close();
    }

    private void alertWarning(String input) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }

}
