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

    private final DatabaseConnector db  = new DatabaseConnector();

    public void addTicketType(List<TicketType> listToBeAdded, int eventID) throws DALException{
        try(Connection connection = db.getConnection()) {
            String query = "INSERT INTO TicketType(TicketName, TicketDescription, ExtraFee, EventID) VALUES (?,?,?,?)";
            PreparedStatement ptsm = connection.prepareStatement(query);
            for (TicketType ticketType: listToBeAdded) {
                ptsm.setString(1, ticketType.getTicketName());
                ptsm.setString(2, ticketType.getTicketDescription());
                ptsm.setDouble(3, ticketType.getExtraFee());
                ptsm.setInt(4, eventID);
                ptsm.addBatch();
            }
            ptsm.executeBatch();
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public void deleteTicketType(TicketType ticketType) throws DALException{
        try(Connection con = db.getConnection()){
            String query = "DELETE FROM TicketType WHERE TypeID = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,ticketType.getTicketTypeID());
            pstm.executeUpdate(); // Executing the statement
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public List<TicketType> getTicketTypes(int eventID) throws DALException {
        List<TicketType> ticketTypeList = new ArrayList<>();
        try (Connection connection = db.getConnection()){
            String query = "SELECT * FROM TicketType WHERE EventID = ? ORDER by TicketName DESC";
            PreparedStatement ptsm = connection.prepareStatement(query);
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()){
                TicketType ticketType = new TicketType(rs.getString("TicketName"), rs.getString("TicketDescription"), rs.getDouble("ExtraFee"), rs.getInt("TypeID"));
                ticketTypeList.add(ticketType);
            }
            return ticketTypeList;
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }
}
