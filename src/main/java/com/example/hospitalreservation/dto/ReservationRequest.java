package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public record ReservationRequest(
        Long doctorId,
        Long patientId,
        LocalDateTime reservationTime,
        String reason
) {}
