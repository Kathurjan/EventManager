package Gui.Model;

import BLL.BLLInterface;

public class MainModel {

    private BLLInterface bllInterface;

    public MainModel(){
        bllInterface = new BLLInterface() {


            @Override
            public void examplemethod() {}};


    }
}
