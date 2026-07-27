package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Event;

public class EventsDAOImpl implements EventsDAO {

    private final List<Event> events;
    private final int capacity;

    public EventsDAOImpl(int capacity) {

        this.capacity = capacity;
        this.events = new ArrayList<>(capacity);
    }

    @Override
    public boolean addEvent(Event event) {

        if (events.size() >= capacity)
            return false;

        if (getEventById(event.getEventId()) != null)// duplict event add hone se bach ne keliye
            return false;

        events.add(event);
        return true;
    }

    @Override
    public List<Event> getAllEvents() {

        return events;
    }

    @Override
    public boolean deleteEvent(String eventId) {

        Event event = getEventById(eventId);
        if (event != null) {
            events.remove(event);
            return true;
        }
        return false;
    }

    @Override
    public Event getEventById(String eventId) {

        for (Event event : events) {
            if (event.getEventId().equalsIgnoreCase(eventId))
                return event;
        }
        return null;
    }

    @Override
    public Event updateEvent(Event newEvent) {

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equalsIgnoreCase(newEvent.getEventId())) {
                events.set(i, newEvent);

                return newEvent;
            }
        }

        return null;
    }

    @Override
    public Event searchByName(String eventName) {

        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName))
                return event;
        }

        return null;
    }

}