package DAL;

import BE.Admin;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AdminDAO {
    Connection con;

    public AdminDAO(Connection connection) {
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



    // inital idea was to use this method to verify the login, to check if the username matched with the admin info input. 
    public String verifyUserName() {
        String userName = "";

        try(Connection connection = con) {
            String sqlStatement = "SELCT count(1) FROM dbo.Admin";

            Statement statement = connection.createStatement();
            if (statement.execute(sqlStatement)){
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){
                    userName = resultSet.getString("userName");

                }
            }
            return userName;


        }
        catch (SQLException ex){
            System.out.println(ex);
            return null;
        }

    }


    public String verifyUserPassWord() {
        String password = "";
        System.out.println(1);
        try(Connection connection = con) {
            String sqlStatement = "SELECT count(1) FROM dbo.Admin";
            System.out.println(sqlStatement);
            Statement statement = connection.createStatement();
            if (statement.execute(sqlStatement)){
                System.out.println(sqlStatement);
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()){
                    System.out.println(1);
                    password = resultSet.getString("userPassWord");

                }
            }
            return password;


        }
        catch (SQLException ex){
            System.out.println(ex);
            return null;
        }


    }
}
