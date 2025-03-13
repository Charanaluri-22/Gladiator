package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a User.
 * 
 * Annotated with `@Entity` to map the class to a database table.
 * Annotated with `@Table` to specify the table name in the database.
 * 
 * @author SreyaReddy
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for a user.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    /**
     * Email address of the user.
     */
    @NotNull(message = "Email cannot be null")
    private String email;

    /**
     * Password of the user.
     */
    @NotNull(message = "Password cannot be null")
    private String password;

    /**
     * Username of the user.
     */
    @NotNull(message = "Username cannot be null")
    private String username;

    /**
     * Mobile number of the user.
     */
    @NotNull(message = "Mobile number cannot be null")
    private String mobileNumber;

    /**
     * Role of the user.
     */
    @NotNull(message = "Role cannot be null")
    private String role;

    /**
     * @Transient for storing temporarily
     */
    @Transient
    private String customerName;

    @Transient
    private String information;

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Custom constructor for initializing a user with specific attributes.
     *
     * @param userId       the unique identifier of the user.
     * @param email        the email address of the user.
     * @param password     the password of the user.
     * @param username     the username of the user.
     * @param mobileNumber the mobile number of the user.
     * @param role         the role of the user.
     */
    public User(long userId, String email, String password, String username, String mobileNumber, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    /**
     * Getter for userId.
     *
     * @return the unique identifier of the user.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter for userId.
     *
     * @param userId the unique identifier of the user.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Getter for email.
     *
     * @return the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email the email address of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for username.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     *
     * @param username the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for mobileNumber.
     *
     * @return the mobile number of the user.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Setter for mobileNumber.
     *
     * @param mobileNumber the mobile number of the user.
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Getter for role.
     *
     * @return the role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for role.
     *
     * @param role the role of the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
