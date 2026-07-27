package com.event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.event.beans.Event;
import com.event.beans.User;
import com.event.config.JwtUtil;
import com.event.dto.LoginDTO;
import com.event.dto.ResitoryDTO;
import com.event.repository.UserRepository;
import com.event.service.EventService;
import com.event.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private EventService eventService;
	@Autowired
    private UserRepository userDAO ;

	@Autowired
	private UserService userService;

//    public UserController(EventService eventService,
//                          UserService userService) {
//
//        this.eventService = eventService;
//        this.userService = userService;
//
//    }

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody ResitoryDTO dto) {

		User user = userService.registerUser(dto.getName(), dto.getEmail(), dto.getPassword(),dto.getRole());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO login) {

		User user = userService.loginUser(login.getEmail(), login.getPassword());

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Login Successful!");
	    response.put("token", token);
	    response.put("role", user.getRole());
	    response.put("email", user.getEmail());

	    return ResponseEntity.ok(response);
		//return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("/getAllEvent")
	public ResponseEntity<?> getAllEvent() {
		try {
			List<Event> events = eventService.getAllEvents();
			return new ResponseEntity<>(events,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/registerForEvent/{eventId}")
	public ResponseEntity<?> registerForEvent(@PathVariable String eventId, @RequestBody User user) {
            
		try {
		String result = eventService.registerForEvent(eventId, user);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return ResponseEntity.status(HttpStatus.OK).body(result);
		

	}
	
	@GetMapping("/get/{eventId}")
	public ResponseEntity<?> getEventById(@PathVariable String eventId) {
	    try {
	        Event event = eventService.getEventById(eventId);
	        
	        if (event != null) {
	            return new ResponseEntity<Event>(event, HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Event with ID " + eventId + " not found!");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error fetching event: " + e.getMessage());
	    }
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchByName(@RequestParam String eventName) {

		Event event = eventService.searchByName(eventName);
		if (event == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
		}

		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
	
	@GetMapping("/viewMyEvents")
	public ResponseEntity<?> viewMyEvents(@RequestParam String email) {
	   
	    User user = userDAO.getUserByEmail(email); 
	    
	   
	    List<Event> events = eventService.viewMyEvents(user);
	    
	    return ResponseEntity.status(HttpStatus.OK).body(events);
	}

}
