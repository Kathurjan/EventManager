package Gui.Model;

import BE.Event;
import BLL.BLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.Date;

public class EventModel {
    private BLLManager BLLInterface;

    public EventModel() throws SQLServerException {
        BLLInterface = new BLLManager();
    }

    public ObservableList<Event> getAllEvents(){
        return BLLInterface.getAllEvents();
    }

    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime){
        BLLInterface.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }
}
