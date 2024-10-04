package com.system.demo.data.student.dao;


import com.system.demo.appl.model.student.Student;


import java.util.List;

/**
 * Interface for Student Data Access Object.
 * */
public interface StudentDao {
    /**
     * Retrieves all Students from the database.
     *
     * @return list of all students.
     * */
    List<Student> getAllStudents();

    /**
     * Retrieves a Student from the database with specified id.
     *
     * @param studentId the id of the students.
     * @return the Student.
     * */
    Student getStudentByNumber(String studentId);

    Student getStudentById(int id);

}
