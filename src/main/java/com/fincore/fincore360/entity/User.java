package com.fincore.fincore360.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
 * @Entity:
 * User class ला database table शी map करतो.
 */
@Entity
public class User {

    /*
     * Primary Key
     * प्रत्येक User ला unique ID मिळते.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
     * User चे नाव.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /*
     * Login साठी Email वापरणार आहोत.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    /*
     * Login Password.
     */
    @NotBlank(message = "Password is required")
    private String password;


    /*
     * Default Constructor:
     * JPA ला object तयार करण्यासाठी आवश्यक.
     */
    public User() {
    }


    /*
     * Parameterized Constructor:
     * सर्व values एकाच वेळी set करण्यासाठी.
     */
    public User(int id, String name, String email, String password) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    // ID Getter
    public int getId() {
        return id;
    }

    // ID Setter
    public void setId(int id) {
        this.id = id;
    }


    // Name Getter
    public String getName() {
        return name;
    }

    // Name Setter
    public void setName(String name) {
        this.name = name;
    }


    // Email Getter
    public String getEmail() {
        return email;
    }

    // Email Setter
    public void setEmail(String email) {
        this.email = email;
    }


    // Password Getter
    public String getPassword() {
        return password;
    }

    // Password Setter
    public void setPassword(String password) {
        this.password = password;
    }
}
