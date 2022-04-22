package Gui.Controller;

import BE.Event;
import BE.Participant;

import DAL.DALException;
import Gui.Model.EventModel;
import Gui.Model.PersonModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Date;

public class UserPageController {

    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn<Event, String> nameColumn, locationColumn, startTimeColumn, userFirstNameColumn, userLastNameColumn;
    @FXML
    private TableColumn<Event,Double> priceColumn;
    @FXML
    private TableColumn<Event, Date> dateColumn;
    @FXML
    private TableView<Event> eventTableView;
    @FXML
    private TableView<Participant> participantsTableView;

    private EventModel eventModel;
    private PersonModel personModel;


    // This is our constructor.
    public UserPageController() {
        eventModel = new EventModel();
        personModel = new PersonModel();
    }

    // Here we do a initialize for our tableview.
    public void initialize() {
        try {
            populateEventTableView();
        } catch (Exception e) {
            alertWarning("Failed to initialize User page");
        }
    }

    // Here we populate our event table view.
    public void populateEventTableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
        try {
            eventTableView.setItems(eventModel.getAllEvents());
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }
    }
    private void populateParticipantTableView(){
        userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        try{
            participantsTableView.setItems(personModel.getAllParticipants(eventTableView.getSelectionModel().getSelectedItem().getEventID()));
        } catch (DALException e){
            alertWarning(e.getMessage());
        }
    }

    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }

    public void ticketsBTN(ActionEvent actionEvent) {
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

    public void handleShopParticipants(MouseEvent mouseEvent) {
        populateParticipantTableView();
    }

    @FXML
    private void setupSignUpPage(ActionEvent actionEvent) throws IOException {
        if (eventTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/ParticipantSignUp.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ParticipantSignUpController signUp = fxmlLoader.getController();
                signUp.setController(this);
                fxmlLoader.<ParticipantSignUpController>getController().setItems(eventTableView.getSelectionModel().getSelectedItem());
                fxmlLoader.<ParticipantSignUpController>getController();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
                alertWarning("Failed to load the create/edit event window");
            }
        }
        else alertWarning("You must select an event to sign up");
    }


}
