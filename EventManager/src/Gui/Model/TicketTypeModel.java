package Gui.Model;

import BE.TicketType;
import BLL.BLLException;
import BLL.BLLManager;
import DAL.DALException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TicketTypeModel {
    private BLLManager bllInterface;

    private ObservableList<TicketType> ticketTypeObservableList;

    public TicketTypeModel()  {
        bllInterface = new BLLManager();
    }

    public ObservableList<TicketType> getTicketTypes(int eventID) throws DALException {
        ticketTypeObservableList = FXCollections.observableArrayList();
        ticketTypeObservableList.setAll(bllInterface.getTicketTypes(eventID));
        return ticketTypeObservableList;
    }

    public void addTicketTypes(ObservableList<TicketType> listToBeAdded, int eventID) throws DALException {
        bllInterface.addTicketTypes(listToBeAdded, eventID);
    }

    public double convertTxtToDouble(String input) throws BLLException {
        return bllInterface.convertTextToDouble(input);
    }

    public void deleteTicketType(TicketType ticketType) throws DALException {
        bllInterface.deleteTicketType(ticketType);
    }
}
