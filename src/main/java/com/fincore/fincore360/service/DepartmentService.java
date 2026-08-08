package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Department;
import com.fincore.fincore360.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Save Department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get All Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get Department By Id
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department updateDepartment(Long id, Department department) {

    Department existingDepartment = departmentRepository.findById(id).orElse(null);

    if (existingDepartment != null) {
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(existingDepartment);
    }

    return null;
}
   public void deleteDepartment(Long id) {

    Department department =
            departmentRepository.findById(id).orElse(null);

    if (department == null) {
        return;
    }

    if (department.getEmployees() != null
            && !department.getEmployees().isEmpty()) {

        throw new RuntimeException(
                "Cannot delete department because employees are assigned to it."
        );
    }

    departmentRepository.deleteById(id);
}

    public List<Department> searchDepartment(String departmentName) {
    return departmentRepository.findByDepartmentNameContainingIgnoreCase(departmentName);
}
}