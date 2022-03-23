package Gui.Model;

import BLL.BLLInterface;

import java.util.Date;

public class EventModel {
    private BLLInterface bllfacade;

    public EventModel(BLLInterface bllfacade) {
        this.bllfacade = bllfacade;
    }

    public void addEvent(int eventID, String eventname, Date startdate, String eventlocation, double price){
        bllfacade.addEvent(eventID,startdate,eventlocation,price);
    }
}
