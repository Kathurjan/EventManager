package DAL;

import BE.Participant;
import BE.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    private final DatabaseConnector db = new DatabaseConnector();

    public void addParticipant(Participant participant) throws DALException {
        String sqlStatement = "INSERT INTO EventParticipant(PersonID, EventID, TicketID, HasPayed) VALUES (?,?,?,?)";
        try (Connection con = db.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(sqlStatement);
            pstm.setInt(1, participant.getID());
            pstm.setInt(2, participant.getEventID());
            pstm.setInt(3, participant.getTicketID());
            pstm.setBoolean(4, participant.getHasPayed());
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, add participant operation", throwables);
        }
    }

    public void deleteParticipant(int personID, int eventID) throws DALException{
        String query = "DELETE FROM EventParticipant WHERE PersonID = ? and EventID = ?";
        try (Connection con = db.getConnection()){
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, personID);
            pstm.setInt(2, eventID);
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        }
        catch (SQLException throwables){
            System.out.println(throwables);
            throw new DALException("The data access layer met with an error, delete participant operation", throwables);
        }
    }

    public void editParticipant(int personID, int eventID, boolean hasPayed) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "UPDATE EventParticipant set HasPayed = ? WHERE personID = ? and eventID =?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setBoolean(1, hasPayed);
            pstm.setInt(2, personID);
            pstm.setInt(3, eventID);
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, edit participant operation", throwables);
        }

    }

    public List<Participant> getAllParticipants(int eventID) throws DALException {
        List<Participant> participantList = new ArrayList<>();
        try (Connection connection = db.getConnection()){
            String query = "SELECT * FROM EventParticipant INNER JOIN Person ON EventParticipant.PersonID = Person.id WHERE EventParticipant.EventID = ? ORDER BY Person.FirstName DESC";
            PreparedStatement ptsm = connection.prepareStatement(query); // Returns a list of participants from one specified event
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()){
                Participant participant = new Participant(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getInt("Type"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getInt("EventID"),
                        rs.getInt("TicketID"), false);
                participantList.add(participant);
            }
            return participantList;
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public List<Participant> getPersonsInEvent(int eventID) throws DALException {
        List<Participant> participantList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String query = "SELECT DISTINCT Person.id, Person.FirstName, Person.LastName, Person.email, Person.Type, EventParticipant.TicketID, EventParticipant.HasPayed FROM Person INNER JOIN EventParticipant ON Person.id = EventParticipant.PersonID WHERE EventParticipant.EventID = ? AND Person.Type = 2";
            PreparedStatement ptsm = connection.prepareStatement(query); //The select distinct query insures that we do not get duplicates from having participants in multiple events
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                Participant participant = new Participant(
                        rs.getInt("id"),
                        null,
                        rs.getString("email"),
                        rs.getInt("Type"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        eventID,
                        rs.getInt("TicketID"),
                        rs.getBoolean("HasPayed"));
                participantList.add(participant);
            }
            return participantList;
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, get all persons in event operation", throwables);
        }
    }

    public List<Participant> getPersonsNotInEvent(int eventID) throws DALException {
        List<Participant> participantList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String query = "SELECT DISTINCT Person.id, Person.FirstName, Person.LastName, Person.email, Person.Type FROM Person INNER JOIN EventParticipant ON Person.id = EventParticipant.PersonID WHERE EventParticipant.EventID != ? AND Person.Type = 2";
            PreparedStatement ptsm = connection.prepareStatement(query); //The select distinct query insures that we do not get duplicates from having participants in multiple events
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                Participant participant = new Participant(
                        rs.getInt("id"),
                        null,
                        rs.getString("email"),
                        rs.getInt("Type"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        eventID,
                        -1,
                        false
                );
                participantList.add(participant);
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, get persons not in event operation", throwables);
        }
        try (Connection connection2 = db.getConnection()) {
            String query2 = "SELECT DISTINCT Person.id, Person.userName, Person.FirstName, Person.LastName, Person.email, Person.Type FROM Person WHERE NOT EXISTS (SELECT * FROM EventParticipant WHERE EventParticipant.PersonID = Person.id) AND Person.Type = 2";
            PreparedStatement ptsm2 = connection2.prepareStatement(query2); // Second query to get people who are not in the participants list yet
            ResultSet rs2 = ptsm2.executeQuery();
            while (rs2.next()) {
                Participant participant = new Participant(
                        rs2.getInt("id"),
                        null,
                        rs2.getString("email"),
                        rs2.getInt("Type"),
                        rs2.getString("FirstName"),
                        rs2.getString("LastName"),
                        eventID,
                        -1,
                        false
                );
                participantList.add(participant);
            }
        }
        catch (SQLException throwables){
            throw new DALException("The Data access layer met with an error, get persons not in event operation", throwables);
        }
        return participantList;
    }
}
