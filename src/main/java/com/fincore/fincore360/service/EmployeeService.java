package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Department;
import com.fincore.fincore360.entity.Designation;
import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.repository.DepartmentRepository;
import com.fincore.fincore360.repository.DesignationRepository;
import com.fincore.fincore360.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DesignationRepository designationRepository;
    // Employee सोबत आलेली Designation database मधून शोधण्यासाठी

    @Autowired
    private DepartmentRepository departmentRepository;
// Employee सोबत आलेला Department database मधून शोधण्यासाठी

   // =====================================
// Marathi : Employee Save करणे
// English : Save Employee
// =====================================
public void saveEmployee(Employee employee) {

    // =====================================
    // Department check
    // Employee ने Department select केला आहे का ते check करतो
    // =====================================
    if (employee.getDepartment() != null
            && employee.getDepartment().getId() != 0) {

        // Selected Department ची actual database record मिळवतो
        Department department =
                departmentRepository.findById(
                        employee.getDepartment().getId()
                ).orElse(null);

        // Database मधील Department Employee ला set करतो
        employee.setDepartment(department);
    }


    // =====================================
    // Designation check
    // Employee ने Designation select केली आहे का ते check करतो
    // =====================================
    if (employee.getDesignation() != null
            && employee.getDesignation().getId() != null) {

        // Selected Designation ची actual database record मिळवतो
        Designation designation =
                designationRepository.findById(
                        employee.getDesignation().getId()
                ).orElse(null);

        // Database मधील Designation Employee ला set करतो
        employee.setDesignation(designation);
    }


    // =====================================
    // Employee database मध्ये save करतो
    // =====================================
    employeeRepository.save(employee);
}

    // =====================================
    // मराठी : सर्व Employees मिळवणे
    // English : Get All Employees
    // =====================================
   public List<Employee> getAllEmployees() {
    return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
}

    // =====================================
    // मराठी : ID वापरून Employee मिळवणे
    // English : Get Employee By ID
    // =====================================
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
    // =====================================
    // मराठी : ID वापरून Employee Delete करणे
    // English : Delete Employee By ID
    // =====================================
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
     // Search employee by name
    public List<Employee> searchEmployee(String name) {
    return employeeRepository.findByNameContainingIgnoreCase(name);
    }
 // =====================================
// Marathi : Department नुसार Employee Filter करणे
// English : Filter Employees By Department
// =====================================
public List<Employee> filterByDepartment(String department) {

    // Department Name नुसार Employees शोधतो
    return employeeRepository
            .findByDepartment_DepartmentNameIgnoreCase(department);
}

public Double getTotalSalary() {
    return employeeRepository.getTotalSalary();
}
public long getTotalEmployees() {
    return employeeRepository.count();
}
public long getTotalDepartments() {
    return employeeRepository.getTotalDepartments();
} public Double getAverageSalary() {
    return employeeRepository.getAverageSalary();
}
// =====================================
// Dashboard Salary Formatting
// =====================================

// Total Salary ला dashboard साठी readable format मध्ये convert करतो.
// Example: 640000 -> ₹6.40 L
public String getFormattedTotalSalary() {

    double salary = getTotalSalary();

    return formatSalary(salary);
}


// Average Salary ला dashboard साठी readable format मध्ये convert करतो.
// Example: 160000 -> ₹1.60 L
public String getFormattedAverageSalary() {

    double salary = getAverageSalary();

    return formatSalary(salary);
}


// Salary number readable format मध्ये convert करणारी common method.
private String formatSalary(double salary) {

    if (salary >= 10000000) {

        // 1 Crore किंवा त्यापेक्षा जास्त
        return String.format("₹%.2f Cr", salary / 10000000);

    } else if (salary >= 100000) {

        // 1 Lakh किंवा त्यापेक्षा जास्त
        return String.format("₹%.2f L", salary / 100000);

    } else if (salary >= 1000) {

        // 1 Thousand किंवा त्यापेक्षा जास्त
        return String.format("₹%.2f K", salary / 1000);

    } else {

        return String.format("₹%.2f", salary);
    }
}
public List<Employee> sortByNameAsc() {
    return employeeRepository.findAllByOrderByNameAsc();
}

public List<Employee> sortByNameDesc() {
    return employeeRepository.findAllByOrderByNameDesc();
}
public List<String> getAllDepartments() {
    return employeeRepository.getAllDepartments();
}

}


