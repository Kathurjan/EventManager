package Gui.Model;

import BLL.BLLFacade;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.Date;

public class EventModel {
    private BLLInterface bllfacade;

    public EventModel() throws SQLServerException {
        bllfacade = new BLLFacade();
    }

    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime){
    bllfacade.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }
}
