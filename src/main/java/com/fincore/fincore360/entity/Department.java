package com.fincore.fincore360.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @NotBlank(message = "Department Name is required")
    @Size(min = 2, max = 50, message = "Department Name must be between 2 and 50 characters")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department() {

    }

    // public Department(int id, String departmentName) {
    //     this.id = id;
    //     this.departmentName = departmentName;
    // }

    // Department ची ID मिळवण्यासाठी
    public Long getId() {
        return id;
    }

    // Department ची ID set करण्यासाठी
    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public List<Employee> getEmployees() {
    return employees;
}

    public void setEmployees(List<Employee> employees) {
    this.employees = employees;
}

}

