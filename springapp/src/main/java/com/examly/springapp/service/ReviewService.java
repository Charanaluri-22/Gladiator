package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Review;

/**
 * Interface defining the contract for ReviewService.
 */
public interface ReviewService {

    /**
     * Creates a new review.
     *
     * @param review the review to be created.
     * @return the created review.
     * 
     * @author Priyanjali
     */
    Review createReview(Review review);

    /**
     * Retrieves all reviews from the system.
     *
     * @return a list of all reviews.
     */
    List<Review> getAllReviews();

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId the ID of the review to be retrieved.
     * @return the review with the specified ID.
     */
    Review getReviewById(long reviewId);

    /**
     * Retrieves reviews associated with a specific user ID.
     *
     * @param userId the user ID associated with the reviews to be retrieved.
     * @return a list of reviews associated with the specified user ID.
     */
    List<Review> getReviewsByUserId(long userId);

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId the ID of the review to be deleted.
     * @return true if the review was successfully deleted, false otherwise.
     */
    boolean deleteReview(long reviewId);
}
