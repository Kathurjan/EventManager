package DAL;

import BE.Person;
import BE.Ticket;
import BE.TicketType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    DatabaseConnector db = new DatabaseConnector();

    public void addTickets(List<Ticket> listToBeAdded, int TicketTypeID) throws DALException{
        try(Connection connection = db.getConnection()) {
            String query = "INSERT INTO Ticket(TicketNumber, TicketTypeID) VALUES (?,?)";
            PreparedStatement ptsm = connection.prepareStatement(query);
            for (Ticket ticket: listToBeAdded) {
                ptsm.setInt(1, ticket.getNumber());
                ptsm.setInt(2, TicketTypeID);
                ptsm.addBatch();
            }
            ptsm.executeBatch();
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public void deleteTickets(List<Ticket> listToBeDeleted) throws DALException{
            try(Connection connection = db.getConnection()) {
                String query = "DELETE FROM Ticket WHERE ID = ?";
                PreparedStatement pstm = connection.prepareStatement(query);
                for (Ticket ticket: listToBeDeleted) {
                    pstm.setInt(1, ticket.getID());
                    pstm.addBatch();
                }
                pstm.executeBatch();
            } catch (SQLException throwables) {
                throw new DALException("The Data access layer met with an error", throwables);
            }
    }

    public List<Ticket> getAllTicketsWithTicketTypeList(List<TicketType> TicketTypes) {
        List<Ticket> ticketList = new ArrayList<>();
        for (TicketType tickets: TicketTypes) {
            int TicketTypeID = tickets.getTicketTypeID();
            try (Connection connection = db.getConnection()) {
                String query = "SELECT * FROM Ticket WHERE TicketTypeID = ? ORDER BY TicketNumber DESC";
                PreparedStatement ptsm = connection.prepareStatement(query);
                ptsm.setInt(1, TicketTypeID);
                ResultSet rs = ptsm.executeQuery();
                while (rs.next()) {
                    Ticket ticket = new Ticket(rs.getInt("ID"), rs.getInt("TicketNumber"), rs.getInt("TicketTypeID"));
                    ticketList.add(ticket);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ticketList;
    }

    public List<Ticket> getAllTicketPerType(int TicketTypeID) {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = db.getConnection()){
            String query = "SELECT * FROM Ticket WHERE TicketTypeID = ? ORDER by TicketNumber DESC";
            PreparedStatement ptsm = connection.prepareStatement(query);
            ptsm.setInt(1, TicketTypeID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()){
                Ticket ticket = new Ticket(rs.getInt("ID"), rs.getInt("TicketNumber"), rs.getInt("TicketTypeID"));
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
