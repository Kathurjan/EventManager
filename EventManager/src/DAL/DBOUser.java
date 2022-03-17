package DAL;

import BE.Admin;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBOUser {
    Connection con;

    public DBOUser(Connection connection) {
        this.con = connection;
    }


    //method used to get a list of admins from the database;
    public List<Admin> getAdmins(){
        List<Admin> adminlist = FXCollections.observableArrayList();

        try(Connection connection = con) {
            String sqlStatement = "SELECT * From dbo.Admin ";

            Statement statement  = connection.createStatement();

            if (statement.execute(sqlStatement)){
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){
                    String userName = resultSet.getString("userName");
                    String userPassWord = resultSet.getString("userPassWord");
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");

                    Admin admin = new Admin(id,userName,userPassWord,name);
                    adminlist.add(admin);
                }
            }


        }
        catch (SQLException ex){
            System.out.println(ex);
            return null;
        }
        return adminlist;

    }

}
