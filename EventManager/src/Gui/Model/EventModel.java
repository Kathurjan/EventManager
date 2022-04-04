package Gui.Model;

import BE.Event;
import BLL.BLLException;
import BLL.BLLInterface;
import BLL.BLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;

public class EventModel {
    private BLLManager BLLInterface;

    public EventModel() throws SQLServerException {
        BLLInterface = new BLLManager();
    }

    public ObservableList<Event> getAllEvents(){
        return BLLInterface.getAllEvents();
    }

    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id){
        BLLInterface.editEvent(eventName,startDate,eventLocation,price,startTime,warningLabel,id);
    }

    public double convertStringToDouble(String input) throws BLLException {
        return BLLInterface.convertTextToDouble(input);
    }

    public String convertStartTimeToOneString(String string1, String string2){
        return BLLInterface.convertStartTimeToOneString(string1,string2);
    }

    public void creatEvent() throws SQLServerException {
        BLLInterface.creatEvent();
    }

    public String[] convertStartTimeToTwoString(String string){
        return BLLInterface.convertStartTimeToTwoString(string);
    }

    public int selectLatest(){
        return BLLInterface.selectLatest();
    }

    public void deleteEventWithID(int EventID){
        BLLInterface.deleteEventWithID(EventID);
    }
}
