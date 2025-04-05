package com.example.hospitalreservation.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ReservationRequest(
        Long doctorId,
        Long patientId,
        @JsonProperty("reservationStartTime") LocalDateTime reservationTime,
        String reason
) {}
