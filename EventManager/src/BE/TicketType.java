package BE;

public class TicketType {
    private final int ticketTypeID;
    private String ticketDescription;
    private String ticketName;

    public TicketType(String name, String desc, int ID){
        this.ticketTypeID = ID;
        this.ticketDescription = desc;
        this.ticketName = name;
    }

    public int getTicketTypeID(){
        return ticketTypeID;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
}
