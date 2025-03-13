package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.model.Cart;

/**
 * @author Aluri Charan
 * Repository interface for managing `Cart` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
public interface CartRepo extends JpaRepository<Cart, Long> {

    /**
     * Finds the cart associated with the specified user ID.
     *
     * @param userId the ID of the user.
     * @return an Optional containing the `Cart` entity if found, or an empty Optional if not.
     */
    @Query("SELECT c FROM Cart c JOIN c.customer cu WHERE cu.user.id = :userId")
    Optional<Cart> findByUserId(long userId);

    /**
     * Finds the cart associated with the specified customer ID.
     *
     * @param customerId the ID of the customer.
     * @return an Optional containing the `Cart` entity if found, or an empty Optional if not.
     */
    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    Optional<Cart> findByCustomerId(long customerId);
}
