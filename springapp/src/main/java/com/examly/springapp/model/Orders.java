package com.examly.springapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing an Order.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 * @author Shantanu
 */
@Entity
@Table(name = "orders")
public class Orders {

    /**
     * Unique identifier for an order.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    /**
     * Price of the order.
     */
    @NotNull(message = "Order price cannot be null")
    private double orderPrice;

    /**
     * List of courses associated with this order.
     * 
     * Annotated with `@ManyToMany` to denote the many-to-many relationship with the `Course` entity.
     * Uses `@JoinTable` to specify the join table and foreign key columns.
     */
    @ManyToMany()
    @JoinTable(
        name = "order_course",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @NotNull(message = "Courses cannot be null")
    private List<Course> courses;

    /**
     * The customer who placed this order.
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `Customer` entity.
     * Uses `@JoinColumn` to specify the foreign key column.
     */
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer cannot be null")
    private Customer customer;

    /**
     * Status of order
     */
    @NotNull(message = "Status cannot be null")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Default constructor for the Orders class.
     */
    public Orders() {}

    /**
     * Custom constructor for initializing an order with specific attributes.
     *
     * @param orderId    the unique identifier of the order.
     * @param orderPrice the price of the order.
     * @param courses    the list of courses associated with the order.
     * @param customer   the customer who placed the order.
     */
    public Orders(long orderId, double orderPrice, List<Course> courses, Customer customer, String status) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.courses = courses;
        this.customer = customer;
        this.status = status;
    }

    /**
     * Getter for orderId.
     *
     * @return the unique identifier of the order.
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Setter for orderId.
     *
     * @param orderId the unique identifier of the order.
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Getter for orderPrice.
     *
     * @return the price of the order.
     */
    public double getOrderPrice() {
        return orderPrice;
    }

    /**
     * Setter for orderPrice.
     *
     * @param orderPrice the price of the order.
     */
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * Getter for courses.
     *
     * @return the list of courses associated with the order.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Setter for courses.
     *
     * @param courses the list of courses to be associated with the order.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Getter for customer.
     *
     * @return the customer who placed the order.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter for customer.
     *
     * @param customer the customer who placed the order.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
