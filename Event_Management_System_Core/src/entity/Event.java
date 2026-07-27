package entity;

public class Event 
{
	private String eventId;
	private String eventName;
	private String eventDate;
	private int availableSeats;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Event(String eventId, String eventName, String eventDate, int availableSeats) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.availableSeats = availableSeats;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", availableSeats=" + availableSeats + "]";
	}
	
	

}

