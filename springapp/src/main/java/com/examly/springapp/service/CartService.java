package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Cart;

/**
 * Interface defining the contract for CartService.
 */
public interface CartService {
    
    /**
     * Adds a new cart to the system.
     *
     * @param newCart the cart to be added.
     * @return the added cart.
     *  @author Charan Aluri
     */
    public Cart addCart(Cart newCart);
    
    /**
     * Updates an existing cart.
     *
     * @param cartId the ID of the cart to be updated.
     * @param updatedCart the updated cart data.
     * @return the updated cart.
     */
    public Cart updateCart(long cartId, Cart updatedCart);
    
    /**
     * Removes a course from a specified cart.
     *
     * @param cartId the ID of the cart from which the course is to be removed.
     * @param courseId the ID of the course to be removed.
     * @return the cart after the course has been removed.
     */
    public Cart removeCourseFromCart(long cartId, long courseId);
    
    /**
     * Retrieves the cart associated with a specific user ID.
     *
     * @param userId the ID of the user whose cart is to be retrieved.
     * @return the cart associated with the specified user ID.
     */
    public Cart getCartByUserId(long userId);
    
    /**
     * Retrieves the cart associated with a specific customer ID.
     *
     * @param customerId the ID of the customer whose cart is to be retrieved.
     * @return the cart associated with the specified customer ID.
     */
    public Cart getCartByCustomerId(long customerId);
    
    /**
     * Retrieves all carts in the system.
     *
     * @return a list of all carts.
     */
    public List<Cart> getAllCarts();

    public void clearCart(long userId);
}