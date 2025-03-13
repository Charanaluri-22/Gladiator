package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Course;

/**
 * @author Sannappa Priya
 * Repository interface for managing `Course` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */
public interface CourseRepo extends JpaRepository<Course, Long> {

}
