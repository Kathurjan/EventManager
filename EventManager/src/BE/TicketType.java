package BE;

public class TicketType {
    private final int ticketTypeID;
    private String ticketDescription;
    private String ticketName;
    private double extraFee;

    // This is the create object for ticket types.

    public TicketType(String ticketName, String ticketDescription, double extraFee, int ID){
        this.ticketTypeID = ID;
        this.ticketDescription = ticketDescription;
        this.ticketName = ticketName;
        this.extraFee = extraFee;
    }

    public double getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(double extraFee) {
        this.extraFee = extraFee;
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
