package com.example.hospitalreservation.model;

import com.example.hospitalreservation.dto.ReservationDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private String reason;
    private int fee;

    protected Reservation() {}

    public Reservation(Doctor doctor, Patient patient, LocalDateTime reservationStartTime,
                       LocalDateTime reservationEndTime, String reason, int fee) {
        this.doctor = doctor;
        this.patient = patient;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.reason = reason;
        this.fee = fee;
    }
    public ReservationDTO toDto() {
        return new ReservationDTO(
                this.id,
                this.doctor.getId(),
                this.patient.getId(),
                this.reservationStartTime,
                this.reservationEndTime,
                this.reason,
                this.fee
        );
    }


    public Long getId() { return id; }
    public LocalDateTime getReservationStartTime() { return reservationStartTime; }
    public LocalDateTime getReservationEndTime() { return reservationEndTime; }
    public int getFee() { return fee; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
}
