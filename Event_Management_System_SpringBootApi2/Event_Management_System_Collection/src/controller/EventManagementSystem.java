package controller;

import repository.EventsDAO;
import repository.EventsDAOImpl;
import service.EventsService;
import service.EventsServiceImpl;

public class EventManagementSystem {

    public static void main(String[] args) {

        EventsDAO dao = new EventsDAOImpl(10);

        EventsService service = new EventsServiceImpl(dao);

        EventsController controller = new EventsController(service);

        controller.start();

    }

}