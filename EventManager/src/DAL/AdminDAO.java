package DAL;

import BE.Admin;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

public class AdminDAO {

    private DatabaseConnector con;

    public AdminDAO(Connection connection) {
        con = new DatabaseConnector();
    }

/*
Below method is used to verify if the user login is an admin,
if the username and password matches the any of the username and passwords in the database it will verify them as
an admin and login them into the admin window.

 */
    public Admin verifyAdmin(String username, String password,int type) {

        Admin admin = null;

        try(Connection connection = con.getConnection()){
            String sqlquery = "SELECT * FROM Person WHERE userName= ? AND userPassWord = ? AND Type =?";
            PreparedStatement statement = connection.prepareStatement(sqlquery);


                statement.setString(1, username);
                statement.setString(2, password);
                statement.setInt(3,type);
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
