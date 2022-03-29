package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The appointment class
 */
public class Appointment {
    private int aptID;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * The appointment constructor
     *
     * @param aptID
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointment(int aptID, String title, String description, String location, String contactName, String type,
                       LocalDate startDate, LocalDateTime startTime, LocalDate endDate, LocalDateTime endTime, int customerID, int userID, int contactID) {
        this.aptID = aptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactName = contactName;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * The appointment constructor for appointment alerts
     *
     * @param aptID
     * @param startDate
     * @param startTime
     */
    public Appointment(int aptID, LocalDate startDate, LocalDateTime startTime) {
        this.aptID = aptID;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * The appointment ID getter
     *
     * @return
     */
    public int getAptID() {
        return aptID;
    }

    public void setAptID(int aptID) {
        this.aptID = aptID;
    }

    /**
     * The appointment title getter
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The appointment contact name getter
     *
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The appointment description getter
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The appointment location getter
     *
     * @return
     */
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * The appointment type getter
     *
     * @return
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * The appointment start date getter
     *
     * @return
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * The appointment end date getter
     *
     * @return
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * The appointment start time getter
     *
     * @return
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * The appointment end time getter
     *
     * @return
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * The appointment customer ID getter
     *
     * @return
     */
    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int ID) {
        this.customerID = ID;
    }

    /**
     * The appointment user ID getter
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * The appointment contact ID getter
     *
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}