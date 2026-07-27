package repository;

import entity.Event;

public interface EventsDAO
{
	boolean addEvent(Event event);
	Event[] getAllEvents();
	boolean deleteEvent(String eventId);
	Event getEventById(String eventId);
	Event updateEvent(Event newEvent);

}

