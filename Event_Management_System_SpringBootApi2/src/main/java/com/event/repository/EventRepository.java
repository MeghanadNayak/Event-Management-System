package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.beans.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, String>{

	Event findByEventName(String eventName);
	boolean existsByEventId(String eventId);

}
