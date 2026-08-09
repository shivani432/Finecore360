package com.fincore.fincore360.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fincore.fincore360.entity.Designation;
import com.fincore.fincore360.repository.DesignationRepository;

@Service
public class DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    // Save Designation
    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    // Get All Designations
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    // Get Designation By ID
    public Designation getDesignationById(Integer id) {
        return designationRepository.findById(id).orElse(null);
    }

    // Update Designation
    public Designation updateDesignation(Integer id,
                                         Designation designation) {

        Designation existingDesignation =
                designationRepository.findById(id).orElse(null);

        if (existingDesignation != null) {

            existingDesignation.setDesignationName(
                    designation.getDesignationName());

            return designationRepository.save(existingDesignation);
        }

        return null;
    }

    // Delete Designation
    public void deleteDesignation(Integer id) {
        designationRepository.deleteById(id);
    }

    // Search Designation
    public List<Designation> searchDesignation(String designationName) {
        return designationRepository
                .findByDesignationNameContainingIgnoreCase(
                        designationName);
    }
}
