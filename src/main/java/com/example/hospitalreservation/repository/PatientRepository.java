package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}