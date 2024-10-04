package com.system.demo.data.employee.dao.impl;

import com.system.demo.appl.model.employee.Employee;
import com.system.demo.data.connection.ConnectionHelper;
import com.system.demo.data.employee.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import static com.system.demo.data.utils.employee.QueryConstants.*;

/**
 * The EmployeeDaoImpl class implements the EmployeeDao interface
 * it provides methods for interacting with the employee database.
 * It includes methods for retrieving, adding, and updating employee records.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    /**
     * Constructor for EmployeeDaoImpl.
     * */
    public EmployeeDaoImpl() {
    }


    @Override
    public Employee getEmployeeById(String id) {
        try (Connection connection = ConnectionHelper.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(GET_EMPLOYEE_BY_ID_STATEMENT);
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    LOGGER.debug("Employee retrieved successfully.");
                    return setEmployee(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An SQL Exception occurred." + e.getMessage());
        }
        LOGGER.debug("Employee not found.");
        return null;
    }


    @Override
    public Employee getById(int id) {
        try (Connection connection = ConnectionHelper.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(GET_BY_ID_STATEMENT);
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    LOGGER.debug("Employee retrieved successfully.");
                    return setEmployee(rs);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An SQL Exception occurred." + e.getMessage());
        }
        LOGGER.debug("Employee not found.");
        return null;
    }


    private Employee setEmployee(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setLastName(rs.getString("last_name"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setMiddleName(rs.getString("middle_name"));
            employee.setPositionInRC(rs.getString("position_in_rc"));
            employee.setDateEmployed(rs.getTimestamp("date_employed"));
            employee.setBirthdate(rs.getTimestamp("birthdate"));
            employee.setBirthplace(rs.getString("birthplace"));
            employee.setSex(rs.getString("sex"));
            employee.setAddress(rs.getString("address"));
            employee.setContactNo(rs.getString("contact_number"));
            employee.setCivilStatus(rs.getString("civil_status"));
            employee.setCitizenship(rs.getString("citizenship"));
            employee.setReligion(rs.getString("religion"));
            employee.setEmail(rs.getString("email"));
            employee.setSssNo(rs.getString("sss_no"));
            employee.setTinNo(rs.getString("tin_no"));
            employee.setPagibigNo(rs.getString("pagibig_no"));
            employee.setEmployeeNo(rs.getString("employee_number"));
            employee.setStationId(rs.getInt("station_id"));
            employee.setId(rs.getInt("id"));


            return employee;
        } catch (Exception e) {
            LOGGER.error("An SQL Exception occurred." + e.getMessage());
        }
        LOGGER.debug("No employee was set.");
        return null;
    }
}
