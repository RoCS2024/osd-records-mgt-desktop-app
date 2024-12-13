package com.rc.porms.appl.facade.employee.impl;

import com.rc.porms.appl.model.employee.Employee;
import com.rc.porms.data.employee.dao.EmployeeDao;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class EmployeeFacadeImplTest {

    private EmployeeDao employeeDao = mock(EmployeeDao.class);
    private EmployeeFacadeImpl employeeFacade = new EmployeeFacadeImpl(employeeDao);

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
<<<<<<< HEAD
        employee1.setEmployeeNo("EMP16-0005");
        employee1.setLastName("Roque");
        employee1.setFirstName("Joshua");

        Employee employee2 = new Employee();
        employee2.setEmployeeNo("EMP16-0143");
        employee2.setLastName("Claus");
        employee2.setFirstName("Saint");
=======
        employee1.setEmployeeNo("EMP21-0143");
        employee1.setLastName("Magnaye");
        employee1.setFirstName("Justine");

        Employee employee2 = new Employee();
        employee2.setEmployeeNo("EMP21-0144");
        employee2.setLastName("Doe");
        employee2.setFirstName("John");
>>>>>>> a2576df5d6196d9d7e42ffb197218cf85ee53497

        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeDao.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeFacade.getAllEmployees();

        assertNotNull(result, "Employee list should not be null.");
        assertEquals(2, result.size(), "There should be two employees in the list.");
        verify(employeeDao, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
<<<<<<< HEAD
        String employeeId = "EMP16-0005";
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeNo(employeeId);
        expectedEmployee.setLastName("Roque");
        expectedEmployee.setFirstName("Joshua");
=======
        String employeeId = "EMP21-0143";
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeNo(employeeId);
        expectedEmployee.setLastName("Magnaye");
        expectedEmployee.setFirstName("Justine");
>>>>>>> a2576df5d6196d9d7e42ffb197218cf85ee53497
        when(employeeDao.getEmployeeById(employeeId)).thenReturn(expectedEmployee);

        Employee result = employeeFacade.getEmployeeById(employeeId);

        assertNotNull(result, "Employee should not be null.");
        assertEquals(expectedEmployee, result, "Returned employee should match the expected employee.");
        verify(employeeDao, times(1)).getEmployeeById(employeeId);
    }

    @Test
    void testGetById() {
        int employeeId = 1;
        Employee expectedEmployee = new Employee();
<<<<<<< HEAD
        expectedEmployee.setEmployeeNo("EMP16-0005");
        expectedEmployee.setLastName("Roque");
        expectedEmployee.setFirstName("Joshua");
=======
        expectedEmployee.setEmployeeNo("EMP21-0143");
        expectedEmployee.setLastName("Magnaye");
        expectedEmployee.setFirstName("Justine");
>>>>>>> a2576df5d6196d9d7e42ffb197218cf85ee53497
        when(employeeDao.getById(employeeId)).thenReturn(expectedEmployee);

        Employee result = employeeFacade.getById(employeeId);

        assertNotNull(result, "Employee should not be null.");
        assertEquals(expectedEmployee, result, "Returned employee should match the expected employee.");
        verify(employeeDao, times(1)).getById(employeeId);
    }

    @Test
    void testAddEmployee() {
        Employee newEmployee = new Employee();
<<<<<<< HEAD
        newEmployee.setEmployeeNo("EMP16-0144");
        newEmployee.setLastName("Claus");
        newEmployee.setFirstName("Rudolf");
=======
        newEmployee.setEmployeeNo("EMP21-0145");
        newEmployee.setLastName("Smith");
        newEmployee.setFirstName("Emily");
>>>>>>> a2576df5d6196d9d7e42ffb197218cf85ee53497

        when(employeeDao.addEmployee(newEmployee)).thenReturn(true);
        when(employeeDao.getEmployeeById(newEmployee.getEmployeeNo())).thenReturn(null);

        boolean result = employeeFacade.addEmployee(newEmployee);

        assertTrue(result, "Employee should be added successfully.");
        verify(employeeDao, times(1)).addEmployee(newEmployee);
    }

    @Test
    void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
<<<<<<< HEAD
        existingEmployee.setEmployeeNo("EMP16-0005");
        existingEmployee.setLastName("Roque");
        existingEmployee.setFirstName("Joshua");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeNo("EMP16-0005");
        updatedEmployee.setLastName("Roque");
        updatedEmployee.setFirstName("Joshua Gerome");
=======
        existingEmployee.setEmployeeNo("EMP21-0143");
        existingEmployee.setLastName("Magnaye");
        existingEmployee.setFirstName("Justine");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeNo("EMP21-0143");
        updatedEmployee.setLastName("Magnaye");
        updatedEmployee.setFirstName("Justine Updated");
>>>>>>> a2576df5d6196d9d7e42ffb197218cf85ee53497

        when(employeeDao.getEmployeeById(existingEmployee.getEmployeeNo())).thenReturn(existingEmployee);
        when(employeeDao.updateEmployee(updatedEmployee)).thenReturn(true);

        boolean result = employeeFacade.updateEmployee(updatedEmployee);

        assertTrue(result, "Employee should be updated successfully.");
        verify(employeeDao, times(1)).updateEmployee(updatedEmployee);
    }

}
