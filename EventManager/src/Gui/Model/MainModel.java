package Gui.Model;

import BLL.BLLInterface;
import javafx.collections.FXCollections;

import java.util.HashMap;

public class MainModel {

    private BLLInterface bllInterface;

    public MainModel(){
        bllInterface = new BLLInterface() {


            @Override
            public void examplemethod() {}};


    }
}
