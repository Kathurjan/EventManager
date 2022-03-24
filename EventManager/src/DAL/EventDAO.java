package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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



}
