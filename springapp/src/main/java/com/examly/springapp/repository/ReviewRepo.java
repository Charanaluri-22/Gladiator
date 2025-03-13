package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.model.Review;

/**
 * @author Priyanjali
 * Repository interface for managing `Review` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
public interface ReviewRepo extends JpaRepository<Review, Long> {
    
    /**
     * Finds all reviews associated with the specified user ID.
     *
     * @param userId the ID of the user.
     * @return a list of `Review` entities associated with the user ID.
     */
    @Query("select r from Review r where r.customer.user.userId =?1")
    List<Review> findByUserId(long userId);
}
