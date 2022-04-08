package Gui.Controller;

import BE.Event;
import DAL.DALException;
import Gui.Model.EventModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventManagerPageController implements Initializable{
    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn<Event, String> nameColumn, locationColumn, startTimeColumn;
    @FXML
    private TableColumn<Event,Double> priceColumn;
    @FXML
    private TableColumn<Event, Date> dateColumn;
    @FXML
    private TableView<Event> eventTableView;

    private EventModel eventModel;

    // This is our constructor.
    public EventManagerPageController() {
        eventModel = new EventModel();
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
    private void populateEventTableView(){
        try {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("StateTime"));
            eventTableView.setItems(eventModel.getAllEvents());
            System.out.println(eventModel.getAllEvents());
        }
        catch (DALException e){
            alertWarning(e.getMessage());
        }

    }


    @FXML
    private void editEventPress(ActionEvent actionEvent){
        if(eventTableView.getSelectionModel().getSelectedItem() != null){
            try {
                setupEventCreator(true);
            }
            catch (IOException ex){
                alertWarning("Failed setting up the edit window");
            }
        }
        else System.out.println("Put this in an error label or something (edit event missing selected item)");
    }

    @FXML
    private void addEventPress(ActionEvent actionEvent){
        try {
            setupEventCreator(false);
        }
        catch (IOException ex){
            alertWarning("Failed setting up the Event Creator");
        }
    }

    private void setupEventCreator(boolean edit) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/EventCreator.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            EventCreatorController addEvent = fxmlLoader.getController();
            addEvent.setController(this);
            if (edit){
                fxmlLoader.<EventCreatorController>getController().setEdit(eventTableView.getSelectionModel().getSelectedItem());
            }
            if(!edit){
                fxmlLoader.<EventCreatorController>getController().createTempEvent();
            }
            fxmlLoader.<EventCreatorController>getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            alertWarning("Failed to load the create/edit event window");
        }
    }



     /* TODO mail address, username, password, title and body

    sendMail("mail address");  // Mail address you want to send an email


    //making connection and then sent the mail
    public static void sendMail(String recepient) throws Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String username = "your acc mail"; //Your account
        String password = "xxxxxxxx";      //Your password

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = prepareMessage(session, username, recepient);

        Transport.send(message);
        System.out.println("mail sent");
    }
    //prepare message to send
    private static Message prepareMessage(Session session, String username, String recepient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("title");        //title of the message
            message.setText("body of email");   //body of the message
            return message;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
    */

    private void alertWarning(String input){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(input);
        alert.setContentText("Please try again.");
        alert.getOwner();
        alert.showAndWait();
    }


    public void assignParticipantsBTNPress(ActionEvent event) {
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
        }
        catch (IOException e){
            alertWarning("Failed to load the create/edit event window");
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
