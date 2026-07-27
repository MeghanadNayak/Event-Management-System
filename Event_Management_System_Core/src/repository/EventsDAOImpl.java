package repository;

import entity.Event;

public class EventsDAOImpl implements EventsDAO {
	
	private Event[] events;
	private int eventCount;
	
	public EventsDAOImpl(int capacity)
	{
		this.events=new Event[capacity];
		this.eventCount=0;
	}

	@Override
	public boolean addEvent(Event event) {
		
		
		 if(eventCount>events.length)
		{
			return false;
		}
		else
		{
			events[eventCount++]=event;
		}
		
		return true;
	}

	@Override
	public Event[] getAllEvents() {
		
		Event[] availableEvents = new Event[eventCount];
		System.arraycopy(events, 0, availableEvents, 0, eventCount);
		if(events.length<=0) {
			return null;
		}
		else
		 return availableEvents;
	}

	@Override
	public boolean deleteEvent(String eventId) {
		
		for(int i=0; i< eventCount; i++)
		{
			if(events[i].getEventId().equals(eventId))
			{
				for(int j=i;j< eventCount-1;j++)
				{
					events[j] = events[j+1]; // shifting elements after deleting
				}
				events[--eventCount]=null;// clearing the last element
				return true;
			}
		}
		return false;
	}

	@Override
	public Event getEventById(String eventId) {
		Event res = null;
		for(int i =0 ;i < eventCount ; i++)
		{
			if(events[i].getEventId().equals(eventId))
			{
				res=events[i];
			}
		}
		return res;
	}

	@Override
	public Event updateEvent(Event newEvent) {
		Event res = null;
		for(int i =0 ;i < eventCount ; i++)
		{
			if(events[i].getEventId().equals(newEvent.getEventId()))
			{
				events[i]=newEvent;
				res=newEvent;
			}
		}
		return res;
	}

}