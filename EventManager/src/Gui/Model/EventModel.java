package Gui.Model;

import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.Date;

public class EventModel {
    private BLLInterface BLLInterface;

    public EventModel() throws SQLServerException {
        BLLInterface = new BLLInterface();
    }

    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime){
        BLLInterface.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }
}
