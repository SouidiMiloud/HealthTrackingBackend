package com.example.helthTracking.medications;

import com.example.helthTracking.user.AppUser;
import com.example.helthTracking.user.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class MedicationService {
    private final MedicationRepo medicationRepo;

    public List<Medication> getMedicationsForPatients(AppUser user, Long patientId){
        if(user.getAppUserRole() == AppUserRole.DOCTOR)
            return medicationRepo.findByPatientId(patientId);
        return medicationRepo.findByPatientId(user.getId());
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
                request.getSideEffects(),
                request.getImagePath()
                )
        );
    }

    public void deleteMedication(Long medicationId){
        Medication medication = medicationRepo.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        medicationRepo.delete(medication);
    }

    public Medication getMedication(Long id) {

        return medicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found"));
    }
}