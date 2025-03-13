package com.examly.springapp.service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.service.CustomerService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing customer-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `CustomerService` interface to provide specific business
 * logic.
 * 
 * @author Srikanth Moparthi
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    /**
     * Registers a new customer.
     * 
     * @param newCustomer the new customer entity to be registered.
     * @return the saved customer entity.
     * @throws EntityExistsException if a customer with the same ID already exists.
     */
    @Override
    public Customer registerCustomer(Customer newCustomer) {
        Long id = newCustomer.getCustomerId();
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (!optionalCustomer.isPresent()) {
            return customerRepo.save(newCustomer);
        }
        throw new EntityExistsException("Customer with ID " + id + " already Exists.");
    }

    /**
     * Retrieves a customer by their ID.
     * 
     * @param customerId the ID of the customer.
     * @return the found customer entity.
     * @throws EntityNotFoundException if the customer with the given ID is not
     *                                 found.
     */
    @Override
    public Customer getCustomerById(long customerId) {
        Optional<Customer> foundCustomer = customerRepo.findById(customerId);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        }
        throw new EntityNotFoundException("Customer with ID " + customerId + " not found.");
    }

    /**
     * Retrieves a customer by their user ID.
     * 
     * @param userId the ID of the user.
     * @return the found customer entity.
     * @throw EntityNotFoundException if the Customer with userId not found.
     */
    @Override
    public Customer getCustomerByUserId(long userId) {
        Optional<Customer> foundCustomer = customerRepo.getCustomerByUserId(userId);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        }
        throw new EntityNotFoundException("Customer with userId: " + userId + " not found");
    }

}
