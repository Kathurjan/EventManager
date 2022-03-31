package Gui.Model;

import BE.TicketType;
import BLL.BLLInterface;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TicketTypeModel {
    private BLLInterface bllInterface;

    private ObservableList<TicketType> ticketTypeObservableList;

    public TicketTypeModel() throws SQLServerException {
        bllInterface = new BLLInterface();
    }

    

    public ObservableList<TicketType> getTicketTypes(int eventID) throws SQLServerException {
        ticketTypeObservableList = FXCollections.observableArrayList();
        ticketTypeObservableList.setAll(bllInterface.getTicketTypes(eventID));
        return ticketTypeObservableList;
    }
}
