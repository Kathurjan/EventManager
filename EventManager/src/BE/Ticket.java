package BE;

public class Ticket {

    private final int ID;
    private int number;
    private int ticketTypeID;

    public Ticket(int ID, int number, int ticketTypeID) {
        this.ID = ID;
        this.number = number;
        this.ticketTypeID = ticketTypeID;
    }

    public int getID() {
        return ID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }
}
