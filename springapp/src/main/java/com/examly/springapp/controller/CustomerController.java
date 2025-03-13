package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Customer;
import com.examly.springapp.service.serviceimpl.CustomerServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * CustomerController class to handle customer-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/customer` base path using `@RequestMapping`.
 * 
 * @author Srikanth Moparthi
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Registers a new customer.
     *
     * @param newCustomer the `Customer` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Customer` entity or an error status.
     */
    @Operation(description="Posting a new customer")
    @ApiResponse(responseCode="201",description="Status code after Posting a new Customer")
    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer newCustomer) {
        Customer added = customerService.registerCustomer(newCustomer);
        return ResponseEntity.status(201).body(added);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId the ID of the customer.
     * @return a `ResponseEntity` containing the `Customer` entity or an error status.
     */
    @Operation(description="Retrieving customer by customerId")
    @ApiResponse(responseCode="200",description="Status code after retrieving customer by customerId")
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long customerId) {
        Customer found = customerService.getCustomerById(customerId);
        return ResponseEntity.status(200).body(found);
    }

    /**
     * Retrieves a customer by user ID.
     *
     * @param userId the ID of the user.
     * @return a `ResponseEntity` containing the `Customer` entity or an error status.
     */
    @Operation(description="Retrieving customer by userId")
    @ApiResponse(responseCode="200",description="Status code after retrieving customer by userId")
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable long userId) {
        Customer found = customerService.getCustomerByUserId(userId);
        return ResponseEntity.status(200).body(found);
    }
}
