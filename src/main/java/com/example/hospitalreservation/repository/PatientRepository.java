package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
이 다음에 한거커밋 메시지 뭘로할까? 지금까지 커밋 안했어 지금 엔티티 다대다 연결 같은건