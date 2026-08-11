package com.fincore.fincore360.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    // @Id tells JPA that this field is the Primary Key.
    @Id

    // @GeneratedValue tells Hibernate to automatically generate the ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores the attendance date, for example: 2026-08-11
    private LocalDate date;

    // Stores attendance status, for example: PRESENT, ABSENT, HALF_DAY
    private String status;

    // Stores the employee's check-in time.
    private LocalTime checkInTime;

    // Stores the employee's check-out time.
    private LocalTime checkOutTime;

    // Default constructor required by JPA/Hibernate.
    public Attendance() {
    }

    // Constructor used to create an Attendance object with values.
    public Attendance(LocalDate date, String status,
                      LocalTime checkInTime, LocalTime checkOutTime) {
        this.date = date;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    // Getter and Setter for id.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for date.
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and Setter for status.
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for check-in time.
    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    // Getter and Setter for check-out time.
    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

        // Returns the Employee associated with this attendance record.
    public Employee getEmployee() {
        return employee;
    }

    // Sets the Employee for this attendance record.
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // @ManyToOne means many Attendance records can belong to one Employee.
    // Example: One Employee can have attendance for many different dates.
    @ManyToOne

    // @JoinColumn creates a foreign-key column in the attendance table.
    // This column will store the Employee's ID.
    @JoinColumn(name = "employee_id")
    private Employee employee;
    }