package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.examly.springapp.model.Orders;

/**
 * @author Shantanu
 * Repository interface for managing `Orders` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
public interface OrderRepo extends JpaRepository<Orders, Long> {   

    /**
     * Finds the order associated with the specified customer ID.
     *
     * @param customerId the ID of the customer.
     * @return the `Orders` entity associated with the customer ID.
     */
    @Query("select o from Orders o where o.customer.customerId =?1")
    public List<Orders> findByCustomerId(long customerId);  
    
    List<Orders> findByStatus(String status);
}