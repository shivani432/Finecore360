package com.fincore.fincore360.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/*
 * @Entity:
 * Leave class ला database table मध्ये map करते.
 */
@Entity
@Table(name = "employee_leaves")
public class Leave {

    /*
     * Leave table ची Primary Key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /*
     * प्रत्येक Leave record एका Employee शी जोडलेला आहे.
     */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    /*
     * Leave ची date.
     * Example: 2026-08-15
     */
    @NotBlank(message = "Leave date is required")
    private String leaveDate;


    /*
     * Leave type.
     * Example: Casual Leave, Sick Leave, Earned Leave
     */
    @NotBlank(message = "Leave type is required")
    private String leaveType;


    /*
     * Leave चा status.
     * Example: Pending, Approved, Rejected
     */
    @NotBlank(message = "Status is required")
    private String status;


    /*
     * Leave घेण्याचे कारण.
     */
    private String reason;


    /*
     * JPA साठी Default Constructor.
     */
    public Leave() {

    }


    /*
     * Getter and Setter for id.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /*
     * Getter and Setter for Employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    /*
     * Getter and Setter for Leave Date.
     */
    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }


    /*
     * Getter and Setter for Leave Type.
     */
    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }


    /*
     * Getter and Setter for Status.
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /*
     * Getter and Setter for Reason.
     */
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
