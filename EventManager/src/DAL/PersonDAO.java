package DAL;

import BE.Admin;
import BE.Participant;
import BE.Person;
import BE.TicketType;
import javafx.collections.FXCollections;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private final DatabaseConnector db = new DatabaseConnector();

    public List<Person> getAllPerson() throws DALException {
        List<Person> personList = FXCollections.observableArrayList();
        try (Connection con = db.getConnection()) {
            String sqlStatement = "SELECT * FROM Person";
            Statement statement = con.createStatement();
            if (statement.execute(sqlStatement)) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("userName");
                    String password = rs.getString("userPassWord");
                    String email = rs.getString("email");
                    int type = rs.getInt("type");
                    Person person = new Person(id, username, email, type, null, null);// Creating a person object from the retrieved values
                    personList.add(person); // Adding the person to  list
                }
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return personList;
    }

    public void addPerson(String username, String password, String email, int type) throws DALException {
        String sqlStatement = "INSERT INTO Person(userName, userPassWord, email, Type) VALUES (?,?,?,?)";
        try (Connection con = db.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(sqlStatement);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, type);
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public Person editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "UPDATE Person set username = ?,password = ?,name = ?, type = ?, WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, type);
            pstm.setInt(5, selectedPerson.getID());
            pstm.executeUpdate(); // Executing the prepared statement with the specified parameters
            return new Person(selectedPerson.getID(), username, email, type, null, null);
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public void deletePerson(Person selectedPerson) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "DELETE FROM Person WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1, selectedPerson.getID());
            pstm.executeUpdate(); // Executing the statement
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

    public Admin verifyAdmin(String username, String password, int type) throws DALException {
        Admin admin = null;
        try (Connection connection = db.getConnection()) {
            String sqlquery = "SELECT * FROM Person WHERE userName= ? AND userPassWord = ? AND Type =?";
            PreparedStatement statement = connection.prepareStatement(sqlquery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, type);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                admin = new Admin(resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("email"),
                        resultSet.getInt("type"),
                        null,
                        null,
                        resultSet.getString("userPassWord"));
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return admin;
    }

    public List<Person> getPersonsNotInEvent(int eventID) throws DALException {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String query = "SELECT DISTINCT Person.id, Person.userName, Person.LastName, Person.email, Person.Type FROM Person INNER JOIN EventParticipant ON Person.id = EventParticipant.PersonID WHERE EventParticipant.EventID != ? AND Person.Type = 2";
            PreparedStatement ptsm = connection.prepareStatement(query); //The select distinct query insures that we do not get duplicates from having participants in multiple events
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("id"),
                        null,
                        rs.getString("email"),
                        rs.getInt("Type"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                personList.add(person);
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
        try (Connection connection2 = db.getConnection()) {
            String query2 = "SELECT DISTINCT Person.id, Person.userName, Person.FirstName, Person.LastName, Person.email, Person.Type FROM Person WHERE NOT EXISTS (SELECT * FROM EventParticipant WHERE EventParticipant.PersonID = Person.id) AND Person.Type = 2";
            PreparedStatement ptsm2 = connection2.prepareStatement(query2); // Second query to get people who are not in the participants list yet
            ResultSet rs2 = ptsm2.executeQuery();
            while (rs2.next()) {
                Person person = new Person(
                        rs2.getInt("id"),
                        null,
                        rs2.getString("email"),
                        rs2.getInt("Type"),
                        rs2.getString("FirstName"),
                        rs2.getString("LastName"));
                System.out.println("Lmao loop");
                personList.add(person);
            }
        }
        catch (SQLException throwables){
            throw new DALException("The Data access layer met with an error", throwables);
        }
        return personList;
    }

    public List<Person> getPersonsInEvent(int eventID) throws DALException {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String query = "SELECT DISTINCT Person.id, Person.userName, Person.LastName, Person.email, Person.Type FROM Person INNER JOIN EventParticipant ON Person.id = EventParticipant.PersonID WHERE EventParticipant.EventID = ? AND Person.Type = 2";
            PreparedStatement ptsm = connection.prepareStatement(query); //The select distinct query insures that we do not get duplicates from having participants in multiple events
            ptsm.setInt(1, eventID);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("id"),
                        null,
                        rs.getString("email"),
                        rs.getInt("Type"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                personList.add(person);
            }
            return personList;
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }

}
