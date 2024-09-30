package com.system.demo.data.employee.dao;

import com.system.demo.appl.model.employee.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * Retrieves an employee by ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the Employee object to the given ID, or null if not found
     */
    Employee getEmployeeById(String id);


    Employee getById(int id);

}

