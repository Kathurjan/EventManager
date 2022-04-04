package BE;

import java.sql.Date;

// This is the create object for events.

public class Event {
    private final int eventID;
    private String eventName;
    private Date startDate;
    private String eventLocation;
    private Double price;
    private String stateTime;
    private String warningLabel;

    public Event(int eventID, String eventname, Date startdate, String eventlocation, Double price, String startTime, String warningLabel){
        this.eventID = eventID;
        this.eventName = eventname;
        this.startDate = startdate;
        this.eventLocation = eventlocation;
        this.price = price;
        this.stateTime = startTime;
        this.warningLabel = warningLabel;
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

    public String getWarningLabel() {
        return warningLabel;
    }

    public void setWarningLabel(String warningLabel) {
        this.warningLabel = warningLabel;
    }
}
