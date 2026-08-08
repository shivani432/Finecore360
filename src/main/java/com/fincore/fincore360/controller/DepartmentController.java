package com.fincore.fincore360.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.fincore360.entity.Department;
import com.fincore.fincore360.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveDepartment(@Valid @RequestBody Department department) {
    return departmentService.saveDepartment(department);
}

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

   @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                   @Valid @RequestBody Department department) {
    return departmentService.updateDepartment(id, department);
}

    @DeleteMapping("/{id}")
public void deleteDepartment(@PathVariable Long id) {
    departmentService.deleteDepartment(id);
}
    @GetMapping("/search")
public List<Department> searchDepartment(@RequestParam String departmentName) {
    return departmentService.searchDepartment(departmentName);
}
}