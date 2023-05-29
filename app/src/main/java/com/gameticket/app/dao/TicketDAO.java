package com.gameticket.app.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.gameticket.app.pojo.Event;
import com.gameticket.app.pojo.Ticket;
import com.gameticket.app.pojo.TimeSlot;
import com.gameticket.app.pojo.User;

import jakarta.persistence.Query;

@Component
public class TicketDAO extends DAO {
 
	public void saveTicket(Ticket ticket) {
        try {
        begin(); //inherited from super class DAO
        getSession().persist(ticket);
        commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
	
	public void updateTicket(Ticket ticket){
		begin();
        getSession().merge(ticket);
        commit();
	}
    
    public void deleteTicket(Ticket ticket) {
        begin();
        getSession().remove(ticket);
        commit();
    }
    
    public void deleteTicketById(int ticketid) {
        begin();
        getSession().remove(getSession().get(Ticket.class, ticketid));
        commit();
    }
    
    public Ticket getTicket(int ticketid) {
        Ticket ticket = getSession().get(Ticket.class, ticketid);
        return ticket;
    }
    
    public List<Ticket> getTicketByUserId(User user) {
        Query query = getSession().createQuery("select t FROM Ticket t where t.user = :user", Ticket.class);
        query.setParameter("user", user);
        Stream<Ticket> tickets = query.getResultStream();
        return tickets.toList();
    }
    
    public void deleteTicketByEvent(Event event) {
    	begin();
    	Query query = getSession().createQuery("delete FROM Ticket t where t.event = :event");
        query.setParameter("event", event);
        query.executeUpdate();
        commit();
    }

    public void cancelTicket(int ticketid) {
        begin();
        Query query = getSession().createQuery("update Ticket t set t.status = :status where t.ticketid = :ticketid");
        query.setParameter("status", "CANCELLED");
        query.setParameter("ticketid", ticketid);
        query.executeUpdate();
        commit();
    }
    
    public Ticket getTicketByUserAndTimeslot(User user, TimeSlot timeslot) {
        Query query = getSession().createQuery("select t FROM Ticket t where t.user = :user and t.timeslot = :timeslot", Ticket.class);
        query.setParameter("user", user);
        query.setParameter("timeslot", timeslot);
        List<Ticket> tickets = query.getResultList();
        if (tickets.size() > 0) {
            return tickets.get(0);
        } else {
            return null;
        }
    }


    
    
}
