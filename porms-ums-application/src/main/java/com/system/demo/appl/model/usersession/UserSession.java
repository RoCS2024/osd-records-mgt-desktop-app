package com.system.demo.appl.model.usersession;

public class UserSession {
    private static String userRole;

    public static String getUserRole() {
        return userRole;
    }

    public static void setUserRole(String role) {
        userRole = role;
    }
}