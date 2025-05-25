package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.fee.FeePolicy;
import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Patient;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.DoctorRepository;
import com.example.hospitalreservation.repository.PatientRepository;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final Map<String, FeePolicy> feePolicyMap;

    public ReservationService(
            ReservationRepository reservationRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            List<FeePolicy> feePolicies
    ) {
        this.reservationRepository = reservationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.feePolicyMap = feePolicies.stream()
                .collect(Collectors.toMap(FeePolicy::getReason, Function.identity()));
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList());
    }

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String reason) {
        int hour = reservationStartTime.getHour();
        int minute = reservationStartTime.getMinute();

        if (hour < 9 || hour > 17 || minute != 0) {
            throw new IllegalArgumentException("예약은 오전 9시부터 오후 5시까지, 매 정각에만 가능합니다.");
        }

        if (!reservationEndTime.equals(reservationStartTime.plusHours(1))) {
            throw new IllegalArgumentException("예약 종료 시간은 시작 시간으로부터 정확히 1시간 후여야 합니다.");
        }

        boolean isDuplicate = reservationRepository.findAll().stream()
                .anyMatch(r -> r.getReservationStartTime().equals(reservationStartTime));
        if (isDuplicate) {
            throw new IllegalArgumentException("해당 시간에는 이미 예약이 존재합니다.");
        }

        FeePolicy policy = feePolicyMap.get(reason);
        if (policy == null) {
            throw new IllegalArgumentException("지원하지 않는 진료 목적입니다: " + reason);
        }

        int fee = policy.calculateFee();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 의사를 찾을 수 없습니다."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("해당 환자를 찾을 수 없습니다."));

        Reservation reservation = new Reservation(doctor, patient, reservationStartTime, reservationEndTime, reason, fee);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
