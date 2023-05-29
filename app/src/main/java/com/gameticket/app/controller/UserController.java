package com.gameticket.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameticket.app.dao.EventDAO;
import com.gameticket.app.dao.TicketDAO;
import com.gameticket.app.dao.TimeSlotDAO;
import com.gameticket.app.pojo.Event;
import com.gameticket.app.pojo.TimeSlot;
import com.gameticket.app.pojo.User;
import com.gameticket.app.pojo.Ticket;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Autowired
	private EventDAO eventDao;
	
	@Autowired
	private TicketDAO ticketDao;
	
	@Autowired
	private TimeSlotDAO timeSlotDAO;
	
	@GetMapping("/user")
	public ModelAndView showUserHome(Model model) throws UnsupportedEncodingException {
		List<Event> eventList = eventDao.getAllEvents();
	    for (Event event : eventList) {
	        byte[] bytes = event.getImage();
	        byte[] encodeBase64 = Base64.encodeBase64(bytes, false);
	        String base64Encoded = new String(encodeBase64, "UTF-8");
	        event.setImageAsBase64(base64Encoded);
		 }
	    System.out.println("no.of events = " + eventList.size() + " inside User controller");
		model.addAttribute("eventList", eventList);
		return new ModelAndView("home");
	}
	
	@GetMapping("/see-details")
	public ModelAndView showEventDetails(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		Event event = eventDao.getEvent(Integer.parseInt(request.getParameter("eventid")));
		byte[] bytes = event.getImage();
        byte[] encodeBase64 = Base64.encodeBase64(bytes, false);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("image", base64Encoded);
		model.addAttribute("event", event);
		return new ModelAndView("event-details");
	}
	
	@GetMapping("/book-ticket")
	public ModelAndView showBookTicket(Model model, HttpServletRequest request) {
		Event event = eventDao.getEvent(Integer.parseInt(request.getParameter("eventid")));
	    List<TimeSlot> timeslots = event.getTimeslots();
	    TimeSlot timeslot = null;
	    
	    for(TimeSlot t : timeslots) {
	    	if(t.getTimeslotid() == Integer.parseInt(request.getParameter("timeslotid"))) {
	    		timeslot = t;
	    		break;
	    	}
	    }
		model.addAttribute("event", event);
		model.addAttribute("timeslot", timeslot);
		return new ModelAndView("book-ticket");
	}
	
	@PostMapping("/process-payment")
    public ModelAndView saveTicket(HttpServletRequest request, Model model, Ticket ticket, @RequestParam("eventid") int eventId, @RequestParam("timeslotid") int timeslotid) {
		User user = (User) request.getSession().getAttribute("user");
		Event event = eventDao.getEvent(eventId);
        List<TimeSlot> timeslots = event.getTimeslots();
        TimeSlot timeslot = null;
        if (timeslots != null) {
            for (TimeSlot t : timeslots) {
                if (t.getTimeslotid() == timeslotid) {
                    timeslot = t;
                    break;
                }
            }
        }
        
        Ticket existingTicket = ticketDao.getTicketByUserAndTimeslot(user, timeslot);
        if(existingTicket != null) {
        	if(existingTicket.getStatus().equals("CANCELLED")) {
        		// create next new ticket
        		ticket.setEvent(event);
        		ticket.setTimeslot(timeslot);
        		ticket.setUser(user);
        		ticket.setStatus("BOOKED");
                ticketDao.saveTicket(ticket);
        	} else {
        		// update existing ticket
        		existingTicket.setPositions(existingTicket.getPositions() + ticket.getPositions());
        		ticketDao.updateTicket(existingTicket);
        	}
        	// update timeslot positions after updating ticket
            timeSlotDAO.updatePositions(timeslotid, timeslot.getAvailablePositions() - existingTicket.getPositions());
        }
        else {
        	// create first new ticket
        	ticket.setEvent(event);
            ticket.setTimeslot(timeslot);
            ticket.setUser(user);
            ticket.setStatus("BOOKED");
            ticketDao.saveTicket(ticket);
         // update timeslot positions after creating new ticket
            timeSlotDAO.updatePositions(timeslotid, timeslot.getAvailablePositions() - ticket.getPositions());
        }
        
        
        
        return new ModelAndView("payment-success");
	}
	
	@GetMapping("/cart")
    public ModelAndView saveTicket(Model model, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("user");
        List<Ticket> tickets = ticketDao.getTicketByUserId(currentUser);
        model.addAttribute("tickets", tickets);
        return new ModelAndView("cart");
    }

    @GetMapping("/cancel")
    public ModelAndView cancelTicket(Model model, HttpServletRequest request, @RequestParam("ticketid") int ticketid) {
        Ticket ticket = ticketDao.getTicket(ticketid);
        int cancelledPositions = ticket.getPositions();
        TimeSlot timeslot = ticket.getTimeslot();
        int updatedPositions = timeslot.getAvailablePositions() + cancelledPositions;
        timeSlotDAO.updatePositions(timeslot.getTimeslotid(), updatedPositions);
        ticketDao.cancelTicket(ticketid);
        return new ModelAndView("redirect:/user");
    }
	
}
