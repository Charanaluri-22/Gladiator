package com.examly.springapp.model;

/**
 * Data Transfer Object for Login.
 * 
 * This class is used to transfer login-related data between processes.
 */
public class LoginDTO {

    /**
     * Unique identifier for a user.
     */
    private long userId;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Role of the user.
     */
    private String role;

    /**
     * Authentication token.
     */
    private String token;

    /**
     * Default constructor for the LoginDTO class.
     */
    public LoginDTO() {
    }

    /**
     * Custom constructor for initializing a LoginDTO with specific attributes.
     *
     * @param userId    the unique identifier of the user.
     * @param email     the email address of the user.
     * @param role      the role of the user.
     * @param token     the authentication token.
     */
    public LoginDTO(long userId, String email, String role, String token) {
        this.userId = userId;
        this.email = email;
        this.role  =  role;
        this.token = token;
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
     * Getter for token.
     *
     * @return the authentication token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for token.
     *
     * @param token the authentication token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for Role.
     *
     * @return the Role of the user.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for password.
     *
     * @param password the password of the user.
     */
    public void setRole(String role) {
        this.role =  role;
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
}