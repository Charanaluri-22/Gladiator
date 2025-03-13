package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a Course.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 *  @author Priya
 */
@Entity
@Table(name = "courses")
public class Course {

    /**
     * Unique identifier for a course.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;

    /**
     * Type of the course.
     */
    @NotNull(message = "Course type cannot be null")
    private String courseType;

    /**
     * URL for the course image.
     */
    @NotNull(message = "Course image URL cannot be null")
    private String courseImageUrl;

    /**
     * Details about the course.
     */
    @NotNull(message = "Course details cannot be null")
    private String courseDetails;

    /**
     * Price of the course.
     */
    @NotNull(message = "Course price cannot be null")
    private double coursePrice;

    /**
     * Default constructor for the Course class.
     */
    public Course() {
    }

    /**
     * Custom constructor for initializing a course with specific attributes.
     *
     * @param courseId       the unique identifier of the course.
     * @param courseType     the type of the course.
     * @param courseImageUrl the URL for the course image.
     * @param courseDetails  the details about the course.
     * @param coursePrice    the price of the course.
     */
    public Course(long courseId, String courseType, String courseImageUrl, String courseDetails, double coursePrice) {
        this.courseId = courseId;
        this.courseType = courseType;
        this.courseImageUrl = courseImageUrl;
        this.courseDetails = courseDetails;
        this.coursePrice = coursePrice;
    }

    /**
     * Getter for courseId.
     *
     * @return the unique identifier of the course.
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * Setter for courseId.
     *
     * @param courseId the unique identifier of the course.
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter for courseType.
     *
     * @return the type of the course.
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * Setter for courseType.
     *
     * @param courseType the type of the course.
     */
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     * Getter for courseImageUrl.
     *
     * @return the URL for the course image.
     */
    public String getCourseImageUrl() {
        return courseImageUrl;
    }

    /**
     * Setter for courseImageUrl.
     *
     * @param courseImageUrl the URL for the course image.
     */
    public void setCourseImageUrl(String courseImageUrl) {
        this.courseImageUrl = courseImageUrl;
    }

    /**
     * Getter for courseDetails.
     *
     * @return the details about the course.
     */
    public String getCourseDetails() {
        return courseDetails;
    }

    /**
     * Setter for courseDetails.
     *
     * @param courseDetails the details about the course.
     */
    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    /**
     * Getter for coursePrice.
     *
     * @return the price of the course.
     */
    public double getCoursePrice() {
        return coursePrice;
    }

    /**
     * Setter for coursePrice.
     *
     * @param coursePrice the price of the course.
     */
    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }
}
