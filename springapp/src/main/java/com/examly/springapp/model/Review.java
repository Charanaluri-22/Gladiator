package com.examly.springapp.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a Review.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 * @author Priyanjali
 */
@Entity
@Table(name = "reviews")
public class Review {

    /**
     * Unique identifier for a review.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewId;

    /**
     * Subject of the review.
     */
    @NotNull(message = "Subject cannot be null")
    private String subject;

    /**
     * Body of the review.
     */
    @NotNull(message = "Body cannot be null")
    private String body;

    /**
     * Rating given in the review.
     */
    @NotNull(message = "Rating cannot be null")
    private int rating;

    /**
     * Date the review was created.
     * Uses `@Temporal` with `TemporalType.TIMESTAMP` to store date and time.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Date created cannot be null")
    private Date dateCreated;

    /**
     * The customer who wrote the review.
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `Customer` entity.
     * Uses `@JoinColumn` to specify the foreign key column.
     */
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer cannot be null")
    private Customer customer;

    /**
     * Default constructor for the Review class.
     */
    public Review() {
    }

    /**
     * Custom constructor for initializing a review with specific attributes.
     *
     * @param reviewId    the unique identifier of the review.
     * @param subject     the subject of the review.
     * @param body        the body of the review.
     * @param rating      the rating given in the review.
     * @param dateCreated the date the review was created.
     * @param customer    the customer who wrote the review.
     */
    public Review(long reviewId, String subject, String body, int rating, Date dateCreated, Customer customer) {
        this.reviewId = reviewId;
        this.subject = subject;
        this.body = body;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.customer = customer;
    }

    /**
     * Getter for reviewId.
     *
     * @return the unique identifier of the review.
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * Setter for reviewId.
     *
     * @param reviewId the unique identifier of the review.
     */
    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Getter for subject.
     *
     * @return the subject of the review.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter for subject.
     *
     * @param subject the subject of the review.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Getter for body.
     *
     * @return the body of the review.
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter for body.
     *
     * @param body the body of the review.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Getter for rating.
     *
     * @return the rating given in the review.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Setter for rating.
     *
     * @param rating the rating given in the review.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Getter for dateCreated.
     *
     * @return the date the review was created.
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Setter for dateCreated.
     *
     * @param dateCreated the date the review was created.
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Getter for customer.
     *
     * @return the customer who wrote the review.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter for customer.
     *
     * @param customer the customer who wrote the review.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
