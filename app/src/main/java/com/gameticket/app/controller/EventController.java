package com.gameticket.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameticket.app.dao.EventDAO;
import com.gameticket.app.dao.TicketDAO;
import com.gameticket.app.pojo.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Controller
public class EventController {
	@Autowired
	private EventDAO eventDao;
	
	@Autowired
	private TicketDAO ticketDao;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@GetMapping("/admin")
	public ModelAndView showAdminHome(Model model) throws UnsupportedEncodingException {
		model.addAttribute("event", new Event());
		List<Event> eventList = eventDao.getAllEvents();
		for (Event event : eventList) {
	        byte[] bytes = event.getImage();
	        byte[] encodeBase64 = Base64.encodeBase64(bytes, false);
	        String base64Encoded = new String(encodeBase64, "UTF-8");
	        event.setImageAsBase64(base64Encoded);
		 }
		System.out.println(eventList.size());
		model.addAttribute("eventList", eventList);
		return new ModelAndView("admin-home");
	}
	
	@PostMapping("/add-event")
	public ModelAndView saveAdminHome(Event event, Model model, HttpServletRequest request) throws IOException, ServletException {
		InputStream is = null;
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                is = part.getInputStream();
            }
        }
        event.setImage(is.readAllBytes());
        event.setFileName(fileName);
		System.out.println(event.getEventname());
		eventDao.saveEvent(event);
		return new ModelAndView("redirect:/admin");
	}
	
	@PostMapping("/delete-event")
	public ModelAndView deleteEvent(HttpServletRequest request) {
		int eventid = Integer.parseInt(request.getParameter("eventid"));
		
		Event event = eventDao.getEvent(eventid);
		System.out.println("deleted event is " + event);
		ticketDao.deleteTicketByEvent(event);
		
		eventDao.deleteEventById(eventid);
		return new ModelAndView("redirect:/admin");
	}
	
	@GetMapping("/update-event")
	public ModelAndView getUpdateEvent(Model model, HttpServletRequest request) {
		Event event = eventDao.getEvent(Integer.parseInt(request.getParameter("eventid")));
		model.addAttribute("event", event);
		return new ModelAndView("update-event");
	}
	
	@PostMapping("/updateevent")
	public ModelAndView actionUpdateEvent(Model model, @RequestParam("eventid") int eventId, HttpServletRequest request) throws IOException, ServletException {
	    Event existingEvent = eventDao.getEvent(eventId);

	    // Set the updated values for the event
	    existingEvent.setEventname(request.getParameter("eventname"));
	    existingEvent.setDescription(request.getParameter("description"));
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date eventDate = dateFormat.parse(request.getParameter("eventdate"));
	        existingEvent.setEventdate(eventDate);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    InputStream is = null;
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                is = part.getInputStream();
            }
        }
        existingEvent.setImage(is.readAllBytes());
        existingEvent.setFileName(fileName);

	    // Save the updated event to the database
	    eventDao.updateEvent(existingEvent);
	    return new ModelAndView("redirect:/admin");
	}

	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
	
}






