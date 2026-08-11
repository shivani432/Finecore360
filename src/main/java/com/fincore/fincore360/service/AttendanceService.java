package com.fincore.fincore360.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Attendance;
import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.repository.AttendanceRepository;
import com.fincore.fincore360.repository.EmployeeRepository;

// @Service tells Spring that this class contains business logic.
// Spring automatically creates and manages an object of this class.
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                         EmployeeRepository employeeRepository) {

    this.attendanceRepository = attendanceRepository;
    this.employeeRepository = employeeRepository;
}


    public Attendance saveAttendance(Attendance attendance) {

    // Employee ID वरून actual Employee database मधून घेतो.
    if (attendance.getEmployee() != null) {

        int employeeId = attendance.getEmployee().getId();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        attendance.setEmployee(employee);
    }

    return attendanceRepository.save(attendance);
}

    // Fetches all Attendance records from the database.
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Finds one Attendance record using its ID.
    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    // Deletes an Attendance record using its ID.
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    // Updates an existing Attendance record.
public Attendance updateAttendance(Long id, Attendance updatedAttendance) {

    Attendance existingAttendance = attendanceRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Attendance not found"));

    existingAttendance.setDate(updatedAttendance.getDate());
    existingAttendance.setStatus(updatedAttendance.getStatus());
    existingAttendance.setCheckInTime(updatedAttendance.getCheckInTime());
    existingAttendance.setCheckOutTime(updatedAttendance.getCheckOutTime());

    // Employee ID वरून actual Employee database मधून घेतो.
    if (updatedAttendance.getEmployee() != null) {

        int employeeId = updatedAttendance.getEmployee().getId();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingAttendance.setEmployee(employee);
    }

    return attendanceRepository.save(existingAttendance);
}
}
