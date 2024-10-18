package com.system.demo.appl.facade.user.impl;


import com.system.demo.appl.facade.user.UserFacade;
import com.system.demo.appl.model.user.User;
import com.system.demo.data.user.dao.UserDao;


/**
 * An implementation class of the User Facade.
 */

public class UserFacadeImpl implements UserFacade {

    private UserDao userDao;

    /**
     * Constructs a new UserFacadeImpl with the provided UserDao.
     *
     * @param userDao The data access object for user operations.
     */
    public UserFacadeImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }
}
