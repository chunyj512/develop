package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;
import com.example.hospitalreservation.dto.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByDoctorIdAndReservationStartTime(Long doctorId, LocalDateTime time);

}

