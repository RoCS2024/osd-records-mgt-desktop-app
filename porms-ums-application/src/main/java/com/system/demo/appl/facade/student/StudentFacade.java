/**
 * The com.system.demo.appl.facade.student package contains interfaces and classes
 * related to the student facade.
 */
package com.system.demo.appl.facade.student;

import com.system.demo.appl.model.student.Student;

import java.util.List;

/**
 * The StudentFacade interface defines methods for managing student data.
 */
public interface StudentFacade {

    /**
     * Retrieves all students from the database.
     *
     * @return A list of all students.
     */
    List<Student> getAllStudents();

    /**
     * Retrieves a student from the database with the specified student number.
     *
     * @param studentNumber The student number of the student to retrieve.
     * @return The student with the specified student number, or null if not found.
     */
    Student getStudentByNumber(String studentNumber);

    Student getStudentById(int id);

}
