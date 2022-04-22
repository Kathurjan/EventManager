package DAL;

import BE.Event;
import BE.Person;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;

public class EventDAO {


    private final DatabaseConnector db = new DatabaseConnector();

    public int selectLastest() throws DALException{
        int id = 0;
        String sqlStatement = "SELECT TOP 1 * FROM Event ORDER BY ID DESC";
        try (Connection con = db.getConnection()) {
            Statement statement = con.createStatement();
            if (statement.execute(sqlStatement)) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    id = rs.getInt("ID");
                }
            }
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return id;
    }

    public void creatEvent() throws DALException {
        String sqlQuery = "INSERT INTO Event(EventName, EventDate, EventLocation, EventPrice, StartTime, WarningLabel) VALUES (?,?,?,?,?,?) ";
        try (Connection con = db.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sqlQuery);
            statement.setString(1, null);
            statement.setDate(2, null);
            statement.setString(3, null);
            statement.setDouble(4, 0.00);
            statement.setString(5, null);
            statement.setString(6, null);
            statement.addBatch(); // Adding to the statement
            statement.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public void editEvent(String eventName, java.sql.Date eventDate, String eventLocation, double eventPrice, String startTime, String warningLabel, int eventID) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "UPDATE Event set EventName = ?, EventDate = ?, EventLocation = ?, EventPrice = ?, StartTime = ?, WarningLabel = ? WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, eventName);
            pstm.setDate(2, eventDate);
            pstm.setString(3, eventLocation);
            pstm.setDouble(4, eventPrice);
            pstm.setString(5, startTime);
            pstm.setString(6, warningLabel);
            pstm.setInt(7, eventID);
            pstm.executeUpdate(); // Executing the prepared statement with the specified parameters
        } catch (SQLException ex) {
            throw new DALException("The Data access layer met with an error", ex);
        }
    }


    public ObservableList<Event> getAllEvents() throws DALException{
        ObservableList<Event> eventsList = FXCollections.observableArrayList();
        try (Connection con = db.getConnection()) {
            String sqlStatement = "SELECT * FROM Event";
            Statement statement = con.createStatement();
            if (statement.execute(sqlStatement)) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String eventName = rs.getString("EventName");
                    Date startdate = rs.getDate("EventDate");
                    String eventlocation = rs.getString("EventLocation");
                    Double price = rs.getDouble("EventPrice");
                    String startTime = rs.getString("StartTime");
                    String warningLabel = rs.getString("WarningLabel");
                    Event event = new Event(id, eventName, startdate, eventlocation, price, startTime, warningLabel);// Creating a person object from the retrieved values
                    eventsList.add(event); // Adding the person to  list
                }
            }
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return eventsList;
    }

    public void deleteEventWithID(int eventID) throws DALException{
        try(Connection con = db.getConnection()){
            String query = "DELETE FROM Person WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,eventID);
            pstm.executeUpdate(); // Executing the statement
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }
}
