package com.system.demo.appl.facade.user.impl;

import com.system.demo.appl.facade.user.UserFacade;
import com.system.demo.appl.model.user.User;
import com.system.demo.data.user.dao.UserDao;

/**
 * Implementation of the UserFacade interface.
 * Provides user management operations using the UserDao.
 */
public class UserFacadeImpl implements UserFacade {

    private UserDao userDao;

    /**
     * Constructs a new UserFacadeImpl with the specified UserDao.
     *
     * @param userDao The data access object for user operations.
     */
    public UserFacadeImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Retrieves a user by their unique user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object with the specified user ID, or null if not found.
     */
    @Override
    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }
}
