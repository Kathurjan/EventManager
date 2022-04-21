package DAL;

import BE.Person;
import javafx.collections.FXCollections;

import java.sql.*;
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
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    Person person = new Person(id, username, email, type, firstName, lastName);// Creating a person object from the retrieved values
                    personList.add(person); // Adding the person to  list
                }
            }
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, get all persons operation", throwables);
        }
        return personList;
    }

    public void addPerson(String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        String sqlStatement = "INSERT INTO Person(userName, userPassWord, email, Type, FirstName, LastName) VALUES (?,?,?,?,?,?)";
        try (Connection con = db.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(sqlStatement);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, type);
            pstm.setString(5, firstName);
            pstm.setString(6, lastName);
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error, add person operation", throwables);
        }
    }

    public void editPerson(Person selectedPerson, String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        try (Connection con = db.getConnection()) {
            String query = "UPDATE Person set username = ?,password = ?,email = ?, type = ?, firstname = ?, lastname = ?, WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, type);
            pstm.setString(5, firstName);
            pstm.setString(6, lastName);
            pstm.setInt(7, selectedPerson.getID());
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

    public Person verifyAdmin(String username, String password) throws DALException {
        try (Connection connection = db.getConnection()) {
            String sqlquery = "SELECT * FROM Person WHERE userName= ? AND userPassWord = ?";
            PreparedStatement statement = connection.prepareStatement(sqlquery);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstname = resultSet.getString("FirstName");
                String lastname = resultSet.getString("LastName");
                int type = resultSet.getInt("type");
                Person person = new Person(id, username, email, type, firstname, lastname);// Creating a person object from the retrieved values
                return person;
            }
            else return null;
        } catch (SQLException throwables) {
            throw new DALException("The Data access layer met with an error", throwables);
        }
    }


}
