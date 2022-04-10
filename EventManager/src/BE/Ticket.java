package BE;

public class Ticket {

    private final int ID;
    private int ticketTypeID;

    public Ticket(int ID, int ticketTypeID) {
        this.ID = ID;
        this.ticketTypeID = ticketTypeID;
    }

    public int getID() {
        return ID;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }
}
