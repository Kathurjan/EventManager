package Gui.Controller;

import BE.Event;
import BE.Participant;
import BE.Person;
import BE.TicketType;
import DAL.DALException;
import Gui.Model.EventModel;
import Gui.Model.PersonModel;
import Gui.Model.TicketModel;
import Gui.Model.TicketTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ParticipantAssignController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField priceTxt;
    @FXML
    private CheckBox hasPayedCheck;
    @FXML
    private TableView<Person> currentlyParticipatingTable;
    @FXML
    private TableColumn<Person, String> currentFirstNameColumn, currentLastNameColumn, currentHasPayedColumn;
    @FXML
    private TableView<Person> availableParticipatingTable;
    @FXML
    private TableColumn<Person, String> availFirstNameColumn, availLastNameColumn;
    @FXML
    private TextField availableParticipatingSearchfield;


    private PersonModel personModel;
    private TicketTypeModel ticketTypeModel;
    private HashMap<String, Double> choiceToPrice;
    private TicketModel ticketModel;
    private Event selectedEvent;
    private ObservableList<Person> availParticipantObservable;
    private ObservableList<Person> currentParticipantObservable;
    private EventManagerPageController eventManagerPageController;


    public ParticipantAssignController() {
        ticketTypeModel = new TicketTypeModel();
        personModel = new PersonModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelBTNPress(ActionEvent event) {
        Stage stage =  (Stage) availableParticipatingSearchfield.getScene().getWindow();
        stage.close();
    }

    public void addBTNPress(ActionEvent event) {
        if(availableParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            currentParticipantObservable.add(availableParticipatingTable.getSelectionModel().getSelectedItem());
            availParticipantObservable.remove(availableParticipatingTable.getSelectionModel().getSelectedItem());
            populateTables();
        }
        else {
            alertWarning("You must select an available participant");
        }
    }

    public void removeBTNPress(ActionEvent event) {
        if(currentlyParticipatingTable.getSelectionModel().getSelectedItem() != null) {
            availParticipantObservable.add(currentlyParticipatingTable.getSelectionModel().getSelectedItem());
            currentParticipantObservable.remove(currentlyParticipatingTable.getSelectionModel().getSelectedItem());
            populateTables();
        }
        else {
            alertWarning("You need to select a current participant");
        }
    }

    public void hasPayedCheckPress(ActionEvent event) {
    }

    public void doneBTNPress(ActionEvent event) {
    }

    public void sendBTNPress(ActionEvent event) {
    }

    private void populateTables() {
        availFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        availLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        availableParticipatingTable.setItems(availParticipantObservable);

        currentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        currentLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        currentlyParticipatingTable.setItems(currentParticipantObservable);
    }

    private void alertWarning(String input) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }

    private void setUpChoiceBox(){
        try {
            ObservableList<String> tempList = FXCollections.observableArrayList();
            for (TicketType ticketype: ticketTypeModel.getTicketTypes(selectedEvent.getEventID())) {
                tempList.add(ticketype.getTicketName().trim());
                choiceToPrice.put(ticketype.getTicketName(), ticketype.getExtraFee());
            }
            choiceBox.setItems(tempList);
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
    }

    public void setEdit(Event event){
        selectedEvent = event;
        System.out.println(event);
        System.out.println(selectedEvent);
        setUpChoiceBox();
        try {
            availParticipantObservable = personModel.getPersonsNotInEvent(selectedEvent.getEventID());
            currentParticipantObservable = personModel.getPersonsInEvent(selectedEvent.getEventID());
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
        populateTables();
    }

    public void setController(EventManagerPageController eventManagerPageController) {
        this.eventManagerPageController = eventManagerPageController;
    }
}
