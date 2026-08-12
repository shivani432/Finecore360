package com.fincore.fincore360.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.fincore360.entity.Leave;

/*
 * Repository:
 * Leave table सोबत database communication करण्यासाठी वापरतो.
 */
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    /*
     * Specific Employee चे सर्व Leave records शोधतो.
     */
    List<Leave> findByEmployee_Id(int employeeId);

    /*
     * Status नुसार Leave records शोधतो.
     * Example: Approved / Pending / Rejected
     */
    List<Leave> findByStatusIgnoreCase(String status);
}
