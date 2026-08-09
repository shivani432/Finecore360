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

import com.fincore.fincore360.entity.Designation;
import com.fincore.fincore360.service.DesignationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/designations")
@CrossOrigin(origins = "*")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    // Save Designation
    @PostMapping
    public Designation saveDesignation(
            @Valid @RequestBody Designation designation) {

        return designationService.saveDesignation(designation);
    }

    // Get All Designations
    @GetMapping
    public List<Designation> getAllDesignations() {

        return designationService.getAllDesignations();
    }

    // Get Designation By ID
    @GetMapping("/{id}")
    public Designation getDesignationById(
            @PathVariable Integer id) {

        return designationService.getDesignationById(id);
    }

    // Update Designation
    @PutMapping("/{id}")
    public Designation updateDesignation(
            @PathVariable Integer id,
            @Valid @RequestBody Designation designation) {

        return designationService.updateDesignation(id, designation);
    }

    // Delete Designation
    @DeleteMapping("/{id}")
    public void deleteDesignation(
            @PathVariable Integer id) {

        designationService.deleteDesignation(id);
    }

    // Search Designation
    @GetMapping("/search")
    public List<Designation> searchDesignation(
            @RequestParam String designationName) {

        return designationService.searchDesignation(
                designationName);
    }
}
