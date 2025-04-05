package com.example.hospitalreservation.model;

import java.time.LocalDateTime;
import com.example.hospitalreservation.dto.ReservationDTO;

public class Reservation {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime reservationTime;
    private String reason;
    private int fee;

    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationTime, String reason, int fee) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
        this.reason = reason;
        this.fee = fee;
    }

    public ReservationDTO toDto() {
        return new ReservationDTO(id, doctorId, patientId, reservationTime, reason, fee);
    }
}
