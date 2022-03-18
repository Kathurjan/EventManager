package DAL;

import BE.Admin;
import javafx.collections.FXCollections;

import java.sql.*;
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
        System.out.println("TEST DAO");
        try(Connection connection = con) {
            String sqlStatement = "SELECT * from dbo.Person where userName=?";

            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1,userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                userName = resultSet.getString("userName");

            }




        }
        catch (SQLException ex){
            System.out.println(ex);
            return null;
        }
        if(userName.equals(""))
            System.out.println("no such userName");
        return userName;
    }


    public String verifyUserPassWord() {
        String passWord = "";
        System.out.println("TEST DAO");
        try(Connection connection = con) {
            String sqlStatement = "SELECT * from dbo.Person where userPassWord=?";

            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1,passWord);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                passWord = resultSet.getString("userName");

            }




        }
        catch (SQLException ex){
            System.out.println(ex);
            return null;
        }
        if(passWord.equals(""))
            System.out.println("no such userName");
        return passWord;
    }
}
