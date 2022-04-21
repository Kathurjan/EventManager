package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

// This is where we parse through all our data.

public class DALManager implements DALInterface{

    private PersonDAO personDAO;
    private EventDAO eventDAO;
    private TicketTypeDAO ticketTypeDAO;
    private ParticipantDAO participantDAO;
    private TicketDAO ticketDAO;

    public DALManager() {
        personDAO = new PersonDAO();
        eventDAO = new EventDAO();
        ticketTypeDAO = new TicketTypeDAO();
        participantDAO = new ParticipantDAO();
        ticketDAO = new TicketDAO();
    }

    @Override
    public List<Person> getAllPerson() throws DALException {
        return personDAO.getAllPerson();
    }

    @Override
    public ObservableList<Event> getAllEvents() throws DALException {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<TicketType> getTicketTypes(int eventID) throws DALException {
        return ticketTypeDAO.getTicketTypes(eventID);
    }

    @Override
    public void addPerson(String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        personDAO.addPerson(username, password, email, type, firstName, lastName);
    }

    @Override
    public void deletePerson(Person selectedPerson) throws DALException {
        personDAO.deletePerson(selectedPerson);
    }

    @Override
    public void editPerson(Person selectedPerson, String username, String password, String email, int type, String firstName, String lastName) throws DALException {
        personDAO.editPerson(selectedPerson, username, password, email, type, firstName, lastName);
    }

    @Override
    public Person verifyAdmin(String username, String password) throws DALException {
        return personDAO.verifyAdmin(username, password);
    }

    @Override
    public void editEvent(String eventName, Date startDate, String eventLocation, double price, String startTime, String warningLabel, int id) {
        eventDAO.editEvent(eventName,startDate,eventLocation,price,startTime,warningLabel,id);
    }

    @Override
    public void addTicketTypes(List<TicketType> simpleList, int eventID) throws DALException{
        ticketTypeDAO.addTicketType(simpleList, eventID);
    }

    @Override
    public void creatEvent() throws DALException {
        eventDAO.creatEvent();
    }

    @Override
    public void deleteTicketType(TicketType ticketType) throws DALException {
        ticketTypeDAO.deleteTicketType(ticketType);
    }

    @Override
    public int selectLatest() throws DALException {
        return eventDAO.selectLastest();
    }

    @Override
    public void deleteEventWithID(int EventID) throws DALException {
        eventDAO.deleteEventWithID(EventID);
    }

    @Override
    public List<Participant> getAllParticipants(int EventID) throws DALException{
        return participantDAO.getAllParticipants(EventID);
    }

    @Override
    public List<Participant> getPersonsNotInEvent(int EventID) throws DALException{
        return participantDAO.getPersonsNotInEvent(EventID);
    }

    @Override
    public List<Participant> getPersonsInEvent(int EventID) throws DALException{
        return participantDAO.getPersonsInEvent(EventID);
    }

    @Override
    public int addTempTicket(int ticketTypeID) throws DALException {
        ticketDAO.addTempTicket(ticketTypeID);
        return ticketDAO.selectLastest();
    }

    @Override
    public void deleteSingleTicket(int id) throws DALException{
        ticketDAO.deleteSingleTicket(id);
    }

    @Override
    public void deleteTickets(List<Ticket> ticketList) throws DALException{
        ticketDAO.deleteTickets(ticketList);
    }

    @Override
    public void addParticipant(Participant participant) throws DALException{
        participantDAO.addParticipant(participant);
    }

    @Override
    public void deleteParticipant(int personID, int eventID) throws DALException{
        participantDAO.deleteParticipant(personID,eventID);
    }

    @Override
    public void editParticipant(int personID, int eventID, boolean hasPayed) throws DALException{
        participantDAO.editParticipant(personID,eventID,hasPayed);
    }

    @Override
    public List<Ticket> getAllTicketPerType(List<TicketType> ticketTypes) throws DALException{
        return ticketDAO.getAllTicketPerType(ticketTypes);
    }
}
