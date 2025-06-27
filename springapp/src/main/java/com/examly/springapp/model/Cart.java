package com.examly.springapp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a shopping Cart.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 *  @author Charan Aluri
 */

@Entity
@Table(name = "carts")
public class Cart {

    /**
     * Unique identifier for a cart.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    /**
     * The customer associated with this cart.
     * Annotated with `@OneToOne` to denote the one-to-one relationship with the `Customer` entity.
     * Uses `@JoinColumn` to specify the foreign key column.
     */
    @OneToOne()
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer cannot be null")
    private Customer customer;

    /**
     * List of courses associated with this cart.
     * 
     * Annotated with `@ManyToMany` to denote the many-to-many relationship with the `Course` entity.
     * Uses `@JoinTable` to specify the join table and foreign key columns.
     */
    @ManyToMany()
    @JoinTable(
        name = "cart_course",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    /**
     * Total amount for the items in the cart.
     */
    @Column(nullable = false)
    @NotNull(message = "Total amount cannot be null")
    private double totalAmount;

    /**
     * Default constructor for the Cart class.
     */
    public Cart() {
        //NO SONAR
    }

    /**
     * Getter for cartId.
     *
     * @return the unique identifier of the cart.
     */
    public long getCartId() {
        return cartId;
    }

    /**
     * Setter for cartId.
     *
     * @param cartId the unique identifier of the cart.
     */
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    /**
     * Getter for customer.
     *
     * @return the customer associated with this cart.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter for customer.
     *
     * @param customer the customer to be associated with this cart.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Getter for courses.
     *
     * @return the list of courses associated with this cart.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Setter for courses.
     *
     * @param courses the list of courses to be associated with this cart.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Getter for totalAmount.
     *
     * @return the total amount for the items in the cart.
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Setter for totalAmount.
     *
     * @param totalAmount the total amount for the items in the cart.
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
