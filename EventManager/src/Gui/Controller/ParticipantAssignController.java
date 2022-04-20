package Gui.Controller;

import BE.*;
import DAL.DALException;
import Gui.Model.EventModel;
import Gui.Model.PersonModel;
import Gui.Model.TicketModel;
import Gui.Model.TicketTypeModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.Part;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ParticipantAssignController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField priceTxt;
    @FXML
    private CheckBox hasPayedCheck;
    @FXML
    private TableView<Participant> currentlyParticipatingTable;
    @FXML
    private TableColumn<Person, String> currentFirstNameColumn, currentLastNameColumn, currentHasPayedColumn;
    @FXML
    private TableView<Participant> availableParticipatingTable;
    @FXML
    private TableColumn<Person, String> availFirstNameColumn, availLastNameColumn;
    @FXML
    private TextField availableParticipatingSearchfield;
    @FXML
    private TextField currentlyParticipatingSearchfield;


    private PersonModel personModel;
    private TicketTypeModel ticketTypeModel;
    private HashMap<String, Double> ticketTypeMap;
    private HashMap<Integer, String> ticketMap;
    private List<Ticket> listOfTickets;
    private TicketModel ticketModel;
    private Event selectedEvent;
    private ObservableList<Participant> availParticipantObservable;
    private ObservableList<Participant> currentParticipantObservable;
    private ObservableList<TicketType> ticketTypeObservableList;
    private EventManagerPageController eventManagerPageController;


    public ParticipantAssignController() {
        ticketTypeModel = new TicketTypeModel();
        personModel = new PersonModel();
        ticketModel = new TicketModel();
        listOfTickets = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypeMap = new HashMap<>();
        ticketMap = new HashMap<>();
    }

    @FXML
    private void cancelBTNPress(ActionEvent event) {
        Stage stage = (Stage) availableParticipatingSearchfield.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addBTNPress(ActionEvent event) {
        if (availableParticipatingTable.getSelectionModel().getSelectedItem() != null) { //Checks for null selection
            Participant tempParticipant = availableParticipatingTable.getSelectionModel().getSelectedItem(); //Creates a temp participant for editing
            for (Participant particpant : currentParticipantObservable) {
                if (particpant.getID() == tempParticipant.getID()) {
                    alertWarning("This participant is already on the list, how did we get here?");
                    return;
                }
            }
            if (hasPayedCheck.isSelected()) { // Checks if the ticket is already payed for
                tempParticipant.setHasPayed(true);
            }
            if (choiceBox.getSelectionModel().getSelectedIndex() < 0) { // Check the ticket type selected and default to basic type if nothing is (Up for discussion)
                choiceBox.getSelectionModel().select(0);
            }
            tempParticipant.setEventID(selectedEvent.getEventID());// Gets the event.id from the event that was selected
            Participant finishedTempParticipant = ticketCreation(tempParticipant);// creates a finished participant from the out of the ticketCreation method
            try {
                personModel.addParticipant(finishedTempParticipant);  // Sends the participant down the rabbithole
            } catch (DALException e) {
                alertWarning(e.getMessage());
            }
            currentParticipantObservable.add(finishedTempParticipant); // Swaps the object of the participant on the list.
            availParticipantObservable.remove(availableParticipatingTable.getSelectionModel().getSelectedItem());
            refreshTables();
        } else
            alertWarning("You must select an available participant");
    }


    @FXML
    private void removeBTNPress(ActionEvent event) {
        if (currentlyParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            Participant participant = currentlyParticipatingTable.getSelectionModel().getSelectedItem();
            try { // Deletes both the ticket and the participant from the db, only the connecting data related to the participant is deleted
                personModel.deleteParticipant(currentlyParticipatingTable.getSelectionModel().getSelectedItem().getID(), selectedEvent.getEventID());
                ticketModel.deleteSingleTicket(currentlyParticipatingTable.getSelectionModel().getSelectedItem().getTicketID());
                participant.setEventID(-1);
                participant.setHasPayed(false);
                participant.setTicketID(-1);
                participant.setEventID(-1);
            } catch (DALException e) {
                alertWarning(e.getMessage());
            }
            availParticipantObservable.add(participant);
            currentParticipantObservable.remove(currentlyParticipatingTable.getSelectionModel().getSelectedItem());
            refreshTables();
        } else {
            alertWarning("You need to select a current participant");
        }
    }

    @FXML
    private void hasPayedCheckPress(ActionEvent event) {
        if (currentlyParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            Participant participant = currentlyParticipatingTable.getSelectionModel().getSelectedItem();
            try {
                personModel.editParticipant(currentlyParticipatingTable.getSelectionModel().getSelectedItem().getID(), selectedEvent.getEventID(), hasPayedCheck.isSelected());
            } catch (DALException e) {
                alertWarning(e.getMessage());
            }
            currentlyParticipatingTable.getSelectionModel().getSelectedItem().setHasPayed(hasPayedCheck.isSelected());
            refreshTables();
        }
    }

    @FXML
    private void sendBTNPress(ActionEvent event) {
    }

    @FXML
    private void selectChoiceBox(ActionEvent event) {
        choiceBoxUpdate(choiceBox.getValue());
    }

    @FXML
    private void selectCurrentParticipant(MouseEvent mouseEvent) {
        availableParticipatingTable.getSelectionModel().select(null);
        if (currentlyParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            try {
                choiceBoxUpdate(ticketMap.get(currentlyParticipatingTable.getSelectionModel().getSelectedItem().getTicketID()));
                hasPayedCheck.setSelected(currentlyParticipatingTable.getSelectionModel().getSelectedItem().getHasPayed());
            }
            catch (NullPointerException e){

            }
        }
    }

    @FXML
    private void selectAvailableParticipant(MouseEvent mouseEvent) {
        currentlyParticipatingTable.getSelectionModel().select(null);
        if (availableParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            choiceBox.getSelectionModel().select(1);
            priceTxt.setText("");
            hasPayedCheck.setSelected(false);
        }
    }


    private void populateAvailableTable() {
        availFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        availLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        availableParticipatingTable.setItems(availParticipantObservable);

        FilteredList<Participant> seachfilter = new FilteredList<>(availParticipantObservable, b -> true);
        availableParticipatingSearchfield.textProperty().addListener((observable, oldValue, newValue) -> {
            seachfilter.setPredicate(participant -> {

                // if search value is empty then it displays the participants as is.
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    refreshTables();
                    return true;
                }
                String seachWord = newValue.toLowerCase();
                if (participant.getFirstName().toLowerCase().indexOf(seachWord) > -1) {
                    return true; // data will change if participant found
                } else if (participant.getLastName().toLowerCase().indexOf(seachWord) > -1) {
                    return true;// data will change if participant is found
                }
                return false;
            });
        });
        SortedList<Participant> sorteddata = new SortedList<>(seachfilter);
        // binds the sorted result set with the table view;
        sorteddata.comparatorProperty().bind(availableParticipatingTable.comparatorProperty());
        availableParticipatingTable.setItems(sorteddata);
    }

    private void populateCurrentTable() {
        currentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        currentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        currentHasPayedColumn.setCellValueFactory(new PropertyValueFactory<>("hasPayed"));
        currentlyParticipatingTable.setItems(currentParticipantObservable);

        FilteredList<Participant> seachfilter = new FilteredList<>(currentParticipantObservable, b -> true);
        currentlyParticipatingSearchfield.textProperty().addListener((observable, oldValue, newValue) -> {
            seachfilter.setPredicate(participant -> {

                // if search value is empty then it displays the participant as is.
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    refreshTables();
                    return true;
                }
                String seachWord = newValue.toLowerCase();
                if (participant.getFirstName().toLowerCase().indexOf(seachWord) > -1) {
                    return true; // data will change if participant found
                } else if (participant.getLastName().toLowerCase().indexOf(seachWord) > -1) {
                    return true;// data will change if participant is found
                }
                return false;
            });
        });
        SortedList<Participant> sorteddata = new SortedList<>(seachfilter);
        // binds the sorted result set with the table view;
        sorteddata.comparatorProperty().bind(currentlyParticipatingTable.comparatorProperty());
        currentlyParticipatingTable.setItems(sorteddata);
    }

    private void alertWarning(String input) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }

    private void setUpChoiceBox() {
        ObservableList<String> tempList = FXCollections.observableArrayList();
        for (TicketType ticketype : ticketTypeObservableList) {
            tempList.add(ticketype.getTicketName().trim());
            ticketTypeMap.put(ticketype.getTicketName(), ticketype.getExtraFee());
            choiceBox.setItems(tempList);
        }
    }

    private Participant ticketCreation(Participant tempParticipant) {
        try {
            int ticketTypeID = ticketTypeObservableList.get(choiceBox.getSelectionModel().getSelectedIndex()).getTicketTypeID();
            int tempid = ticketModel.addTempTicket(ticketTypeID);
            Ticket ticket = new Ticket(tempid, ticketTypeID);
            listOfTickets.add(ticket);
            tempParticipant.setTicketID(tempid);
            ticketMap.put(tempid, ticketTypeObservableList.get(choiceBox.getSelectionModel().getSelectedIndex()).getTicketName());
            return tempParticipant;
        } catch (DALException e) {
            alertWarning(e.getMessage());
            return null;
        }
    }

    private void refreshTables() {
            currentlyParticipatingTable.setItems(currentParticipantObservable);
            availableParticipatingTable.setItems(availParticipantObservable);

    }

    private void choiceBoxUpdate(String input) {
        String str = ticketTypeModel.convertDoubleToString(selectedEvent.getPrice() + ticketTypeMap.get(input));
        priceTxt.setText(str);
        choiceBox.setValue(input);
    }

    public void setEdit(Event event) {
        selectedEvent = event;
        try {
            ticketTypeObservableList = ticketTypeModel.getTicketTypes(selectedEvent.getEventID());
            listOfTickets = ticketModel.getAllTicketPerType(ticketTypeObservableList);
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
        for (TicketType ticketType : ticketTypeObservableList) {
            for (Ticket ticket : listOfTickets) {
                if (ticket.getTicketTypeID() == ticketType.getTicketTypeID()) {
                    ticketMap.put(ticket.getID(), ticketType.getTicketName());
                }
            }
        }
        setUpChoiceBox();
        try {
            availParticipantObservable = personModel.getPersonsNotInEvent(selectedEvent.getEventID());
            currentParticipantObservable = personModel.getPersonsInEvent(selectedEvent.getEventID());
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
        populateAvailableTable();
        populateCurrentTable();
    }

    public void setController(EventManagerPageController eventManagerPageController) {
        this.eventManagerPageController = eventManagerPageController;
    }

}
