package Gui.Controller;

import BE.Event;
import BE.Participant;
import DAL.DALException;
import Gui.Model.EventModel;
import Gui.Model.PersonModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventManagerPageController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn<Event, String> nameColumn, locationColumn, startTimeColumn;
    @FXML
    private TableColumn<Event, Double> priceColumn;
    @FXML
    private TableColumn<Event, Date> dateColumn;
    @FXML
    private TableView<Event> eventTableView;
    @FXML
    private TableView<Participant> participantTable;
    @FXML
    private TableColumn<Participant, String> participantColumnFirstname;
    @FXML
    private TableColumn<Participant, String> participantColumnLastname;

    private EventModel eventModel;
    private PersonModel personModel;

    // This is our constructor.
    public EventManagerPageController() {
        eventModel = new EventModel();
        personModel = new PersonModel();

    }

    // Here we do a initialize for our tableview.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateEventTableView();
        } catch (Exception e) {
            alertWarning("Failed to initialize Event manager page");
        }
    }

    // Here we populate our event table view.
    private void populateEventTableView() {
        try {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
            eventTableView.setItems(eventModel.getAllEvents());
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
    }

    private void populateParticipantTable(int id) {
        participantColumnFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        participantColumnLastname.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        try {
            participantTable.setItems(personModel.getPersonsInEvent(id));
        } catch (DALException e) {
            alertWarning(e.getMessage());
        }
    }


    @FXML
    private void editEventPress(ActionEvent actionEvent) {
        if (eventTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                setupEventCreator(true);
            } catch (IOException ex) {
                alertWarning("Failed setting up the edit window");
            }
<<<<<<< Updated upstream
        } else System.out.println("Put this in an error label or something (edit event missing selected item)");
=======
        }
        else alertWarning("Put this in an error label or something (edit event missing selected item");
>>>>>>> Stashed changes
    }

    @FXML
    private void addEventPress(ActionEvent actionEvent) {
        try {
            setupEventCreator(false);
        } catch (IOException ex) {
            alertWarning("Failed setting up the Event Creator");
        }
    }

    @FXML
    public void selectEvent(MouseEvent mouseEvent) {
        populateParticipantTable(eventTableView.getSelectionModel().getSelectedItem().getEventID());
    }

    private void setupEventCreator(boolean edit) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/EventCreator.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            EventCreatorController addEvent = fxmlLoader.getController();
            addEvent.setController(this);
            if (edit) {
                fxmlLoader.<EventCreatorController>getController().setEdit(eventTableView.getSelectionModel().getSelectedItem());
            }
            if (!edit) {
                fxmlLoader.<EventCreatorController>getController().createTempEvent();
            }
            fxmlLoader.<EventCreatorController>getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            alertWarning("Failed to load the create/edit event window");
        }
    }


    private void alertWarning(String input) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }


    public void assignParticipantsBTNPress(ActionEvent event) {
        if (eventTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/ParticipantAssign.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ParticipantAssignController participantAssignController = fxmlLoader.getController();
                fxmlLoader.<ParticipantAssignController>getController().setEdit(eventTableView.getSelectionModel().getSelectedItem());
                participantAssignController.setController(this);
                fxmlLoader.<ParticipantAssignController>getController();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                alertWarning("Failed to load the create/edit event window");
            }
        } else {
            alertWarning("You must select an event to add participants to");
        }
    }

    public void logoutBTN(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

        Stage stagebtnwindow = (Stage) logoutButton.getScene().getWindow();
        stagebtnwindow.close();
    }
}
