package com.system.demo.data.utils.employee;

public final class QueryConstants {

    /**
     * SQL query to retrieves an employee by their Employee Number from the database.
     */
    public static final String GET_EMPLOYEE_BY_ID_STATEMENT = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_NUMBER = ?";

    public static final String GET_BY_ID_STATEMENT = "SELECT * FROM EMPLOYEE WHERE ID = ?";
}
