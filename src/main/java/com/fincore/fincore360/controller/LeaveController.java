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

import com.fincore.fincore360.entity.Leave;
import com.fincore.fincore360.service.LeaveService;

/*
 * @RestController:
 * Leave module चे REST API requests handle करण्यासाठी.
 */
@RestController

/*
 * सर्व Leave APIs /leave पासून सुरू होतील.
 */
@RequestMapping("/leave")

/*
 * Postman / Frontend requests allow करण्यासाठी.
 */
@CrossOrigin(origins = "*")
public class LeaveController {

    /*
     * Leave ची business logic Service कडे देतो.
     */
    private final LeaveService leaveService;


    /*
     * Constructor Injection:
     * Spring automatically LeaveService object देतो.
     */
    public LeaveController(LeaveService leaveService) {

        this.leaveService = leaveService;
    }


    /*
     * POST /leave
     *
     * नवीन Leave record save करण्यासाठी.
     */
    @PostMapping
    public Leave saveLeave(@RequestBody Leave leave) {

        return leaveService.saveLeave(leave);
    }


    /*
     * GET /leave
     *
     * सर्व Leave records मिळवण्यासाठी.
     */
    @GetMapping
    public List<Leave> getAllLeaves() {

        return leaveService.getAllLeaves();
    }


    /*
     * GET /leave/{id}
     *
     * ID वापरून एक Leave record मिळवण्यासाठी.
     */
    @GetMapping("/{id}")
    public Leave getLeaveById(@PathVariable int id) {

        return leaveService.getLeaveById(id);
    }


    /*
     * DELETE /leave/{id}
     *
     * Leave record delete करण्यासाठी.
     */
    @DeleteMapping("/{id}")
    public String deleteLeave(@PathVariable int id) {

        leaveService.deleteLeave(id);

        return "Leave deleted successfully";
    }


    /*
     * PUT /leave/{id}
     *
     * Existing Leave record update करण्यासाठी.
     */
    @PutMapping("/{id}")
    public Leave updateLeave(
            @PathVariable int id,
            @RequestBody Leave leave) {

        Leave existingLeave =
                leaveService.getLeaveById(id);

        if (existingLeave == null) {
            throw new RuntimeException("Leave not found");
        }

        existingLeave.setEmployee(leave.getEmployee());
        existingLeave.setLeaveDate(leave.getLeaveDate());
        existingLeave.setLeaveType(leave.getLeaveType());
        existingLeave.setStatus(leave.getStatus());
        existingLeave.setReason(leave.getReason());

        /*
         * Service Employee verify करून
         * updated Leave database मध्ये save करेल.
         */
        return leaveService.saveLeave(existingLeave);
    }


    /*
     * GET /leave/employee/{employeeId}
     *
     * Specific Employee चे Leave records मिळवण्यासाठी.
     */
    @GetMapping("/employee/{employeeId}")
    public List<Leave> getLeavesByEmployee(
            @PathVariable int employeeId) {

        return leaveService.getLeavesByEmployee(employeeId);
    }


    /*
     * GET /leave/status/{status}
     *
     * Status नुसार Leave records मिळवण्यासाठी.
     */
    @GetMapping("/status/{status}")
    public List<Leave> getLeavesByStatus(
            @PathVariable String status) {

        return leaveService.getLeavesByStatus(status);
    }
}
