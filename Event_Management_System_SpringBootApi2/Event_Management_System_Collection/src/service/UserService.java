package service;

import entity.User;
import repository.UserDAO;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(String name, String email, String password) {

        if (userDAO.getUserByEmail(email) != null) {

            return false;
        }

        User user = new User(
                userDAO.generateId(),
                name,
                email,
                password,
                "USER");

        return userDAO.addUser(user);
    }

    public User loginUser(String email, String password) {

        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

}