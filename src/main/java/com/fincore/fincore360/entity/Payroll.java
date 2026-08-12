package com.fincore.fincore360.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/*
 * @Entity tells JPA/Hibernate that Payroll is a database table.
 */
@Entity
public class Payroll {

    /*
     * Primary key of Payroll table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /*
     * Each Payroll record belongs to one Employee.
     */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    /*
     * Payroll month.
     * Example: August 2026
     */
    @NotBlank(message = "Month is required")
    private String month;


    /*
     * Employee's basic salary.
     */
    @PositiveOrZero(message = "Basic salary cannot be negative")
    private double basicSalary;


    /*
     * Additional allowances.
     */
    @PositiveOrZero(message = "Allowance cannot be negative")
    private double allowances;


    /*
     * Salary deductions.
     */
    @PositiveOrZero(message = "Deduction cannot be negative")
    private double deductions;


    /*
     * Final salary after deductions.
     */
    @PositiveOrZero(message = "Net salary cannot be negative")
    private double netSalary;


    /*
     * Default constructor required by JPA.
     */
    public Payroll() {

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
     * Getter and Setter for Month.
     */
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    /*
     * Getter and Setter for Basic Salary.
     */
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }


    /*
     * Getter and Setter for Allowances.
     */
    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }


    /*
     * Getter and Setter for Deductions.
     */
    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }


    /*
     * Getter and Setter for Net Salary.
     */
    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
}
