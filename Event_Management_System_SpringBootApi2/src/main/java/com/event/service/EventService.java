package com.event.service;

import java.util.List;

import com.event.beans.Event;
import com.event.beans.User;



public interface EventService {

    Event addEvent(Event event);
    List<Event> getAllEvents();
    boolean deleteEvent(String eventId);
    Event getEventById(String eventId);
    Event updateEvent(Event newEvent,String eventId);
    String registerForEvent(String eventId, User user);
    List<Event> viewMyEvents(User user);
    Event searchByName(String eventName);

}