package com.rc.porms.data.user.dao;

import com.rc.porms.appl.model.user.User;

public interface UserDao {

    /**
     * Retrieves a user by their username.
     *
     * @param userName the username of the user
     * @return the User associated with the given username
     */
    User getUserByUsername(String userName);

}


