package com.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.beans.Event;
import com.event.beans.User;
import com.event.repository.EventRepository;
import com.event.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository repo;
    

@Autowired
private UserRepository userRepository;


    @Override
    public Event addEvent(Event event) {

    	if(repo.existsByEventId(event.getEventId())) {
    		throw new RuntimeException("Duplicate Event with id "+event.getEventDate()+"already exits ");
    	}
        return repo.save(event);
    }

    @Override
    public List<Event> getAllEvents() {

        return repo.findAll();
    }

    @Override
    public boolean deleteEvent(String eventId) {
    	boolean res = false;
		if(repo.findById(eventId)!=null) {
			repo.deleteById(eventId);
			res = true;
		}
		return res;
}
       

    @Override
    public Event getEventById(String eventId) {

        return repo.getById(eventId);
    }

    @Override
    public Event updateEvent(Event newEvent,String eventId) {
           Event res = null;
        if( repo.findById(eventId)!=null) {
        	res=repo.save(newEvent);
        }
        return res;
    }

    @Override
    @Transactional
    public String registerForEvent(String eventId, User userFromRequest) {
               
        Event event = repo.findById(eventId).orElse(null);
        if (event == null) {
            return "Event not found.";
        }      
        if (event.getAvailableSeats() <= 0) {
            return "No seats available.";
        }

        User existingUser = userRepository.getUserByEmail(userFromRequest.getEmail());
        if (existingUser == null) {
            return "User not found in Database.";
        }   
        if (existingUser.getRegisteredEvents().contains(event)) {
            return "You have already registered for this event.";
        }
       
        existingUser.getRegisteredEvents().add(event);
        event.setAvailableSeats(event.getAvailableSeats() - 1);
       
        userRepository.save(existingUser);
        repo.save(event);

        return "Registration Successful.";
    }

    @Override
    public List<Event> viewMyEvents(User user) {

        return user.getRegisteredEvents();
    }

    @Override
    public Event searchByName(String eventName) {

        return repo.findByEventName(eventName);
    }

}
