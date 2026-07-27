package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Event;
import util.JdbcConnection;

public class EventsDAOImpl implements EventsDAO {

	@Override
	public boolean addEvent(Event event) {
		String sql = "INSERT INTO events (eventId,eventName,eventDate,availableSeats) VALUES(?,?,?,?)";
		
			try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			pst.setString(1, event.getEventId());
			pst.setString(2, event.getEventName());
			pst.setString(3, event.getEventDate());
			pst.setInt(4, event.getAvailableSeats());
			int res = pst.executeUpdate();
			if (res > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> eventList = new ArrayList<Event>();
		String sql = "SELECT * FROM events";
		
			try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Event event = new Event(
						rs.getString("eventId"),
						rs.getString("eventName"),
						rs.getString("eventDate"),
						rs.getInt("availableSeats"));
				eventList.add(event);			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventList;
	}

	@Override
	public boolean deleteEvent(String eventId) {
		String sql = "DELETE FROM events WHERE eventId = ?";
		
			try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			pst.setString(1, eventId);
			int rowsAffected = pst.executeUpdate();
			if(rowsAffected>0) {
				return true;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Event getEventById(String eventId) {
     Event event = null;
		String sql = "SELECT * FROM events WHERE eventId = ? ";
		
		try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			pst.setString(1, eventId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				 event = new Event(
						rs.getString("eventId"), 
						rs.getString("eventName"), 
						rs.getString("eventDate"),
						rs.getInt("availableSeats"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public Event updateEvent(Event newEvent) {
		String sql = "UPDATE events SET eventName = ?,eventDate = ?,availableSeats = ? WHERE eventId = ?";
		
			try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			pst.setString(1, newEvent.getEventName());
			pst.setString(2,newEvent.getEventDate());
			pst.setInt(3, newEvent.getAvailableSeats());
			pst.setString(4, newEvent.getEventId());
			int res = pst.executeUpdate();
			if (res > 0) {
				return newEvent;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Event searchByName(String eventName) {
      Event event = null;
		String sql = "SELECT*FROM events WHERE eventName = ? ";
		
			try(Connection cnn = JdbcConnection.getConnection();
			PreparedStatement pst = cnn.prepareStatement(sql)){
			pst.setString(1, eventName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				 event = new Event(
						rs.getString("eventId"), 
						rs.getString("eventName"), 
						rs.getString("eventDate"),
						rs.getInt("availableSeats"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}
}