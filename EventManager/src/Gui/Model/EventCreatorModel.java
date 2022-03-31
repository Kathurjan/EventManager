package Gui.Model;

import BE.TicketType;
import BLL.BLLInterface;
import DAL.TicketDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventCreatorModel {
    private BLLInterface bllInterface;

    private ObservableList<TicketType> ticketTypeObservableList;

    public EventCreatorModel() throws SQLServerException {
        bllInterface = new BLLInterface();
    }

    public ObservableList<TicketType> getTicketTypes(int eventID){
        ticketTypeObservableList = FXCollections.observableArrayList();
        ticketTypeObservableList.setAll(bllInterface.getTicketTypes(eventID));
        return ticketTypeObservableList;
    }
}
