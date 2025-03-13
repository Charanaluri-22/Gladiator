package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Course;

/**
 * Interface defining the contract for CourseService.
 */
public interface CourseService {

    /**
     * Adds a new course to the system.
     *
     * @param newCourse the course to be added.
     * @return the added course.
     *  @author Priya
     */
    Course addCourse(Course newCourse);

    /**
     * Retrieves all courses from the system.
     *
     * @return a list of all courses.
     */
    List<Course> getAllCourses();

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId the ID of the course to be retrieved.
     * @return the course with the specified ID.
     */
    Course getCourseById(long courseId);

    /**
     * Updates an existing course.
     *
     * @param courseId the ID of the course to be updated.
     * @param newCourse the updated course data.
     * @return the updated course.
     */
    Course updateCourse(long courseId, Course newCourse);

    /**
     * Deletes a course by its ID.
     *
     * @param courseId the ID of the course to be deleted.
     * @return true if the course was successfully deleted, false otherwise.
     */
    boolean deleteCourse(long courseId);
}