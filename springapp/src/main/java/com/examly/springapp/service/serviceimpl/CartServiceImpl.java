package com.examly.springapp.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.exception.EmptyCartException;
import com.examly.springapp.model.Cart;
import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CartRepo;
import com.examly.springapp.service.CartService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing cart-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `CartService` interface to provide specific business logic.
 * 
 * @author Aluri Charan
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo; 
    private static final String CART_NOT_FOUND = "CART NOT FOUND WITH ID: "; 
    /**
     * Constructor to inject the `CartRepo` bean.
     * 
     * @param cartRepo the repository for managing `Cart` entity data access.
     */
    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    /**
     * Adds a new cart to the system.
     * 
     * @param newCart the new cart entity to be added.
     * @return the saved cart entity.
     * @throws EntityExistsException if a cart with the same ID already exists.
     */
    @Override
    public Cart addCart(Cart newCart) {
        long cartId = newCart.getCartId();
        Optional<Cart> foundCart = cartRepo.findById(cartId);
        if (foundCart.isPresent()) {
            throw new EntityExistsException("Cart With ID: " + cartId + " Already Exists.");
        }
        return cartRepo.save(newCart);
    }

    /**
     * Updates an existing cart.
     * 
     * @param cartId      the ID of the cart to be updated.
     * @param updatedCart the updated cart entity.
     * @return the updated cart entity.
     * @throws EntityNotFoundException if the cart with the given ID is not found.
     */
    @Override
    public Cart updateCart(long cartId, Cart updatedCart) {
        Optional<Cart> existingCart = cartRepo.findById(cartId);
        if (existingCart.isPresent()) {
            updatedCart.setCartId(cartId);
            return cartRepo.save(updatedCart);
        }
        throw new EntityNotFoundException(CART_NOT_FOUND + cartId);
    }

    /**
     * Removes a course from the cart.
     * 
     * @param cartId   the ID of the cart.
     * @param courseId the ID of the course to be removed.
     * @return the updated cart entity.
     * @throws EntityNotFoundException if the cart or the course with the given ID is not found.
     */
    @Override
    public Cart removeCourseFromCart(long cartId, long courseId) {
        Optional<Cart> existingCart = cartRepo.findById(cartId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            List<Course> courses = cart.getCourses();
            int initialLength = courses.size();
            courses.removeIf(course -> course.getCourseId() == courseId);
            int finalLength = courses.size();
            if (initialLength == finalLength) {
                throw new EntityNotFoundException("Course With ID: Not found." + courseId );
            }
            cart.setCourses(courses);
            return cartRepo.save(cart);
        }
        throw new EntityNotFoundException(CART_NOT_FOUND + cartId);
    }

    /**
     * Retrieves a cart by the user ID.
     * 
     * @param userId the ID of the user.
     * @return the found cart entity.
     * @throws EntityNotFoundException if the cart for the given user ID is not found.
     */
    @Override
    public Cart getCartByUserId(long userId) {
        Optional<Cart> foundCart = cartRepo.findByUserId(userId);
        if (foundCart.isPresent()) {
            return foundCart.get();
        }
        throw new EntityNotFoundException("Cart Of User Not Found with ID: " + userId);
    }

    /**
     * Retrieves a cart by the customer ID.
     * 
     * @param customerId the ID of the customer.
     * @return the found cart entity.
     * @throws EntityNotFoundException if the cart for the given customer ID is not found.
     */
    @Override
    public Cart getCartByCustomerId(long customerId) {
        Optional<Cart> foundCart = cartRepo.findByCustomerId(customerId);
        if (foundCart.isPresent()) {
            return foundCart.get();
        }
        throw new EntityNotFoundException("Cart Of The Customer Not Found: " + customerId);
    }

    /**
     * Retrieves a list of all carts.
     * 
     * @return a list of all cart entities.
     * @throws EmptyCartException if no carts are available.
     */
    @Override
    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();
        if (carts.isEmpty()) {
            throw new EmptyCartException("No Carts Available.");
        }
        return carts;
    }

    @Override
    public void clearCart(long userId) {
        Optional<Cart> foundCart = cartRepo.findByUserId(userId);
        if (foundCart.isPresent()) {
            Cart cart = foundCart.get();
            cart.getCourses().clear();
            cartRepo.save(cart);
        } else {
            throw new EntityNotFoundException("Cart Of User Not Found with ID: " + userId);
        }
    }
}
