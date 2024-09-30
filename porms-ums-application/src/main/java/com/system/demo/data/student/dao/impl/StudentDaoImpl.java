package com.system.demo.data.student.dao.impl;


import com.system.demo.appl.model.student.Student;
import com.system.demo.data.connection.ConnectionHelper;
import com.system.demo.data.student.dao.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.system.demo.data.utils.student.QueryConstants.*;


/**
 * An implementation class of the Student Data Access Object.
 * */
public class StudentDaoImpl implements StudentDao {

    public static Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection con = ConnectionHelper.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(GET_ALL_STUDENTS_STATEMENT);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(setStudent(rs));
            }
            return students;

        } catch (Exception e) {
            LOGGER.error("An SQL Exception occurred." + e.getMessage());
        }
        LOGGER.debug("Student database is empty.");
        return students;
    }

    @Override
    public Student getStudentByNumber(String studentId) {

        try (Connection con = ConnectionHelper.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(GET_STUDENT_BY_STUDENT_ID_STATEMENT);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return setStudent(rs);
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving Student with Student Number " + studentId + ": " + e.getMessage());
            e.printStackTrace();
        }
        LOGGER.debug("Student not found.");
        return null;
    }

    @Override
    public Student getStudentById(int id) {

        try (Connection con = ConnectionHelper.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(GET_STUDENT_BY_STUDENT_STATEMENT);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return setStudent(rs);
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving Student with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
        LOGGER.debug("Student not found.");
        return null;
    }

    private Student setStudent(ResultSet rs) {
        try {
            Student student = new Student();
            student.setStudentId(rs.getString("student_number"));
            student.setLastName(rs.getString("last_name"));
            student.setFirstName(rs.getString("first_name"));
            student.setMiddleName(rs.getString("middle_name"));
            student.setSex(rs.getString("sex"));
            student.setBirthday(rs.getTimestamp("birthdate"));
            student.setBirthplace(rs.getString("birthplace"));
            student.setReligion(rs.getString("religion"));
            student.setEmail(rs.getString("email"));
            student.setAddress(rs.getString("address"));
            student.setContactNumber(rs.getString("contact_number"));
            student.setCitizenship(rs.getString("citizenship"));
            student.setCivilStatus(rs.getString("civil_status"));
            student.setSectionId(rs.getInt("section_section_id"));
            student.setId(rs.getInt("id"));

            return student;
        } catch (Exception e) {
            LOGGER.error("An SQL Exception occurred." + e.getMessage());
        }
        LOGGER.debug("set Student failed.");
        return setStudent(rs);
    }

}
