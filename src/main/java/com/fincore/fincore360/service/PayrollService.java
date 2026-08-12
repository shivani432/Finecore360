package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.entity.Payroll;
import com.fincore.fincore360.repository.EmployeeRepository;
import com.fincore.fincore360.repository.PayrollRepository;

/*
 * @Service tells Spring that this class contains
 * the business logic of Payroll module.
 */
@Service
public class PayrollService {

    /*
     * Repository used to communicate with Payroll table.
     */
    private final PayrollRepository payrollRepository;

    /*
     * Repository used to find the actual Employee
     * selected in Payroll.
     */
    private final EmployeeRepository employeeRepository;


    /*
     * Constructor Injection:
     * Spring automatically provides both repositories.
     */
    public PayrollService(PayrollRepository payrollRepository,
                          EmployeeRepository employeeRepository) {

        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }


    /*
     * Save Payroll
     */
    public Payroll savePayroll(Payroll payroll) {

        /*
         * Employee ID वरून actual Employee database मधून घेतो.
         */
        if (payroll.getEmployee() != null) {

            int employeeId = payroll.getEmployee().getId();

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new RuntimeException("Employee not found"));

            payroll.setEmployee(employee);
        }


        /*
         * Net Salary automatically calculate करतो.
         *
         * Net Salary =
         * Basic Salary + Allowances - Deductions
         */
        double netSalary =
                payroll.getBasicSalary()
                + payroll.getAllowances()
                - payroll.getDeductions();

        payroll.setNetSalary(netSalary);


        /*
         * Payroll database मध्ये save करतो.
         */
        return payrollRepository.save(payroll);
    }


    /*
     * Get all Payroll records.
     */
    public List<Payroll> getAllPayrolls() {

        return payrollRepository.findAll();
    }


    /*
     * Get Payroll by ID.
     */
    public Payroll getPayrollById(int id) {

        return payrollRepository.findById(id)
                .orElse(null);
    }


    /*
     * Delete Payroll by ID.
     */
    public void deletePayroll(int id) {

        payrollRepository.deleteById(id);
    }


    /*
     * Search Payroll by Employee ID.
     */
    public List<Payroll> getPayrollsByEmployee(int employeeId) {

        return payrollRepository.findByEmployee_Id(employeeId);
    }


    /*
     * Search Payroll by Month.
     */
    public List<Payroll> getPayrollsByMonth(String month) {

        return payrollRepository.findByMonthIgnoreCase(month);
    }
}