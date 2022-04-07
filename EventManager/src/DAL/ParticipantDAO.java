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
                        rs.getInt("TicketID"));
                participantList.add(participant);
            }
            return participantList;
        } catch(SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }
}
