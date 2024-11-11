/**
 * The UserFacade interface provides methods for managing users in the system.
 * It defines operations such as adding, updating, and retrieving user information.
 */
package com.system.demo.appl.facade.user;

import com.system.demo.appl.model.user.User;

import java.util.List;

public interface UserFacade {

    /**
     * Retrieves a user based on their unique user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the User object with the specified user ID, or null if not found
     */
    User getUserByUserId(String userId);
}
