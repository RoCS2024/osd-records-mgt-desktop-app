package com.system.demo.appl.facade.user.impl;


import com.system.demo.appl.facade.user.UserFacade;
import com.system.demo.appl.model.user.User;
import com.system.demo.data.user.dao.UserDao;

import java.util.List;

/**
 * An implementation class of the User Facade.
 */

public class UserFacadeImpl implements UserFacade {

        /**
         * The logger for this class.
         */

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
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    public User updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    @Override
    public boolean forgotPassword(String username, String securityQuestionAnswer, String newPassword) {
        String getPassword = userDao.getPasswordByUsername(username);
        if(getPassword != null){
            if(securityQuestionAnswer.equalsIgnoreCase("markian")){
                userDao.forgotPassword(username,newPassword);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }


    @Override
    public boolean updateUser(User user) {
        return processUser(user, false);
    }

    private boolean processUser(User user, boolean isAddOperation) {
        try {
            User targetUser = getUserByUserId(user.getUserId());

            // Check for existence based on operation type
            if (isAddOperation && targetUser != null) {
                throw new Exception("User to add already exists.");
            } else if (!isAddOperation && targetUser == null) {
                throw new Exception("Student to update not found.");
            }

            // Perform the appropriate DAO operation
            return isAddOperation ? userDao.addUser(user) : userDao.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


}
