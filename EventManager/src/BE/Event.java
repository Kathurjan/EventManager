package BE;

import java.util.Date;

public class Event {
    private final int eventID;
    private String eventName;
    private Date startDate;
    private String eventLocation;
    private Double price;
    private String stateTime;

    public Event(int eventID, String eventname, Date startdate, String eventlocation, double price, String startTime){
        this.eventID = eventID;
        this.eventName = eventname;
        this.startDate = startdate;
        this.eventLocation = eventlocation;
        this.price = price;
        this.stateTime = startTime;
    }

    public String getStateTime() {
        return stateTime;
    }

    public void setStateTime(String stateTime) {
        this.stateTime = stateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
