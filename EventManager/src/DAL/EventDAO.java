package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class EventDAO {
    private DatabaseConnector con;
    public EventDAO(Connection connection) {
        con = new DatabaseConnector();
    }

    public void addEvent(int eventID, String eventname, Date startdate, String eventlocation, double price) {
        String sqlQuery = "INSERT INTO Event(ID, EventName, Startdate, EventLocation,event price) ";

        try(Connection db = con.getConnection()) {
            PreparedStatement statement = db.prepareStatement(sqlQuery);
            statement.setInt(1,eventID);
            statement.setString(2,eventname);
            statement.setDate(3, (java.sql.Date) startdate);
            statement.setString(4,eventlocation);
            statement.setDouble(5,price);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
