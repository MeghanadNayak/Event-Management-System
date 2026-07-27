package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entity.Event;
import entity.User;
import repository.UserDAO;
import service.EventsService;
import service.UserService;

public class EventsController {

	private EventsService eventsService;
	private UserService userService = new UserService();

	private static Scanner scanner = new Scanner(System.in);

	private static User loggedInUser = null;

	static {
		UserDAO.loadAdmin(new User(1, "Admin", "admin@event.in", "admin@123", "ADMIN"));
	}

	public EventsController(EventsService eventsService) {

		this.eventsService = eventsService;
	}

	public void start() {

		while (true) {
			try {
				System.out.println("\n========== EVENT MANAGEMENT SYSTEM ==========");

				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.print("Enter Choice : ");

				int choice = Integer.parseInt(scanner.nextLine());// memory se buffer ko clear kar ne keliye use kiya
																	// geya hai

				switch (choice) {
				case 1:
					registerUser();
					break;
				case 2:
					loginUser();
					break;
				case 3:
					System.out.println("Existing System.");
					return;
				default:
					System.out.println("Invalid Choice. Please Try Again");

				}
//			} catch (InputMismatchException e) {
//				System.out.println("Invalid Input ! Please enter number");
				//scanner.nextLine();
			} catch (NumberFormatException e) {
				System.out.println("Please Enter the Valid Number !");
				// scanner.nextLine();
			}

		}

	}

	private void registerUser() {

		System.out.print("Enter Name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Email : ");
		String email = scanner.nextLine();

		System.out.print("Enter Password : ");
		String password = scanner.nextLine();

		boolean result = userService.registerUser(name, email, password);

		if (result) {
			System.out.println("Registration Successful.");
		} else {
			System.out.println("Email already exists.");
		}
	}

	private void loginUser() {

		System.out.print("Enter Email : ");
		String email = scanner.nextLine();

		System.out.print("Enter Password : ");
		String password = scanner.nextLine();

		loggedInUser = userService.loginUser(email, password);
		if (loggedInUser == null) {
			System.out.println("Invalid Credentials.");
			return;
		}

		System.out.println("Welcome " + loggedInUser.getName());
		if (loggedInUser.getRole().equalsIgnoreCase("ADMIN")) {
			adminMenu();
		} else {
			userMenu();
		}
	}

	private void adminMenu() {

		while (true) {
			

				System.out.println("\n========= ADMIN MENU =========");

				System.out.println("1. Add Event");
				System.out.println("2. View Events");
				System.out.println("3. Delete Event");
				System.out.println("4. Update Event");
				System.out.println("5. Logout");
				System.out.print("Enter Choice : ");
				try {
				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:

					System.out.print("Enter Event ID : ");
					String id = scanner.nextLine();

					System.out.print("Enter Event Name : ");
					String name = scanner.nextLine();

					System.out.print("Enter Event Date : ");
					String date = scanner.nextLine();

					System.out.print("Enter Seats : ");
					int seats = Integer.parseInt(scanner.nextLine());

					boolean added = eventsService.addEvent(new Event(id, name, date, seats));

					if (added) {
						System.out.println("Event Added Successfully.");
					} else {
						System.out.println("Event Already Exists / Storage Full.");
					}
					break;
				case 2:
					List<Event> events = eventsService.getAllEvents();
					if (events.isEmpty()) {
						System.out.println("No Events Found.");
					} else {
						for (Event event : events) {
							System.out.println(event);
						}
					}
					break;

				case 3:
					System.out.print("Enter Event ID : ");
					String deleteId = scanner.nextLine();

					if (eventsService.deleteEvent(deleteId)) {
						System.out.println("Deleted Successfully.");
					} else {
						System.out.println("Event Not Found.");
					}
					break;
				case 4:
					System.out.print("Enter Event ID : ");
					String updateId = scanner.nextLine();

					System.out.print("Enter Event Name : ");
					String updateName = scanner.nextLine();

					System.out.print("Enter Event Date : ");
					String updateDate = scanner.nextLine();

					System.out.print("Enter Available Seats : ");
					int updateSeats = Integer.parseInt(scanner.nextLine());

					Event updatedEvent = eventsService
							.updateEvent(new Event(updateId, updateName, updateDate, updateSeats));

					if (updatedEvent != null) {
						System.out.println("Event Updated Successfully.");
					} else {
						System.out.println("Event Not Found.");
					}
					break;
				case 5:
					System.out.println("Admin Logout Successful.");
					
					return;
					
				default:
					System.out.println("Invalid Choice. Please Try again !");

				}
				} catch (NumberFormatException e) {
					System.out.println("Please Enter the Valid Number !");
					// scanner.nextLine();
				}

		}

	}

	private void userMenu() {

		while (true) {
			
				System.out.println("\n========== USER MENU ==========");

				System.out.println("1. View Events");
				System.out.println("2. Register For Event");
				System.out.println("3. Search Event");
				System.out.println("4. View My Events");
				System.out.println("5. Logout");

				System.out.print("Enter Choice : ");
				try {
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
				case 1:
					List<Event> events = eventsService.getAllEvents();
					if (events.isEmpty()) {
						System.out.println("No Events Available.");
					} else {
						for (Event event : events) {
							System.out.println(event);
						}

					}
					break;

				case 2:

					System.out.print("Enter Event ID : ");
					String eventId = scanner.nextLine();

					System.out.println(eventsService.registerForEvent(eventId, loggedInUser));

					break;

				case 3:

					System.out.print("Enter Event Name : ");
					String eventName = scanner.nextLine();

					Event event = eventsService.searchByName(eventName);
					if (event == null) {
						System.out.println("Event Not Found.");
					} else {
						System.out.println(event);

					}
					break;

				case 4:

					List<Event> myEvents = eventsService.viewMyEvents(loggedInUser);
					if (myEvents.isEmpty()) {
						System.out.println("You have not registered for any event.");

					} else {
						System.out.println("\n===== MY EVENTS =====");
						for (Event e : myEvents) {
							System.out.println(e);

						}

					}

					break;

				case 5:
					System.out.println("Logout Successful.");
				
					return;
				default:
					System.out.println("Invalid Choice. Please Try again !");

				}
				} catch (NumberFormatException e) {
					System.out.println("Please Enter the Valid Number !");
					// scanner.nextLine();
				}

		}

	}

}