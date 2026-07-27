package repository;

import java.util.List;

import entity.Event;

public interface EventsDAO {

    boolean addEvent(Event event);
    List<Event> getAllEvents();
    boolean deleteEvent(String eventId);
    Event getEventById(String eventId);
    Event updateEvent(Event event);
    Event searchByName(String eventName);

}