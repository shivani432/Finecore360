package com.fincore.fincore360.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.fincore360.entity.Payroll;

/*
 * Repository is used to communicate with the Payroll table in database.
 */
public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

    /*
     * Employee ID नुसार Payroll records शोधतो.
     */
    List<Payroll> findByEmployee_Id(int employeeId);

    /*
     * Payroll month नुसार records शोधतो.
     */
    List<Payroll> findByMonthIgnoreCase(String month);
}
