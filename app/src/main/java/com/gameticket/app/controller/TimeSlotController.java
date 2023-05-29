package com.gameticket.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameticket.app.dao.EventDAO;
import com.gameticket.app.dao.TimeSlotDAO;
import com.gameticket.app.pojo.Event;
import com.gameticket.app.pojo.TimeSlot;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TimeSlotController {

	@Autowired
	private EventDAO eventDao;

	@Autowired
	private TimeSlotDAO timeSlotDao;

	@GetMapping("/timeslot")
	public ModelAndView getTimeSlotPage(Model model, HttpServletRequest request) {
		model.addAttribute("eventid", request.getParameter("eventid"));
		Event event = eventDao.getEvent(Integer.parseInt(request.getParameter("eventid")));
		model.addAttribute("event", event);
		return new ModelAndView("create-timeslot");
	}

	@PostMapping("/savetimeslot")
	public ModelAndView addTimeSlot(TimeSlot timeslot, Model model, @RequestParam("eventid") int eventId) {

		Event event = eventDao.getEvent(eventId);
		event.getTimeslots().add(timeslot);
		timeslot.setEvent(event);

		timeSlotDao.saveTimeSlot(timeslot);
		return new ModelAndView("redirect:/admin");

	}

}
