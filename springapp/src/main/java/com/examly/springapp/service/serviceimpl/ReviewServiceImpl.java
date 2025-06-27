package com.examly.springapp.service.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.exception.NoContentException;
import com.examly.springapp.model.Review;
import com.examly.springapp.repository.ReviewRepo;
import com.examly.springapp.service.ReviewService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing review-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `ReviewService` interface to provide specific business logic.
 * 
 * @author Priyanjali
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private static final String REVIEW_NOT_FOUND = "REVIEW NOT FOUND WITH ID: ";

    /**
     * Constructor to inject the `ReviewRepo` bean.
     * 
     * @param reviewRepo the repository for managing `Review` entity data access.
     */
    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    /**
     * Creates a new review.
     * 
     * @param review the new review entity to be created.
     * @return the saved review entity.
     * @throws EntityExistsException if a review with the same ID already exists.
     */
    @Override
    public Review createReview(Review review) {
        long id = review.getReviewId();
        Optional<Review> foundReview = reviewRepo.findById(id);
        if (foundReview.isPresent()) {
            throw new EntityExistsException(REVIEW_NOT_FOUND+ id );
        }
        return reviewRepo.save(review);
    }

    /**
     * Deletes a review by its ID.
     * 
     * @param reviewId the ID of the review to be deleted.
     * @return true if the review was successfully deleted.
     * @throws EntityNotFoundException if the review with the given ID is not found.
     */
    @Override
    public boolean deleteReview(long reviewId) {
        Optional<Review> foundReview = reviewRepo.findById(reviewId);
        if (foundReview.isPresent()) {
            reviewRepo.deleteById(reviewId);
            return true;
        }
        throw new EntityNotFoundException(REVIEW_NOT_FOUND + reviewId);
    }

    /**
     * Retrieves a list of all reviews.
     * 
     * @return a list of all review entities.
     */
    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepo.findAll();
        if(reviews.isEmpty()){
            throw new NoContentException("No Reviews Found");
        }
        return reviews;
    }

    /**
     * Retrieves a review by its ID.
     * 
     * @param reviewId the ID of the review.
     * @return the found review entity.
     * @throws EntityNotFoundException if the review with the given ID is not found.
     */
    @Override
    public Review getReviewById(long reviewId) {
        Optional<Review> review = reviewRepo.findById(reviewId);
        if (review.isPresent()) {
            return review.get();
        }
        throw new EntityNotFoundException(REVIEW_NOT_FOUND + reviewId);
    }

    /**
     * Retrieves a list of reviews by the user ID.
     * 
     * @param userId the ID of the user.
     * @return a list of reviews associated with the user.
     */
    @Override
    public List<Review> getReviewsByUserId(long userId) {
        List<Review> reviews = reviewRepo.findByUserId(userId);
        if (reviews.isEmpty()) {
            return Collections.emptyList();
        }
        return reviews;
    }
}
