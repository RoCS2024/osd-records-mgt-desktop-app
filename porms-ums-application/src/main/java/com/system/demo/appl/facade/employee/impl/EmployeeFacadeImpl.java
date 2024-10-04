/**
 * The com.system.demo.appl.facade.employee.impl package contains implementations
 * of facades for managing employee-related operations.
 */
package com.system.demo.appl.facade.employee.impl;

import com.system.demo.appl.facade.employee.EmployeeFacade;
import com.system.demo.appl.model.employee.Employee;
import com.system.demo.data.employee.dao.EmployeeDao;

/**
 * The EmployeeFacadeImpl class is an implementation of the EmployeeFacade interface.
 * It provides methods for managing employee data.
 */
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeDao employeeDao;

    /**
     * Constructs a new EmployeeFacadeImpl with the provided EmployeeDao.
     *
     * @param employeeDao The data access object for managing employee data.
     */
    public EmployeeFacadeImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }


    @Override
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }
}
