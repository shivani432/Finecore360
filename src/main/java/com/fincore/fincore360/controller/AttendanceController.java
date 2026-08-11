package com.fincore.fincore360.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.fincore360.entity.Attendance;
import com.fincore.fincore360.service.AttendanceService;



// @RestController tells Spring that this class handles REST API requests.
// The methods below will return data directly as JSON responses.
@RestController

// All APIs in this controller will start with /attendance.
@RequestMapping("/attendance")

// Allows frontend/Postman requests from different origins.
@CrossOrigin(origins = "*")
public class AttendanceController {

    // Service layer is used to perform Attendance business operations.
    private final AttendanceService attendanceService;

    // Constructor Injection:
    // Spring automatically provides the AttendanceService object.
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // POST /attendance
    // Used to create/save a new attendance record.
    @PostMapping
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    // GET /attendance
    // Used to fetch all attendance records.
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // GET /attendance/{id}
    // Used to fetch one attendance record by its ID.
    @GetMapping("/{id}")
    public Optional<Attendance> getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    // DELETE /attendance/{id}
    // Used to delete an attendance record by its ID.
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return "Attendance deleted successfully";
    }

    // PUT /attendance/{id}
// Existing attendance record update करण्यासाठी.
@PutMapping("/{id}")
public Attendance updateAttendance(
        @PathVariable Long id,
        @RequestBody Attendance attendance) {

    Attendance existing = attendanceService
            .getAttendanceById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found"));

    existing.setDate(attendance.getDate());
    existing.setStatus(attendance.getStatus());
    existing.setCheckInTime(attendance.getCheckInTime());
    existing.setCheckOutTime(attendance.getCheckOutTime());

    // Employee दिला असेल तर तोही update करतो.
    if (attendance.getEmployee() != null) {
        existing.setEmployee(attendance.getEmployee());
    }

    return attendanceService.saveAttendance(existing);
}


}
