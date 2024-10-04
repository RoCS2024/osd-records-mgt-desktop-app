/**
 * The com.system.demo.appl.facade.employee package contains interfaces and classes
 * related to the employee facade.
 */
package com.system.demo.appl.facade.employee;

import com.system.demo.appl.model.employee.Employee;

/**
 * The EmployeeFacade interface defines methods for managing employee data.
 */
public interface EmployeeFacade {

    /**
     * Retrieves an employee from the database using their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID, or null if not found.
     */
    Employee getEmployeeById(String id);

    /**
     * Retrieves an employee from the database using their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID, or null if not found.
     */
    Employee getById(int id);

}
