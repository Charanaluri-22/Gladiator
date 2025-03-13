package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.model.Customer;

/**
 * @author Srikanth Moparthi
 * Repository interface for managing `Customer` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    /**
     * Finds the customer associated with the specified user ID.
     *
     * @param userId the ID of the user.
     * @return the `Customer` entity associated with the user ID.
     */
    @Query("SELECT c FROM Customer c WHERE c.user.userId = :userId")
    Optional<Customer> getCustomerByUserId(long userId);

}
