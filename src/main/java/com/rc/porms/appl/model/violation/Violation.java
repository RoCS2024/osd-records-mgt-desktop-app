package com.rc.porms.appl.model.violation;


import com.rc.porms.appl.model.offense.Offense;
import java.sql.Timestamp;

/**
 * Represents a record of a student violation.
 */
public class Violation {
    private int id;
    private Offense offense;
    private int warningNum;
    private int commServHours;
    private String disciplinaryAction;
    private Timestamp dateOfNotice;
    private String studentName;
    private int offenseId;
    private int approvedById;
    private int studentId;




    /**
     * Constructs a new Violation object.
     *
     * @param id        The unique identifier of the violation record.
     * @param offense   The offense associated with the violation.
     * @param warningNum    The number of occurrence the offense was violated.
     * @param commServHours   The community service hours of the violated offense.
     * @param disciplinaryAction    The disciplinary action of the violated offense.
     * @param dateOfNotice   The timestamp indicating the date and time of the violation.
     */

    public Violation(int id, Offense offense, int commServHours, int warningNum, String disciplinaryAction, Timestamp dateOfNotice,  String studentName, int offenseId, int approvedById, int studentId) {
        this.id = id;
        this.offense = offense;
        this.commServHours = commServHours;
        this.warningNum = warningNum;
        this.disciplinaryAction = disciplinaryAction;
        this.dateOfNotice = dateOfNotice;
        this.studentName = studentName;
        this.offenseId = offenseId;
        this.approvedById = approvedById;
        this.studentId = studentId;
    }

    /**
     * Constructs a new Violation object with default values.
     */
    public Violation() {
    }

    /**
     * Retrieves the unique identifier of the Violation record.
     * @return The unique identifier of the Violation record.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the Violation record.
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }



    /**
     * Retrieves the offense object of the Offense associated with the Violation.
     * @return The offense object of the Violation.
     */
    public Offense getOffense() {
        return offense;
    }

    /**
     * Sets the Violation indicating the violation of the offense.
     * @param offense The Offense to set.
     */
    public void setOffense(Offense offense) {
        this.offense = offense;
    }


    /**
     * Sets the warningNum of the Violation.
     * @param warningNum The warningNum to set.
     */
}
