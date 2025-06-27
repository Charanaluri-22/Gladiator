package com.examly.springapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.examly.springapp.model.Cart;
import com.examly.springapp.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * CartController class to handle cart-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/cart` base path using `@RequestMapping`.
 * 
 * @author Aluri Charan
 */

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    Logger logger = LoggerFactory.getLogger(CartController.class);
    /**
     * Adds a new cart.
     *
     * @param newCart the `Cart` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Cart` entity or an error status.
     */

    @Operation(description="Posting the Cart")
    @ApiResponse(responseCode="201",description="Status code after Successful posting of the cart")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Cart> addCart(@RequestBody Cart newCart) {
        Cart createdCart = cartService.addCart(newCart);
        return ResponseEntity.status(201).body(createdCart);
    }

    /**
     * Updates an existing cart.
     *
     * @param cartId the ID of the cart to update.
     * @param updatedCart the `Cart` entity provided in the request body.
     * @return a `ResponseEntity` containing the updated `Cart` entity or an error status.
     */
    @Operation(description="Updating the Cart by cartId")
    @ApiResponse(responseCode="200",description="Status code after Successful updation of cart")
    @PutMapping("/{cartId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> updatedCart(@PathVariable long cartId, @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCart(cartId, updatedCart);
        return ResponseEntity.status(200).body(cart);
    }

    /**
     * Removes a course from a cart.
     *
     * @param cartId the ID of the cart.
     * @param courseId the ID of the course to remove.
     * @return a `ResponseEntity` containing the updated `Cart` entity or an error status.
     */
    @Operation(description="Deleting the Cart by CourseId")
    @ApiResponse(responseCode="200",description="Status code after Successful deletion of the Course in the Cart")
    @DeleteMapping("/{cartId}/course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Cart> removeCourseFromCart(@PathVariable long cartId, @PathVariable long courseId) {
        logger.info("INSIDE REMOVE COURSE FROM CONTROLLER");
        Cart cart = cartService.removeCourseFromCart(cartId, courseId);
        return ResponseEntity.status(200).body(cart);
    }

    /**
     * Retrieves a cart by user ID.
     *
     * @param userId the ID of the user.
     * @return a `ResponseEntity` containing the `Cart` entity or an error status.
     */
    @Operation(description="Retrieving the Cart by UserId")
    @ApiResponse(responseCode="200",description="Status code after retrieving the Cart")
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.status(200).body(cart);
    }

    /**
     * Retrieves a cart by customer ID.
     *
     * @param customerId the ID of the customer.
     * @return a `ResponseEntity` containing the `Cart` entity or an error status.
     */
    @Operation(description="Retrieving the Cart by CustomerId")
    @ApiResponse(responseCode="200",description="Status code after Successful retrieving of the Cart by CustomerId")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.status(200).body(cart);
    }

    /**
     * Retrieves all carts.
     *
     * @return a `ResponseEntity` containing a list of all `Cart` entities or an error status.
     */
    @Operation(description="Retrieving List of Carts")
    @ApiResponse(responseCode="200",description="Status code after Successful retrieving the List of Carts")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @DeleteMapping("/clear/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> clearCart(@PathVariable long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.status(200).build();
    }
}
