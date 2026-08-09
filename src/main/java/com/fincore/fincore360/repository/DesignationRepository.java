package com.fincore.fincore360.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.fincore360.entity.Designation;

public interface DesignationRepository
        extends JpaRepository<Designation, Integer> {

    List<Designation> findByDesignationNameContainingIgnoreCase(
            String designationName);

}