package BE;

public class Participant extends Person {

    private int eventID;
    private int ticketID;
    private boolean hasPayed;

    public Participant(int id, String username, String email, int type, String firstName, String lastName, int eventID, int ticketID, Boolean hasPayed) {
        super(id, username, email, type, firstName, lastName);
        this.eventID = eventID;
        this.ticketID = ticketID;
        this.hasPayed = hasPayed;
    }

    public boolean getHasPayed() {
        return hasPayed;
    }

    public void setHasPayed(boolean hasPayed) {
        this.hasPayed = hasPayed;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
}
