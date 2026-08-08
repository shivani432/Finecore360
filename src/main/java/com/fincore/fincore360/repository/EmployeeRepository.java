package com.fincore.fincore360.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fincore.fincore360.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Search employee by name (Ignore Uppercase / Lowercase)
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartment_DepartmentNameIgnoreCase(String departmentName);
    @Query("SELECT SUM(e.salary) FROM Employee e")
    Double getTotalSalary();

    @Query("SELECT COUNT(DISTINCT e.department.id) FROM Employee e")
    long getTotalDepartments();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    List<Employee> findAllByOrderByNameAsc();

    List<Employee> findAllByOrderByNameDesc();

    @Query("SELECT DISTINCT e.department.departmentName FROM Employee e ORDER BY e.department.departmentName")
    List<String> getAllDepartments();

}