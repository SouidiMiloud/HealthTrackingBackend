package com.example.helthTracking.medications;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients/{patientId}/medications")
@AllArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;


    @GetMapping
    public ResponseEntity<List<Medication>> getMedications(@PathVariable Long patientId){
        return ResponseEntity.ok(medicationService.getMedicationsForPatients(patientId));
    }

    @PostMapping
    public ResponseEntity<Medication> addMedication(@PathVariable Long patientId,
                                                    @RequestBody MedicationReq medication){
        return ResponseEntity.ok(medicationService.addMedication(patientId, medication));
    }

    @PutMapping("/{medicationId}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long patientId,
                                                       @PathVariable Long medicationId,
                                                       @RequestBody Medication medication){
        medication.setPatientId(patientId);
        return ResponseEntity.ok(medicationService.updateMedication(medicationId, medication));
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long patientId,
                                                 @PathVariable Long medicationId){

        medicationService.deleteMedication(medicationId);
        return ResponseEntity.noContent().build();
    }

}