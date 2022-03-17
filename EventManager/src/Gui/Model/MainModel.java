package Gui.Model;

import BLL.BLLFacade;
import BLL.BLLInterface;

public class MainModel {

    private BLLInterface bllInterface;

    public MainModel(){
        bllInterface = new BLLFacade();
    }
}
