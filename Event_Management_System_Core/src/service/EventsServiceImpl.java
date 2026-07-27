package service;

import entity.Event;
import repository.EventsDAO;
import util.EventsFullException;

public class EventsServiceImpl implements EventsService {



	private EventsDAO eventsDAO;

	public EventsServiceImpl(EventsDAO eventsDAO) {
		super();
		this.eventsDAO = eventsDAO;
	}


	@Override
	public boolean addEvent(Event event) {

		boolean res = eventsDAO.addEvent(event);

		if(res==false)
		{
			throw new EventsFullException("no more events accepted");
		}
		return res;

	}

	@Override
	public Event[] getAllEvents() {

		return eventsDAO.getAllEvents();
	}

	@Override
	public boolean deleteEvent(String eventId) {
		if(getEventById(eventId)!= null)
		{
			return eventsDAO.deleteEvent(eventId);
		}
		return false;
	}

	@Override
	public Event getEventById(String eventId) {

		return eventsDAO.getEventById(eventId);
	}

	@Override
	public Event updateEvent(Event newEvent) {

		if(getEventById(newEvent.getEventId())!= null)
		{
			return eventsDAO.updateEvent(newEvent);
		}
		return null;
	}

	public String registerForEvent(String eventId)
	{
		Event event = eventsDAO.getEventById(eventId);
		if(event == null)
		{
			return "Event not found !";

		}

		if(event.getAvailableSeats() <=0)
		{
			return "No seats available !";

		}
		event.setAvailableSeats(event.getAvailableSeats()-1);
		return "Successfully registerd for the event !";
	}
}

