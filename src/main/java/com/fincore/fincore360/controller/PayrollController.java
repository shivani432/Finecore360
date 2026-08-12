package com.fincore.fincore360.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.fincore360.entity.Payroll;
import com.fincore.fincore360.service.PayrollService;

/*
 * @RestController:
 * Payroll REST API requests handle करण्यासाठी.
 */
@RestController

/*
 * Payroll चे सर्व APIs /payroll पासून सुरू होतील.
 */
@RequestMapping("/payroll")

/*
 * Postman / Frontend requests allow करण्यासाठी.
 */
@CrossOrigin(origins = "*")
public class PayrollController {

    /*
     * Payroll business logic Service कडे देतो.
     */
    private final PayrollService payrollService;


    /*
     * Constructor Injection:
     * Spring automatically PayrollService object देतो.
     */
    public PayrollController(PayrollService payrollService) {

        this.payrollService = payrollService;
    }


    /*
     * POST /payroll
     *
     * नवीन Payroll record save करण्यासाठी.
     */
    @PostMapping
    public Payroll savePayroll(@RequestBody Payroll payroll) {

        return payrollService.savePayroll(payroll);
    }


    /*
     * GET /payroll
     *
     * सर्व Payroll records मिळवण्यासाठी.
     */
    @GetMapping
    public List<Payroll> getAllPayrolls() {

        return payrollService.getAllPayrolls();
    }


    /*
     * GET /payroll/{id}
     *
     * ID वापरून एक Payroll record मिळवण्यासाठी.
     */
    @GetMapping("/{id}")
    public Payroll getPayrollById(@PathVariable int id) {

        return payrollService.getPayrollById(id);
    }


    /*
     * DELETE /payroll/{id}
     *
     * Payroll record delete करण्यासाठी.
     */
    @DeleteMapping("/{id}")
    public String deletePayroll(@PathVariable int id) {

        payrollService.deletePayroll(id);

        return "Payroll deleted successfully";
    }


    /*
     * PUT /payroll/{id}
     *
     * Existing Payroll update करण्यासाठी.
     */
    @PutMapping("/{id}")
    public Payroll updatePayroll(
            @PathVariable int id,
            @RequestBody Payroll payroll) {

        Payroll existingPayroll =
                payrollService.getPayrollById(id);

        if (existingPayroll == null) {
            throw new RuntimeException("Payroll not found");
        }

        existingPayroll.setEmployee(payroll.getEmployee());
        existingPayroll.setMonth(payroll.getMonth());
        existingPayroll.setBasicSalary(payroll.getBasicSalary());
        existingPayroll.setAllowances(payroll.getAllowances());
        existingPayroll.setDeductions(payroll.getDeductions());

        /*
         * savePayroll() मध्ये Employee validation
         * आणि Net Salary calculation होईल.
         */
        return payrollService.savePayroll(existingPayroll);
    }


    /*
     * GET /payroll/employee/{employeeId}
     *
     * Specific Employee चे Payroll records मिळवण्यासाठी.
     */
    @GetMapping("/employee/{employeeId}")
    public List<Payroll> getPayrollsByEmployee(
            @PathVariable int employeeId) {

        return payrollService.getPayrollsByEmployee(employeeId);
    }


    /*
     * GET /payroll/month/{month}
     *
     * Specific month चे Payroll records मिळवण्यासाठी.
     */
    @GetMapping("/month/{month}")
    public List<Payroll> getPayrollsByMonth(
            @PathVariable String month) {

        return payrollService.getPayrollsByMonth(month);
    }
}
