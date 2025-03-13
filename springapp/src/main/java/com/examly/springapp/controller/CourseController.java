package com.examly.springapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Course;
import com.examly.springapp.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * CourseController class to handle course-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/course` base path using `@RequestMapping`.
 * 
 * @author Sannappa Priya
 */

@RestController
@RequestMapping("/api/course")
public class CourseController {

    Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    /**
     * Adds a new course.
     *
     * @param newCourse the `Course` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Course` entity or an error
     *         status.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description="Posting the course")
    @ApiResponse(responseCode ="200",description = "Status code after posting")
    @PostMapping()
    public ResponseEntity<Course> addCourse(@RequestBody Course newCourse) {
        Course course = courseService.addCourse(newCourse);
        return ResponseEntity.status(201).body(course);
    }

    /**
     * Retrieves all courses.
     *
     * @return a `ResponseEntity` containing a list of all `Course` entities or an
     *         error status.
     */
    @Operation(description="Retrieving List of  courses")
    @ApiResponse(responseCode="200",description="Status code after retrieving the List of Courses")
    @GetMapping()
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> course = courseService.getAllCourses();
        return ResponseEntity.status(200).body(course);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId the ID of the course.
     * @return a `ResponseEntity` containing the `Course` entity or an error status.
     */
    @Operation(description="Retrieving course by courseId")
    @ApiResponse(responseCode="200",description="Status code after retrieving Course by CourseId")
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable long courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.status(200).body(course);
    }

    /**
     * Updates an existing course.
     *
     * @param courseId  the ID of the course to update.
     * @param newCourse the `Course` entity provided in the request body.
     * @return a `ResponseEntity` containing the updated `Course` entity or an error
     *         status.
     */
    @Operation(description="Updating course by courseId")
    @ApiResponse(responseCode="200",description="Status code after updation of Course by CourseId")
    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Course> updateCourse(@PathVariable long courseId, @RequestBody Course newCourse) {
        Course course = courseService.updateCourse(courseId, newCourse);
        return ResponseEntity.status(200).body(course);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param courseId the ID of the course.
     * @return a `ResponseEntity` indicating whether the deletion was successful or
     *         not.
     */
    @Operation(description="Deletion of course by courseId")
    @ApiResponse(responseCode="200",description="Status code after deletion of course by courseId")
    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable long courseId) {
        logger.info("INSIDE DELETE");
        boolean course = courseService.deleteCourse(courseId);
        logger.info("DELETED COURSE");
        return ResponseEntity.status(200).body(course);
    }

}