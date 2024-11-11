package com.system.demo.data.user.dao;

import com.system.demo.appl.model.user.User;

import java.util.List;

/**
 * Data Access Object (DAO) interface for user-related database operations.
 */
public interface UserDao {

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Adds a new user.
     *
     * @param user the user to be added
     * @return true if the user was added successfully, false otherwise
     */
    boolean addUser(User user);

    /**
     * Updates the details of an existing user.
     *
     * @param user the user with updated information
     * @return true if the update was successful, false otherwise
     */
    boolean updateUser(User user);

    /**
     * Updates the password for a specified user.
     *
     * @param user the user with the updated password
     * @return the updated User object
     */
    User updatePassword(User user);

    /**
     * Retrieves the password associated with a given username.
     *
     * @param username the username of the user
     * @return the password for the specified username, or null if not found
     */
    String getPasswordByUsername(String username);

    /**
     * Resets the password for a specified user.
     *
     * @param username    the username of the user
     * @param newPassword the new password to be set
     * @return the updated User object after the password reset
     */
    String forgotPassword(String username, String newPassword);

    /**
     * Retrieves a user based on their unique user ID.
     *
     * @param userId the ID of the user
     * @return the User object with the specified user ID, or null if not found
     */
    User getUserByUserId(String userId);

    /**
     * Retrieves a user based on their username.
     *
     * @param userName the username of the user
     * @return the User object with the specified username, or null if not found
     */
    User getUserByUsername(String userName);

    /**
     * Retrieves the role of a user based on their username.
     *
     * @param username the username of the user
     * @return the role of the user with the specified username
     */
    String getUserRoleByUsername(String username);
}
