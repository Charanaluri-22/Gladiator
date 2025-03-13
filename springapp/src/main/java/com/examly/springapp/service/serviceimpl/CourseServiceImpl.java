package com.examly.springapp.service.serviceimpl;

import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CourseRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.service.CourseService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing course-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `CourseService` interface to provide specific business logic.
 * 
 * @author [sannappa priya]
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired 
    private CourseRepo courseRepo;
    private static final String COURSE_NOT_FOUND = "COURSE NOT FOUND WITH ID: ";

    /**
     * Adds a new course to the system.
     * 
     * @param newCourse the new course entity to be added.
     * @return the saved course entity.
     * @throws EntityExistsException if a course with the same ID already exists.
     */
    @Override
    public Course addCourse(Course newCourse) {
        long courseId = newCourse.getCourseId();
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            throw new EntityExistsException("Course with ID: " + courseId + " Already Exists.");
        }
        return courseRepo.save(newCourse);
    }

    /**
     * Retrieves a list of all courses.
     * 
     * @return a list of all course entities.
     */
    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    /**
     * Retrieves a course by its ID.
     * 
     * @param courseId the ID of the course.
     * @return the found course entity.
     * @throws EntityNotFoundException if the course with the given ID is not found.
     */
    @Override
    public Course getCourseById(long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        }
        throw new EntityNotFoundException(COURSE_NOT_FOUND+ courseId );
    }

    /**
     * Updates an existing course.
     * 
     * @param courseId  the ID of the course to be updated.
     * @param newCourse the updated course entity.
     * @return the updated course entity.
     * @throws EntityNotFoundException if the course with the given ID is not found.
     */
    @Override
    public Course updateCourse(long courseId, Course newCourse) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (!course.isPresent()) {
            throw new EntityNotFoundException(COURSE_NOT_FOUND+courseId );
        }
        newCourse.setCourseId(courseId);
        return courseRepo.save(newCourse);
    }

    /**
     * Deletes a course by its ID.
     * 
     * @param courseId the ID of the course to be deleted.
     * @return true if the course was successfully deleted.
     * @throws EntityNotFoundException if the course with the given ID is not found.
     */
    @Override
    public boolean deleteCourse(long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (!course.isPresent()) {
            throw new EntityNotFoundException(COURSE_NOT_FOUND + courseId );
        }
        courseRepo.deleteById(courseId);
        return true;
    }
}
