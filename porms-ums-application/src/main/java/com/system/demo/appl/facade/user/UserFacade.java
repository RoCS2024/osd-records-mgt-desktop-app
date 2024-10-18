/**
 * The UserFacade interface provides methods for managing users in the system.
 * It defines operations such as adding, updating, and retrieving user information.
 */
package com.system.demo.appl.facade.user;

import com.system.demo.appl.model.user.User;

import java.util.List;

public interface UserFacade {

    User getUserByUserId(String userId);
}
