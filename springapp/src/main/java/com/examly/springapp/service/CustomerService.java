package com.examly.springapp.service;

import com.examly.springapp.model.Customer;

/**
 * Interface defining the contract for CustomerService.
 */
public interface CustomerService {

    /**
     * Registers a new customer in the system.
     *
     * @param newCustomer the customer to be registered.
     * @return the registered customer.
     *  @author Srikanth Moparthi
     */
    Customer registerCustomer(Customer newCustomer);

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId the ID of the customer to be retrieved.
     * @return the customer with the specified ID.
     */
    Customer getCustomerById(long customerId);

    /**
     * Retrieves a customer by their associated user ID.
     *
     * @param userId the user ID associated with the customer to be retrieved.
     * @return the customer associated with the specified user ID.
     */
    Customer getCustomerByUserId(long userId);
}