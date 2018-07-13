package com.codingdojo.login.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.login.models.Event;
import com.codingdojo.login.models.User;
import com.codingdojo.login.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eRepo;
	public EventService(EventRepository eRepo) {
		this.eRepo = eRepo;
	}
	public List<Event> allEvents(){
		return eRepo.findAll();
	}
	public Event createEvent(Event p) {
		return eRepo.save(p);
	}
	public Event getEvent(Long id) {
		Optional<Event> e = eRepo.findById(id);
		if(e.isPresent()) {
    		return e.get();
		}
		else {
			return null;
		}
	}
	public void deleteEvent(Long id) {
		Optional<Event> e = eRepo.findById(id);
		if(e.isPresent()) {
    		eRepo.delete(e.get());
		}
	}
	public List<Event> locEvents(String state){
		return eRepo.findEventByState(state);
	}
	public List<Event> notEvents(String state){
		return eRepo.findEventByNotState(state);
	}
	public Event updateEvent(Long id, String name, Date date, String city, String state) {
    	Optional<Event> e = eRepo.findById(id);
    	if(e.isPresent()) {
    		e.get().setName(name);
    		e.get().setDate(date);
    		e.get().setCity(city);
    		e.get().setState(state);
    		return eRepo.save(e.get());
    		
    	} else {
    		return null;
    	}
    }
	public void joinEvent(Event e, User u) {
		List<User> joiners = e.getJoiners();
		joiners.add(u);
		e.setJoiners(joiners);
		eRepo.save(e);
	}
	public void leaveEvent(Event e, User u) {
		List<User> joiners = e.getJoiners();
		joiners.remove(u);
		e.setJoiners(joiners);
		eRepo.save(e);
	}
	public Long countJoins(Long id) {
		return eRepo.countJoiners(id);
	}
}
