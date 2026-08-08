package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

public void saveEmployee(Employee employee) {

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
  public List<Employee> filterByDepartment(String department) {

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


