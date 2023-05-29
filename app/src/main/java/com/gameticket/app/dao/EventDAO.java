package com.gameticket.app.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.gameticket.app.pojo.Event;

import jakarta.persistence.Query;



@Component
public class EventDAO extends DAO{
	public void saveEvent(Event event) {
        try {
        begin(); //inherited from super class DAO
        getSession().persist(event);
        commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteEvent(Event event) {
        begin();
        getSession().remove(event);
        commit();
    }
    
    public void deleteEventById(int eventid) {
        begin();
        getSession().remove(getSession().get(Event.class, eventid));
        commit();
    }
    
    public Event getEvent(int eventid) {
        Event event = getSession().get(Event.class, eventid);
        return event;
    }
    
    public List<Event> getAllEvents(){
    	Query query = getSession().createQuery("select e FROM Event e", Event.class);
        Stream<Event> event = query.getResultStream();
        return event.toList();
    }
    
    
    public void updateEvent(Event event) {
        begin();
        getSession().merge(event);
        commit();
    }
}
