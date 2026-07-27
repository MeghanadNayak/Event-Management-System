package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Event;
import service.EventsService;

public class EventsController
{
	private EventsService eventsService;

	public EventsController(EventsService eventsService) {
		super();
		this.eventsService = eventsService;
	}


	public void start()
	{
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			try {
			System.out.println("\n--- Event Management System ---");
			System.out.println("1. Admin Login");
			System.out.println("2. User Login");
			System.out.println("3. Exit");
			System.out.println("Enter your choice");
		
			int choice = scanner.nextInt();
			
			switch(choice)
			{
			case 1: adminMenu(scanner);
			break;
			case 2 : userMenu(scanner);
			break;
			case 3: System.out.println("Exiting system . GoodBye!");
			scanner.close();
			return;
			default: System.out.println("invalid choice , please try again !");
			}
			}catch(InputMismatchException e) {
				System.out.println("Invalid Input ! Please Enter Valid Number");
				scanner.nextLine();// isko ish liye lagaya hai ki ye loop infinite loop me na fasjaye ishiliye
			 }
			
		
		}
	}

	private void adminMenu(Scanner scanner)
	{
		while(true)
		{
			try {
			System.out.println("\n--- Welcome Admin ---");
			System.out.println("1. Add Event");
			System.out.println("2. View Events");
			System.out.println("3. Delete Event ");
			System.out.println("4. Update event");
			System.out.println("5. Logout");
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();
            scanner.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter Event ID: ");
				String id = scanner.nextLine();
				System.out.print("Enter Event Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Event Date (DD/MM/YYYY): ");
				String date = scanner.nextLine();
				System.out.print("Enter Available Seats: ");
				int seats = scanner.nextInt();
				scanner.nextLine();

				if (eventsService.addEvent(new Event(id, name, date, seats))) {
					System.out.println("Event added successfully!");
				} else {
					System.out.println("Failed to add event. Storage is full.");
				}
				break;
			case 2:
				Event[] events = eventsService.getAllEvents();
				if (events.length == 0) {
					System.out.println("No events found.");
				} else {
					for (Event event : events) {
						System.out.println(event);
					}
				}
				break;
			case 3:
			
				System.out.print("Enter Event ID to delete: ");
				String eventId = scanner.next();
				if (eventsService.deleteEvent(eventId)) {
					System.out.println("Event deleted successfully!");
				} else {
					System.out.println("Event not found.");
				}
				break;
			case 4: System.out.print("Enter Event ID: ");
		          	String newid = scanner.nextLine();
			        System.out.print("Enter Event Name: ");
			        String newname = scanner.nextLine();
			        System.out.print("Enter Event Date (DD/MM/YYYY): ");
			        String newdate = scanner.nextLine();
			        System.out.print("Enter Available Seats: ");
			        int newseats = scanner.nextInt();

			if (eventsService.updateEvent(new Event(newid, newname, newdate, newseats))!=null) {
				System.out.println("Event updated successfully!");
			} else {
				System.out.println("Failed to update event. Event with given id not found");
			}
			break;
			case 5:
				System.out.println("Logging out from Admin Menu.");
				return;
			default:
				System.out.println("Invalid choice. Try again.");
			}
			}catch(InputMismatchException e) {
				System.out.println("Invalid input ! please enter the corect number");
				scanner.nextLine();
			}
		}
	}

	private void userMenu(Scanner scanner) {
		while (true) {
			try {
			System.out.println("\n--- User Menu ---");
			System.out.println("1. View Events");
			System.out.println("2. Register for Event");
			System.out.println("3. Logout");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				Event[] events = eventsService.getAllEvents();
				if (events.length == 0) {
					System.out.println("No events found.");
				} else {
					for (Event event : events) {
						System.out.println(event);
					}
				}
				break;
			case 2:
				System.out.print("Enter Event ID to register: ");
				String eventId = scanner.next();
				System.out.println(eventsService.registerForEvent(eventId));
				break;
			case 3:
				System.out.println("Logging out from User Menu.");
				return;
			default:
				System.out.println("Invalid choice. Try again.");
			}
			}catch(InputMismatchException e) {
				System.out.println("Invalid input ! please enter the corect number");
				scanner.nextLine();
			}
		}
	}
}


