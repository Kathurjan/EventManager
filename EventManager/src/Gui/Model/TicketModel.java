package Gui.Model;

import BE.Ticket;
import BE.TicketType;
import BLL.BLLInterface;
import BLL.BLLManager;
import DAL.DALException;

import java.util.List;

public class TicketModel {

    private BLLManager BLLInterface;

    public TicketModel(){
        BLLInterface = new BLLManager();
    }

    public int addTempTicket(int number, int ticketTypeID) throws DALException {
        return BLLInterface.addTempTicket(number, ticketTypeID);
    }

    public void deleteSingleTicket(int id) throws DALException{
        BLLInterface.deleteSingleTicket(id);
    }

    public void deleteTickets(List<Ticket> ticketList) throws DALException{
        BLLInterface.deleteTickets(ticketList);
    }

    public List<Ticket> getAllTicketPerType(List<TicketType> ticketTypes) throws DALException{
        return BLLInterface.getAllTicketPerType(ticketTypes);
    }
}
