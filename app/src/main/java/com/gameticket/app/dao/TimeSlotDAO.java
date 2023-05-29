package com.gameticket.app.dao;

import org.springframework.stereotype.Component;

import com.gameticket.app.pojo.TimeSlot;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
public class TimeSlotDAO extends DAO{

	public void saveTimeSlot(TimeSlot timeslot) {
        try {
        begin(); //inherited from super class DAO
        getSession().persist(timeslot);
        commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteTimeSlot(TimeSlot timeslot) {
        begin();
        getSession().remove(timeslot);
        commit();
    }
    
    public void deleteTimeSlotById(int timeslotid) {
        begin();
        getSession().remove(getSession().get(TimeSlot.class, timeslotid));
        commit();
    }
    
    public TimeSlot getTimeSlot(int timeslotid) {
        TimeSlot timeslot = getSession().get(TimeSlot.class, timeslotid);
        return timeslot;
    }
    
    
    public void updatePositions(int timeslotid, int positions) {
    	begin();
        Query query = getSession().createQuery("update TimeSlot t set t.availablePositions = :availablePositions where t.timeslotid = :timeslotid");
        query.setParameter("availablePositions", positions);
        query.setParameter("timeslotid", timeslotid);
        query.executeUpdate();
        commit();
    }
	
}
