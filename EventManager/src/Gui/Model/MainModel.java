package Gui.Model;

import BE.Admin;
import BLL.BLLFacade;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public class MainModel {

    private BLLInterface bllfacade;

    public MainModel() throws SQLServerException {
        bllfacade = new BLLFacade();
    }

    public List<Admin> getAdmins(){
       return bllfacade.getAdmins();
    }
    public String verifyUserName(){
        return bllfacade.verifyUserName();
    }

    public String verifyUserPassWord(){
        return bllfacade.verifyUserPassWord();
    }
}
