package BE;

public class Participant extends Person {

    private String firstName;
    private String lastName;
    private int eventID;
    private int ticketID;

    public Participant(int id, String username, String password, String email, int type, String firstName, String lastName, int eventID, int ticketID) {
        super(id, username, email, password, type, firstName, lastName);
        this.eventID = eventID;
        this.ticketID = ticketID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
