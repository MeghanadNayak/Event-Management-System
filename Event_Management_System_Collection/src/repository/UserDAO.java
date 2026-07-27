package repository;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDAO {

    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    public static void loadAdmin(User admin) {

        if (users.isEmpty()) {
            users.add(admin);
            idCounter++;
        }
    }

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    public User getUserByEmail(String email) {

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }

        return null;
    }

    public int generateId() {

        return idCounter++;
    }

}