package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.entity.Leave;
import com.fincore.fincore360.repository.EmployeeRepository;
import com.fincore.fincore360.repository.LeaveRepository;

/*
 * @Service:
 * Leave module ची business logic handle करण्यासाठी.
 */
@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    /*
     * Constructor Injection:
     * Spring automatically repositories provide करतो.
     */
    public LeaveService(LeaveRepository leaveRepository,
                        EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }


    /*
     * Leave save करतो.
     *
     * Employee ID वरून actual Employee database मधून शोधतो.
     */
    public Leave saveLeave(Leave leave) {

        if (leave.getEmployee() != null) {

            int employeeId = leave.getEmployee().getId();

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new RuntimeException("Employee not found"));

            leave.setEmployee(employee);
        }

        return leaveRepository.save(leave);
    }


    /*
     * सर्व Leave records मिळवतो.
     */
    public List<Leave> getAllLeaves() {

        return leaveRepository.findAll();
    }


    /*
     * ID वापरून एक Leave record मिळवतो.
     */
    public Leave getLeaveById(int id) {

        return leaveRepository.findById(id)
                .orElse(null);
    }


    /*
     * Leave record delete करतो.
     */
    public void deleteLeave(int id) {

        leaveRepository.deleteById(id);
    }


    /*
     * Employee ID नुसार Leave records मिळवतो.
     */
    public List<Leave> getLeavesByEmployee(int employeeId) {

        return leaveRepository.findByEmployee_Id(employeeId);
    }


    /*
     * Status नुसार Leave records मिळवतो.
     */
    public List<Leave> getLeavesByStatus(String status) {

        return leaveRepository.findByStatusIgnoreCase(status);
    }
}
