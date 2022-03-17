package Gui.Model;

import BE.Admin;
import BLL.BLLInterface;
import BLL.BLLManager;

import java.util.List;

public class MainModel {

    private BLLInterface bllManager;

    public MainModel(){
        bllManager = new BLLManager();




    }

    public List<Admin> getAdmins(){
       return bllManager.getAdmins();
    }


    public String verifyUserName(){
        return bllManager.verifyUserName();
    }
}
