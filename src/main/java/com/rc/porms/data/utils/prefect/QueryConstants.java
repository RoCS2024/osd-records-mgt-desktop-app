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

}
