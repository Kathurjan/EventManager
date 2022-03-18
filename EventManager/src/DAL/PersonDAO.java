package DAL;

import BE.Person;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

public class PersonDAO {

    Connection db;

    public PersonDAO(Connection connection) {
        this.db = connection;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = FXCollections.observableArrayList();
        try (Connection con = db) {
            String sqlStatement = "SELECT * FROM Person";
            Statement statement = con.createStatement();
            if (statement.execute(sqlStatement)) {
                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");

                    Person person = new Person(id, username,password, email);// Creating a movie object from the retrieved values
                    personList.add(person); // Adding the movie to  list
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return personList;
    }

    public void addPerson(String username, String password, String email) {
        String sqlStatement = "INSERT INTO Movie(username, password, email) VALUES (?,?,?)";
        try(Connection con = db){
            PreparedStatement pstm = con.prepareStatement(sqlStatement);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.addBatch(); // Adding to the statement
            pstm.executeBatch(); // Executing the added parameters, and  executing the statement
        } catch(SQLException ex) {
            System.out.println(ex);
        }
        Person person = new Person(1,username,password,email); // Creating a new movie object
    }

    public Person editPerson(Person selectedPerson, String username, String password, String email) {
        try (Connection con = db) {
            String query = "UPDATE Person set username = ?,password = ?,name = ? WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.executeUpdate();
            return new Person(selectedPerson.getID(),username,password,email);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void deletePerson(Person selectedPerson){
        try(Connection con = db){
            String query = "DELETE FROM Person WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setInt(1,selectedPerson.getID());
            pstm.executeUpdate(); // Executing the statement
        } catch(SQLException ex){
            System.out.println(ex);
        }
    }

}
