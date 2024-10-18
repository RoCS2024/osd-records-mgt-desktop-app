package com.system.demo;

import com.system.demo.appl.facade.user.UserFacade;
import com.system.demo.appl.facade.user.impl.UserFacadeImpl;
import com.system.demo.data.user.dao.UserDao;
import com.system.demo.data.user.dao.impl.UserDaoImpl;

/**
 * This class serves as the entry point for managing user information within the system.
 * It initializes the {@link UserFacade} to handle user-related operations such as creating,
 * updating, and retrieving user data.
 */
public class UserInfoMgtApplication {

    private UserFacade userFacade;

    /**
     * Constructor for {@code UserInfoMgtApplication}.
     * This initializes the {@link UserDao} implementation and sets up the {@link UserFacade}
     * for managing user data through the {@link UserFacadeImpl}.
     */
    public UserInfoMgtApplication() {
        UserDao userDaoImpl = new UserDaoImpl();
        this.userFacade = new UserFacadeImpl(userDaoImpl);
    }
}
