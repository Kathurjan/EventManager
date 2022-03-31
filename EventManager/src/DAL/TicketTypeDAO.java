package DAL;

import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAO {

    private final static DatabaseConnector db  = new DatabaseConnector();

    public void addTicketType(List<TicketType> listToBeAdded, int eventID){
        try(Connection connection = db.getConnection()) {
            String query = "INSERT INTO TicketType(TicketName, TicketDescription, EventID) VALUES (?,?,?)";
            PreparedStatement ptsm = connection.prepareStatement(query);
            for (TicketType ticketType: listToBeAdded) {
                ptsm.setString(1, ticketType.getTicketName());
                ptsm.setString(2, ticketType.getTicketDescription());
                ptsm.setInt(3, eventID);
                ptsm.addBatch();
            }
            ptsm.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTicketType(TicketType ticketType){
        try(Connection con = db.getConnection()){
            String query = "DELETE FROM TicketType WHERE TypeID = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,ticketType.getTicketTypeID());
            pstm.executeUpdate(); // Executing the statement
        } catch(SQLException ex){
            System.out.println(ex);
        }
    }

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
