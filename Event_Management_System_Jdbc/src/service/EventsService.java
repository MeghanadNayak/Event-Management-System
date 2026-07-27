package service;

import java.util.List;

import entity.Event;
import entity.User;

public interface EventsService {

    boolean addEvent(Event event);
    List<Event> getAllEvents();
    boolean deleteEvent(String eventId);
    Event getEventById(String eventId);
    Event updateEvent(Event event);
    String registerForEvent(String eventId, User user);
    List<Event> viewMyEvents(User user);
    Event searchByName(String eventName);

}