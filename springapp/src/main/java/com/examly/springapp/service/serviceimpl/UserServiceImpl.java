package com.examly.springapp.service.serviceimpl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing user-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `UserService` interface to provide specific business logic.
 * Implements the `UserDetailsService` interface for Spring Security
 * authentication.
 * 
 * @author [sreya reddy]
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    /**
     * Constructor to inject the necessary beans.
     * 
     * @param userRepo              the repository for managing `User` entity data
     *                              access.
     * @param passwordEncoder       the encoder for user passwords.
     * @param authenticationManager the authentication manager for user
     *                              authentication.
     * @param jwtUtils              the utility for handling JSON Web Tokens.
     */
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Registers a new user.
     * 
     * @param user the new user entity to be registered.
     * @return the saved user entity.
     * @throws EntityExistsException if a user with the same email or ID already
     *                               exists.
     */
    @Override
    public User registerUser(User user) {
        Optional<User> foundUserByEmail = userRepo.findByEmail(user.getEmail());
        if (foundUserByEmail.isPresent()) {
            throw new EntityExistsException("User With Email: " + user.getEmail() + " already exists.");
        }
        long userId = user.getUserId();
        Optional<User> foundUser = userRepo.findById(userId);
        if (!foundUser.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        throw new EntityExistsException("User With ID: " + userId + " already exists.");
    }

    /**
     * Authenticates and logs in a user.
     * 
     * @param user the user entity containing login credentials.
     * @return a JWT token if authentication is successful.
     * @throws EntityNotFoundException if the user is not found or the password is
     *                                 invalid.
     */
    @Override
    public LoginDTO loginUser(User user) {
        String email = user.getEmail();
        Optional<User> foundUser = userRepo.findByEmail(email);
        if (foundUser.isEmpty()) {
            throw new EntityNotFoundException("User With Email: " + email + " Not Found.");
        }
        User existingUser = foundUser.get();
        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            String token = jwtUtils.generateToken(new UserPrinciple(existingUser));
            return new LoginDTO(existingUser.getUserId(), existingUser.getEmail(), existingUser.getRole(), token);
        }
        throw new EntityExistsException("Invalid Credentials");
    }

    /**
     * Loads user details by username (email) for authentication.
     * 
     * @param email the email of the user.
     * @return the user details for authentication.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> founduser = userRepo.findByEmail(email);
        if (founduser.isPresent()) {
            User user = founduser.get();
            return new UserPrinciple(user);
        }
        throw new EntityNotFoundException("User with not found with email " + email);

    }
}
