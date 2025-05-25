package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;
import com.example.hospitalreservation.dto.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

