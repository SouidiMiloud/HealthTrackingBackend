package com.example.helthTracking.medications;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class MedicationService {
    private final MedicationRepo medicationRepo;

    public List<Medication> getMedicationsForPatients(Long patientId){
        return medicationRepo.findByPatientId(patientId);
    }

    public Medication addMedication(Long patientId, MedicationReq request){

        return medicationRepo.save(new Medication(
                patientId,
                request.getName(),
                request.getDosage(),
                MedicationForm.valueOf(request.getForm()),
                request.getFrequency(),
                request.getManufacturer(),
                request.getPrice(),
                LocalDate.parse(request.getExpirationDate()),
                request.getSideEffects())
        );
    }

    public Medication updateMedication(Long medicationId, Medication medicationDetails){
        Medication medication = medicationRepo.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        medication.setPatientId(medicationDetails.getPatientId());
        medication.setName(medicationDetails.getName());
        medication.setDosage(medicationDetails.getDosage());
        medication.setForm(medicationDetails.getForm());
        medication.setFrequency(medicationDetails.getFrequency());
        medication.setManufacturer(medicationDetails.getManufacturer());
        medication.setPrice(medicationDetails.getPrice());
        medication.setExpirationDate(medicationDetails.getExpirationDate());
        medication.setSideEffects(medicationDetails.getSideEffects());

        return medicationRepo.save(medication);
    }

    public void deleteMedication(Long medicationId){
        Medication medication = medicationRepo.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        medicationRepo.delete(medication);
    }
}