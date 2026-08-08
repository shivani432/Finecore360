package com.fincore.fincore360.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Entity
public class Employee {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Positive(message = "Salary must be greater than 0")
    private double salary;

        // मराठी: रिकामा Constructor (Spring Boot ला Object तयार करण्यासाठी लागतो)
        // English: Default Constructor required by Spring Boot

    public Employee() {

    }

    // मराठी: सर्व Values एकाच वेळी Set करण्यासाठी Constructor
    // English: Parameterized Constructor

   public Employee(Department department, String email, int id, String name, double salary) {
    this.department = department;
    this.email = email;
    this.id = id;
    this.name = name;
    this.salary = salary;
}

    // मराठी: Id मिळवण्यासाठी Getter
// English: Getter for id
public int getId() {
    return id;
}

// मराठी: Id Set करण्यासाठी Setter
// English: Setter for id
public void setId(int id) {
    this.id = id;
}

// मराठी: Name मिळवण्यासाठी Getter
// English: Getter for name
public String getName() {
    return name;
}

// मराठी: Name Set करण्यासाठी Setter
// English: Setter for name
public void setName(String name) {
    this.name = name;
}

// मराठी: Email मिळवण्यासाठी Getter
// English: Getter for email
public String getEmail() {
    return email;
}

// मराठी: Email Set करण्यासाठी Setter
// English: Setter for email
public void setEmail(String email) {
    this.email = email;
}

public Department getDepartment() {
    return department;
}

public void setDepartment(Department department) {
    this.department = department;
}

// मराठी: Salary मिळवण्यासाठी Getter
// English: Getter for salary
public double getSalary() {
    return salary;
}

// मराठी: Salary Set करण्यासाठी Setter
// English: Setter for salary
public void setSalary(double salary) {
    this.salary = salary;
}
}