package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.User;

/**
 * @author ur-name
 * Repository interface for managing `User` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 * @author SreyaReddy
 */
public interface UserRepo extends JpaRepository<User, Long> {
    
    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user.
     * @return an Optional containing the `User` entity if found, or an empty Optional if not.
     */
    Optional<User> findByEmail(String email);
}