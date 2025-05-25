package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
