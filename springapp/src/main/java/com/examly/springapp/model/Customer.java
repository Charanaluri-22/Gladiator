package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a Customer.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 * @author Srikanth Moparthi
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * Unique identifier for a customer.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    /**
     * Name of the customer.
     */
    @NotNull(message = "Customer name cannot be null")
    private String customerName;

    /**
     * Additional information about the customer.
     */
    @NotNull(message = "Information cannot be null")
    private String information;

    /**
     * The user associated with this customer.
     * Annotated with `@OneToOne` to denote the one-to-one relationship with the `User` entity.
     * Uses `@JoinColumn` to specify the foreign key column.
     */
    @OneToOne()
    @JoinColumn(name = "user_id")
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * Default constructor for the Customer class.
     */
    public Customer() {
    }
    
    /**
     * Custom constructor for initializing a customer with specific attributes.
     *
     * @param customerId    the unique identifier of the customer.
     * @param customerName  the name of the customer.
     * @param information   additional information about the customer.
     * @param user          the user associated with this customer.
     */
    public Customer(long customerId, String customerName, String information, User user) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.information = information;
        this.user = user;
    }

    /**
     * Getter for customerId.
     *
     * @return the unique identifier of the customer.
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customerId.
     *
     * @param customerId the unique identifier of the customer.
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for customerName.
     *
     * @return the name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for customerName.
     *
     * @param customerName the name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for information.
     *
     * @return additional information about the customer.
     */
    public String getInformation() {
        return information;
    }

    /**
     * Setter for information.
     *
     * @param information additional information about the customer.
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Getter for user.
     *
     * @return the user associated with this customer.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user.
     *
     * @param user the user to be associated with this customer.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
