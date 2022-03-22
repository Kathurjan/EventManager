package BE;

import java.util.Date;

public class Event {
    private final int eventID;
    private String eventName;
    private Date startDate;
    private Date endDate;
    private String eventLocation;

    public Event(String eventname, Date startdate, Date enddate, String eventlocation, int eventID){
        this.eventID = eventID;
        eventName = eventname;
        startDate = startdate;
        endDate = enddate;
        eventLocation = eventlocation;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
