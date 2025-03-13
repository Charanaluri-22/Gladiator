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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Review;
import com.examly.springapp.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * ReviewController class to handle review-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/review` base path using `@RequestMapping`.
 * 
 * @author Priyanjali
 */
@RestController
@RequestMapping("/api/review")
public class ReviewController {
    Logger logger =  LoggerFactory.getLogger(ReviewController.class);

    private ReviewService reviewService;
    
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    /**
     * Creates a new review.
     *
     * @param review the `Review` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Review` entity or an error status.
     */
    @Operation(description="Posting the review")
    @ApiResponse(responseCode="200",description="Status code after posting review")
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review foundReview = reviewService.createReview(review);
        return ResponseEntity.status(201).body(foundReview);
    }

    /**
     * Retrieves all reviews.
     *
     * @return a `ResponseEntity` containing a list of all `Review` entities or an error status.
     */
    @Operation(description="Retrieving List of reviews")
    @ApiResponse(responseCode="200",description="Status code after retrieving all Reviews")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Review>> getAllReviews(){
        logger.info("IN GET ALL REVIEWS");
        List<Review> reviewList = reviewService.getAllReviews();
        logger.info("REVIEWS FETCHED");
        return ResponseEntity.status(200).body(reviewList);
    }

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId the ID of the review.
     * @return a `ResponseEntity` containing the `Review` entity or an error status.
     */
    @Operation(description="Retrieving review by reviewId")
    @ApiResponse(responseCode="200",description="Status code after retrieving review by reviewId")
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long reviewId){
        Review foundReview = reviewService.getReviewById(reviewId);
        return ResponseEntity.status(200).body(foundReview);
    }

    /**
     * Retrieves reviews by user ID.
     *
     * @param userId the ID of the user.
     * @return a `ResponseEntity` containing the list of `Review` entities or an error status.
     */
    @Operation(description="Retrieving review by userId")
    @ApiResponse(responseCode="200",description="Status code after retrieving review by userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable long userId){
        List<Review> reviewList = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.status(200).body(reviewList);
    }

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId the ID of the review.
     * @return a `ResponseEntity` indicating whether the deletion was successful or not.
     */
    @Operation(description="Deleting a review by reviewId")
    @ApiResponse(responseCode="200",description="Status code after deletion of review by reviewId")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable long reviewId){
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if(!isDeleted){
            return ResponseEntity.status(500).body(false);
        }    
        return ResponseEntity.status(200).body(true);
    }
}