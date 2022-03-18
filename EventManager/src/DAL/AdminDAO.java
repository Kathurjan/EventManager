package DAL;

import BE.Admin;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

public class AdminDAO {
    DatabaseConnector con;

    public AdminDAO(Connection connection) {
        con = new DatabaseConnector();
    }


    public Admin verifyAdmin(String username, String password) {

        Admin admin = null;

        try(Connection connection = con.getConnection()){
            String sqlquery = "SELECT * FROM Person WHERE userName= ? AND userPassWord = ?";
            PreparedStatement statement = connection.prepareStatement(sqlquery);


                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    admin = new Admin(resultSet.getInt("id"),resultSet.getString("userName"),
                            resultSet.getString("userPassWord"),resultSet.getString("email"));
                }
        }
        catch(SQLException ex){
            System.out.println("here1");
            System.out.println(ex);
            return null;
        }
        return admin;
    }
}
