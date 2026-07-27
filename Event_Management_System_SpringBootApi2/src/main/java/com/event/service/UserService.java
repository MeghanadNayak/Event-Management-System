package com.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.beans.User;
import com.event.repository.UserRepository;
@Service
public class UserService {

	@Autowired
    private UserRepository userDAO ;

    public User registerUser(String name, String email, String password,String role) {

        if (userDAO.getUserByEmail(email) != null) {

            return null;
        }

        User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);

        return userDAO.save(user);
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
