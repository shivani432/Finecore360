package com.fincore.fincore360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.fincore360.entity.Attendance;

// Repository interface database मधील Attendance records वर
// CRUD operations करण्यासाठी वापरतो.
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
