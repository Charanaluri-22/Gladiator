package com.examly.springapp.service;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

/**
 * Interface defining the contract for UserService.
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param user the user to be registered.
     * @return the registered user.
     */
    User registerUser(User user);

    /**
     * Authenticates a user's login credentials.
     *
     * @param user the user attempting to log in.
     * @return LoginDTO with information like userId, Email and JWTToken for authentication.
     */
    LoginDTO loginUser(User user);
}