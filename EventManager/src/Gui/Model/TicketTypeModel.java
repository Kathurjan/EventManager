package Gui.Model;

import BE.TicketType;
import BLL.BLLManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketTypeModel {
    private BLLManager bllInterface;

    private ObservableList<TicketType> ticketTypeObservableList;

    public TicketTypeModel() throws SQLServerException {
        bllInterface = new BLLManager();
    }

    public ObservableList<TicketType> getTicketTypes(int eventID) throws SQLServerException {
        ticketTypeObservableList = FXCollections.observableArrayList();
        ticketTypeObservableList.setAll(bllInterface.getTicketTypes(eventID));
        return ticketTypeObservableList;
    }

    public double convertTxtToDouble(String input){
        try {
            return bllInterface.convertTextToDouble(input);
        }
        catch (NumberFormatException ex){
            System.out.println("Staph numberformatexception");
            return 0.00;
        }
    }
}
