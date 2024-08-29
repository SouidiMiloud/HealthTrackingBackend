package com.example.helthTracking.medications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long> {

    List<Medication> findByPatientId(Long patientId);
}