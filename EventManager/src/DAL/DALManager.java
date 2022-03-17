package DAL;

import BE.Admin;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class DALManager implements DALInterface{
    private DBOUser dboUser;
    private DatabaseConnector db = new DatabaseConnector();
    public DALManager() throws SQLServerException {
        dboUser = new DBOUser(db.getConnection());
    }


    @Override
    public void getallusers() {
        dboUser.getAdmins();
    }

    @Override
    public List<Admin> getallAdmins() {
        return dboUser.getAdmins();
    }

    @Override
    public String verifyUserName() {
        return dboUser.verifyUserName();
    }
}
