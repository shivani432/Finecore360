package com.fincore.fincore360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fincore.fincore360.service.AttendanceService;
import com.fincore.fincore360.service.EmployeeService;
import com.fincore.fincore360.service.LeaveService;
import com.fincore.fincore360.service.PayrollService;

/*
 * @Controller:
 * Reports / Analytics चा Thymeleaf dashboard page
 * browser मध्ये display करण्यासाठी.
 */
@Controller
public class ReportController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;
    private final LeaveService leaveService;
    private final PayrollService payrollService;

    /*
     * Constructor Injection:
     * Reports साठी लागणाऱ्या सर्व Services Spring provide करतो.
     */
    public ReportController(
            EmployeeService employeeService,
            AttendanceService attendanceService,
            LeaveService leaveService,
            PayrollService payrollService) {

        this.employeeService = employeeService;
        this.attendanceService = attendanceService;
        this.leaveService = leaveService;
        this.payrollService = payrollService;
    }

    /*
     * /reports URL open झाल्यावर हा method चालतो.
     */
    @GetMapping("/reports")
    public String reportsPage(Model model) {

        // Total Employees dashboard ला पाठवतो.
        model.addAttribute("totalEmployees",
                employeeService.getTotalEmployees());

        // Total Salary dashboard ला पाठवतो.
 // Dashboard वर formatted Total Salary दाखवतो.
model.addAttribute("totalSalary",
        employeeService.getFormattedTotalSalary());

        // Dashboard वर formatted Average Salary दाखवतो.
model.addAttribute("averageSalary",
        employeeService.getFormattedAverageSalary());

        // Total Attendance records dashboard ला पाठवतो.
        model.addAttribute("totalAttendance",
                attendanceService.getAllAttendance().size());

        // Total Leave records dashboard ला पाठवतो.
        model.addAttribute("totalLeaves",
                leaveService.getAllLeaves().size());

        // Total Payroll records dashboard ला पाठवतो.
        model.addAttribute("totalPayrolls",
                payrollService.getAllPayrolls().size());

        // reports.html Thymeleaf page open करतो.
        return "reports";
    }
}