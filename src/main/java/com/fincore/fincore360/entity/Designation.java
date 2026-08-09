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
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Database मध्ये Designation ची ID automatically generate करण्यासाठी
    private Integer id;

    @NotBlank(message = "Designation Name is required")
    @Size(min = 2, max = 50,
          message = "Designation Name must be between 2 and 50 characters")
    // Designation Name रिकामे नसावे आणि 2 ते 50 characters असावेत
    private String designationName;


    @OneToMany(mappedBy = "designation")
    // एका Designation ला अनेक Employees असू शकतात
    // mappedBy = "designation" म्हणजे Employee.java मधील
    // 'designation' field या relationship ला control करतो
    private List<Employee> employees;


    // रिकामा Constructor
    // JPA ला object तयार करण्यासाठी आवश्यक
    public Designation() {

    }


    // सर्व values set करण्यासाठी Constructor
    public Designation(Integer id, String designationName) {
        this.id = id;
        this.designationName = designationName;
    }


    // ID मिळवण्यासाठी
    public Integer getId() {
        return id;
    }


    // ID set करण्यासाठी
    public void setId(Integer id) {
        this.id = id;
    }


    // Designation Name मिळवण्यासाठी
    public String getDesignationName() {
        return designationName;
    }


    // Designation Name set करण्यासाठी
    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }


    // या Designation शी जोडलेले Employees मिळवण्यासाठी
    public List<Employee> getEmployees() {
        return employees;
    }


    // Employees ची List set करण्यासाठी
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}