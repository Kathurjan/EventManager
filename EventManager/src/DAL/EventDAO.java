package DAL;

import BE.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class EventDAO {
    private final static DatabaseConnector db = new DatabaseConnector();

    public EventDAO(){

    }

    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime) {
        String sqlQuery = "INSERT INTO Event(ID, EventName, Startdate, EventLocation, event price, StartTime) ";
        try(Connection con = db.getConnection()) {
            PreparedStatement statement = con.prepareStatement(sqlQuery);
            statement.setInt(1,eventID);
            statement.setString(2,eventName);
            statement.setDate(3, (java.sql.Date) startDate);
            statement.setString(4,eventLocation);
            statement.setDouble(5,price);
            statement.setString(6, startTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Event> getAllEvents() {
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
                    Float price = rs.getFloat("EventPrice");
                    String startTime = rs.getString("StartTime");
                    Event event = new Event(id,eventName, startdate, eventlocation, price, startTime);// Creating a person object from the retrieved values
                    eventsList.add(event); // Adding the person to  list
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return eventsList;
    }
}
