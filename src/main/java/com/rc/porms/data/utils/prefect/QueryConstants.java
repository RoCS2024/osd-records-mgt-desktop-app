package com.rc.porms.data.utils.prefect;

public class QueryConstants {
    public static final String ADD_OFFENSE_STATEMENT = "INSERT INTO offense (type, description) VALUES (?, ?)";

    /**
     * SQL query to retrieve all offenses from the database.
     */
    public static final String GET_ALL_OFFENSE_STATEMENT = "SELECT * FROM offense";

    /**
     * SQL query to retrieve all offenses by type from the database.
     */
    public static final String GET_ALL_OFFENSE_BY_TYPE_STATEMENT = "SELECT * FROM offense WHERE type = ?";

    /**
     * SQL query to retrieve an offense by its offense id from the database.
     */
    public static final String GET_OFFENSE_BY_ID_STATEMENT = "SELECT * FROM offense WHERE id = ?";

    /**
     * SQL query to retrieve an offense by its description from the database.
     */
    public static final String GET_OFFENSE_BY_NAME_STATEMENT = "SELECT * FROM offense WHERE description LIKE ?";

    /**
     * SQL query to update an existing offense in the database.
     */
    public static final String UPDATE_OFFENSE_STATEMENT = "UPDATE offense SET type = ?, description = ? WHERE id = ?";

    /**
     * SQL query to retrieve all violations from the database.
     */
    public static final String GET_ALL_VIOLATION_STATEMENT = "SELECT * FROM violation";

    /**
     * SQL query to retrieve all violations by student ID from the database.
     */
    public static final String GET_ALL_VIOLATION_BY_STUDENT_ID_STATEMENT = "SELECT * FROM violation WHERE student_id = ?";

    /**
     * SQL query to retrieve a violation by its ID from the database.
     */
    public static final String GET_VIOLATION_BY_ID_STATEMENT = "SELECT * FROM violation WHERE id = ?";

    /**
     * SQL query to update an existing violation in the database.
     */
    public static final String UPDATE_VIOLATION_STATEMENT = "UPDATE violation SET student_id = ?, offense_id = ?, warning_number = ?, cs_hours = ?, disciplinary_action = ?, date_of_notice = ?, approved_by_id = ? WHERE id = ?";

    /**
     * SQL query that adds a new violation to the database.
     */
    public static final String ADD_VIOLATION_STATEMENT = "INSERT INTO violation (student_id, offense_id, warning_number, cs_hours, disciplinary_action, date_of_notice, approved_by_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_VIOLATION_BY_STUDENT_NAME_STATEMENT = "SELECT violation.*, s.first_name AS student_first_name, s.last_name AS student_last_name " +
            "FROM VIOLATION violation " +
            "JOIN STUDENT s ON violation.STUDENT_ID = s.ID " +
            "WHERE s.LAST_NAME = ? AND s.FIRST_NAME = ?";

}
