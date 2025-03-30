package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;

public record ReservationDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime reservationTime
) {
}
