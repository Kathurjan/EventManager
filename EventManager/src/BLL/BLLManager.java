package BLL;

import BE.Admin;
import DAL.DALInterface;
import DAL.DALManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class BLLManager implements BLLInterface{
    private DALInterface dalManager;

    public BLLManager() {
        try {
            this.dalManager = new DALManager();
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void examplemethod() {

    }
    @Override
    public List<Admin> getAdmins(){
       return dalManager.getallAdmins();
    }

    @Override
    public void getallusers() {
        dalManager.getallusers();
    }
}
