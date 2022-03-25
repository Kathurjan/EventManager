package Gui.Model;

import BE.Event;
import BE.Person;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public class EventModel {
    private BLLInterface BLLInterface;

    public EventModel() throws SQLServerException {
        BLLInterface = new BLLInterface();
    }

    public ObservableList<Event> getAllEvents(){
        return BLLInterface.getAllEvents();
    }

    public void addEvent(int eventID, String eventName, Date startDate, String eventLocation, double price, String startTime){
        BLLInterface.addEvent(eventID,eventName,startDate,eventLocation,price,startTime);
    }
}
