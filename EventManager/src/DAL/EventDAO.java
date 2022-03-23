package DAL;

import java.sql.Connection;
import java.util.Date;

public class EventDAO {
    private DatabaseConnector con;

    public EventDAO(Connection connection) {
        con = new DatabaseConnector();
    }


    public static void addEvent(int eventID, Date startdate, String eventLocation, double price) {
    }
}
