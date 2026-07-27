package com.event.beans;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Event {

	@Id
    private String eventId;
    private String eventName;
    private String eventDate;
    private int availableSeats;

    public Event() {
    }

    public Event(String eventId, String eventName, String eventDate, int availableSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.availableSeats = availableSeats;
    }

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

    @Override
    public String toString() {
        return "Event ID : " + eventId +
                "\nEvent Name : " + eventName +
                "\nDate : " + eventDate +
                "\nAvailable Seats : " + availableSeats +
                "\n-------------------------";
    }



}
