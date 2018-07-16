package com.codingdojo.login.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.login.models.Event;
import com.codingdojo.login.models.Message;
import com.codingdojo.login.models.User;
import com.codingdojo.login.services.EventService;
import com.codingdojo.login.services.MessageService;
import com.codingdojo.login.services.UserService;

@Controller
@RequestMapping("/events")
public class EventController {
	private final UserService uServ;
	private final EventService eServ;
	private final MessageService mServ;
	public EventController(UserService uServ, EventService eServ, MessageService mServ) {
		this.uServ = uServ;
		this.eServ = eServ;
		this.mServ = mServ;
	}
	@RequestMapping("")
    public String home(HttpSession session, Model model, @ModelAttribute("event") Event event) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
    	User user = uServ.findUserById(id);
    	model.addAttribute("user", user);
    	List<Event> localEvents = eServ.locEvents(user.getState());
    	model.addAttribute("localEvents", localEvents);
    	List<Event> notEvents = eServ.notEvents(user.getState());
    	model.addAttribute("notEvents", notEvents);
    	List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
        		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
        model.addAttribute("states", states);
    	return "events.jsp";
        // get user from session, save them in the model and return the home page
    }
	@RequestMapping(value="",method = RequestMethod.POST)
	public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Long id = (Long) session.getAttribute("user");
			User user = uServ.findUserById(id);
	    	model.addAttribute("user", user);
	    	List<Event> localEvents = eServ.locEvents(user.getState());
	    	model.addAttribute("localEvents", localEvents);
	    	List<Event> notEvents = eServ.notEvents(user.getState());
	    	model.addAttribute("notEvents", notEvents);
	    	List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
	        		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
	        model.addAttribute("states", states);
        	return "events.jsp";
    	}
    	else {
    		eServ.createEvent(event);
    		return "redirect:/events/";
    	}
	}
	@RequestMapping("/{id}")
	public String getEvent(HttpSession session, Model model, @PathVariable("id") Long eventID, @ModelAttribute("message") Message message) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
		User user = uServ.findUserById(id);
    	model.addAttribute("user", user);
    	Event event = eServ.getEvent(eventID);
    	model.addAttribute("event",event);
    	Long joiners = eServ.countJoins(eventID);
    	model.addAttribute("joiners", joiners);
		return "event.jsp";
	}
	@RequestMapping(value="/{id}/message", method=RequestMethod.POST)
	public String postMessage(@Valid @ModelAttribute("message") Message message, @PathVariable("id") Long eventID, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Long id = (Long) session.getAttribute("user");
			User user = uServ.findUserById(id);
			model.addAttribute("user", user);
			Event event = eServ.getEvent(eventID);
			model.addAttribute("event",event);
			return "event.jsp";
		}else {
	  		mServ.createMessage(message);
	   		return "redirect:/events/"+eventID;
	   	}
	}
	@RequestMapping("/{id}/delete")
	public String deleteEvent(@PathVariable("id") Long eventID, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
		Event event = eServ.getEvent(eventID);
		User user = uServ.findUserById(id);
		if(event.getPoster().getId() != user.getId() ) {
			return "redirect:/events";
		}
		eServ.deleteEvent(eventID);
		return "redirect:/events";
	}
	@RequestMapping(value="/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model, @PathVariable("id") Long eventID) {
        if (result.hasErrors()) {
        	List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
	        		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
	        model.addAttribute("states", states);
            return "edit.jsp";
        } else {
            eServ.updateEvent(eventID,event.getName(),event.getDate(), event.getCity(),event.getState());
            return "redirect:/events/"+eventID;
        }
    }
	@RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long eventID, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
		Event event = eServ.getEvent(eventID);
		User user = uServ.findUserById(id);
		if(event.getPoster().getId() != user.getId() ) {
			return "redirect:/events";
		}
		List<String> states = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT","DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA","KS","KY","LA","ME","MD","MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH","NJ","NM",
        		"NY","NC" ,"ND" ,"OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
        model.addAttribute("states", states);
        model.addAttribute("event", event);
        return "edit.jsp";
    }
	@RequestMapping("/{id}/join")
	public String join(@PathVariable("id") Long eventID, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
		Event event = eServ.getEvent(eventID);
		User user = uServ.findUserById(id);
		eServ.joinEvent(event, user);
		return "redirect:/events";
	}
	@RequestMapping("/{id}/cancel")
	public String cancel(@PathVariable("id") Long eventID, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		if (id == null) {
			return "redirect:/";
		}
		Event event = eServ.getEvent(eventID);
		User user = uServ.findUserById(id);
		eServ.leaveEvent(event, user);
		return "redirect:/events";
	}
}
