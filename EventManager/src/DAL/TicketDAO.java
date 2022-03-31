package DAL;

import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private final static DatabaseConnector db  = new DatabaseConnector();

    public List<TicketType> getTicketTypes(int eventID) throws SQLServerException {
        List<TicketType> ticketTypeList = new ArrayList<>();
        try (Connection connection = db.getConnection()){
            String query = "SELECT * FROM TicketType INNER JOIN Event ON TicketType.EventID = Event.ID WHERE TicketType.ID = ? ORDER by TicketName DESC";
            PreparedStatement ptsm = connection.prepareStatement(query);
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()){
                TicketType ticketType = new TicketType(rs.getString("TicketName"), rs.getString("TicketDescription"), rs.getInt("TypeID"));
                ticketTypeList.add(ticketType);
            }
            return ticketTypeList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
