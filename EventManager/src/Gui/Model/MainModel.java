package Gui.Model;

import BE.Admin;
import BLL.BLLFacade;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class MainModel {

    private BLLInterface bllfacade;

    public MainModel() throws SQLServerException {
        bllfacade = new BLLFacade();
    }




    public Admin verifyadmin(String username,String password){
        return bllfacade.verifyadmin( username, password);
    }

}
