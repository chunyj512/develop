package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationDTO;
import com.example.hospitalreservation.fee.FeePolicy;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final Map<String, FeePolicy> feePolicyMap;
    private Long idCounter = 1L;

    public ReservationService(ReservationRepository reservationRepository, List<FeePolicy> feePolicies) {
        this.reservationRepository = reservationRepository;
        this.feePolicyMap = feePolicies.stream()
                .collect(Collectors.toMap(FeePolicy::getReason, Function.identity()));
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList());
    }

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime, String reason) {
        int hour = reservationTime.getHour();
        int minute = reservationTime.getMinute();

        if (hour < 9 || hour > 17 || minute != 0) {
            throw new IllegalArgumentException("예약은 오전 9시부터 오후 5시까지, 매 정각에만 가능합니다.");
        }

        boolean isDuplicate = reservationRepository.findAll().stream()
                .anyMatch(r -> r.toDto().reservationTime().equals(reservationTime));
        if (isDuplicate) {
            throw new IllegalArgumentException("해당 시간에는 이미 예약이 존재합니다.");
        }

        FeePolicy policy = feePolicyMap.get(reason);
        if (policy == null) {
            throw new IllegalArgumentException("지원하지 않는 진료 목적입니다: " + reason);
        }

        int fee = policy.calculateFee();
        Reservation reservation = new Reservation(idCounter++, doctorId, patientId, reservationTime, reason, fee);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
