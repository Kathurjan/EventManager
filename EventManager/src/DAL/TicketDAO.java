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

    private final DatabaseConnector db = new DatabaseConnector();

    public void addTempTicket(int number, int ticketTypeID) throws DALException {
        try (Connection connection = db.getConnection()) {
            String query = "INSERT INTO Ticket(TicketNumber, TicketTypeID) VALUES (?,?)";
            PreparedStatement ptsm = connection.prepareStatement(query);
            ptsm.setInt(1, number);
            ptsm.setInt(2, ticketTypeID);
            ptsm.addBatch();
            ptsm.executeBatch();
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public int selectLastest() throws DALException {
        int id = 0;
        String sqlStatement = "SELECT TOP 1 * FROM Ticket ORDER BY ID DESC";
        try (Connection con = db.getConnection()) {
            Statement statement = con.createStatement();
            if (statement.execute(sqlStatement)) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    id = rs.getInt("ID");
                }
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return id;
    }

    public void deleteTickets(List<Ticket> listToBeDeleted) throws DALException {
        try (Connection connection = db.getConnection()) {
            String query = "DELETE FROM Ticket WHERE ID = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            for (Ticket ticket : listToBeDeleted) {
                pstm.setInt(1, ticket.getID());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public void deleteSingleTicket(int id) throws DALException {
        try (Connection connection = db.getConnection()) {
            String query = "DELETE FROM Ticket WHERE ID = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.addBatch();
            pstm.executeBatch();
        } catch (SQLException throwables) {
            System.out.println(throwables);
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public List<Ticket> getAllTicketsWithTicketTypeList(List<TicketType> TicketTypes) throws DALException {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            for (TicketType tickets : TicketTypes) {
                int TicketTypeID = tickets.getTicketTypeID();
                String query = "SELECT * FROM Ticket WHERE TicketTypeID = ? ORDER BY TicketNumber DESC";
                PreparedStatement ptsm = connection.prepareStatement(query);
                ptsm.setInt(1, TicketTypeID);
                ResultSet rs = ptsm.executeQuery();
                while (rs.next()) {
                    Ticket ticket = new Ticket(rs.getInt("ID"), rs.getInt("TicketNumber"), rs.getInt("TicketTypeID"));
                    ticketList.add(ticket);
                }
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return ticketList;
    }

    public List<Ticket> getAllTicketPerType(List<TicketType> ticketTypes) throws DALException {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String query = "SELECT * FROM Ticket WHERE TicketTypeID = ? ORDER by TicketNumber DESC";
            PreparedStatement ptsm = connection.prepareStatement(query);
            for (TicketType ticketType: ticketTypes) {
                ptsm.setInt(1, ticketType.getTicketTypeID());
                ResultSet rs = ptsm.executeQuery();
                while (rs.next()) {
                    Ticket ticket = new Ticket(rs.getInt("ID"), rs.getInt("TicketNumber"), rs.getInt("TicketTypeID"));
                    ticketList.add(ticket);
                }
            }
            return ticketList;
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }
}
