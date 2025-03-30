package com.example.hospitalreservation.model;

import java.time.LocalDateTime;
import com.example.hospitalreservation.dto.ReservationDTO;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;

    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    public ReservationDTO toDto() {
        return new ReservationDTO(id, doctorId, patientId, reservationTime);
    }
}

