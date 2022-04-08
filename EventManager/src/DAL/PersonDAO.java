package DAL;

import BE.Admin;
import BE.Person;
import javafx.collections.FXCollections;

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
                    String email = rs.getString("email");
                    int type = rs.getInt("type");
                    Person person = new Person(id, username, email, type, null, null);// Creating a person object from the retrieved values
                    personList.add(person); // Adding the person to  list
                }
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, get all persons operation", throwables);
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
            throw new DALException("The Data access layer met with an error, add person operation", throwables);
        }
    }

    public void editPerson(Person selectedPerson, String username, String password, String email, int type) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "UPDATE Person set username = ?,password = ?,name = ?, type = ?, WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, type);
            pstm.setInt(5, selectedPerson.getID());
            pstm.executeUpdate(); // Executing the prepared statement with the specified parameters
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, edit person operation", throwables);
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


}
