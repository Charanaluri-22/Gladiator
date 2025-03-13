package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Customer;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.CustomerService;
import com.examly.springapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * AuthController class to handle authentication-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/user` base path using `@RequestMapping`.
 * @author SreyaReddy
 */

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    /**
     * Constructor to inject the `UserService` bean.
     * Annotated with `@Autowired` to indicate that the constructor should be autowired.
     * 
     * @param userService the `UserService` implementation.
     */

    @Autowired
    private AuthController(UserService userService){
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param user the `User` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `User` entity or an error status.
     */

    @Operation(description = "Registering the User")
    @ApiResponse(responseCode = "201",description = "Status code after Successful Registration")
    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        Customer customer = new Customer();
        customer.setCustomerName(user.getCustomerName());
        customer.setInformation(user.getInformation());
        customer.setUser(registeredUser);
        customerService.registerCustomer(customer);
        return ResponseEntity.status(201).body(customer);
    }

    /**
     * Authenticates a user during login.
     *
     * @param user the `User` entity provided in the request body.
     * @return a `ResponseEntity` containing a success message or an error status.
     */

    @Operation(description="Login of User")
    @ApiResponse(responseCode="200",description="Status code after Successful Login")
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody User user){
        return ResponseEntity.status(200).body(userService.loginUser(user));
    }
}