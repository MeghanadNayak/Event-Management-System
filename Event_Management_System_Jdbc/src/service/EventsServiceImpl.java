package service;

import java.util.List;

import entity.Event;
import entity.User;
import repository.EventsDAO;

public class EventsServiceImpl implements EventsService {

    private EventsDAO eventsDAO;

    public EventsServiceImpl(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    @Override
    public boolean addEvent(Event event) {

        return eventsDAO.addEvent(event);
    }

    @Override
    public List<Event> getAllEvents() {

        return eventsDAO.getAllEvents();
    }

    @Override
    public boolean deleteEvent(String eventId) {

        return eventsDAO.deleteEvent(eventId);
    }

    @Override
    public Event getEventById(String eventId) {

        return eventsDAO.getEventById(eventId);
    }

    @Override
    public Event updateEvent(Event event) {

        return eventsDAO.updateEvent(event);
    }

    @Override
    public String registerForEvent(String eventId, User user) {

        Event event = eventsDAO.getEventById(eventId);

        if (event == null) {
            return "Event not found.";
        }

        if (event.getAvailableSeats() <= 0) {
            return "No seats available.";
        }
        if (user.getRegisteredEvents().contains(event)) {
            return "You have already registered for this event.";
        }

        user.getRegisteredEvents().add(event);

        event.setAvailableSeats(event.getAvailableSeats() - 1);

        return "Registration Successful.";
    }

    @Override
    public List<Event> viewMyEvents(User user) {

        return user.getRegisteredEvents();
    }

    @Override
    public Event searchByName(String eventName) {

        return eventsDAO.searchByName(eventName);
    }

}