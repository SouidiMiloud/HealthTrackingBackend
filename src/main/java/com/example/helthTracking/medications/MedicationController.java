package com.example.helthTracking.medications;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/patients/{patientId}/medications")
@AllArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;


    private static final String UPLOAD_DIR = "C:/Users/Electro Ragragui/Desktop/health_tracking/public/uploads";
    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            File targetFile = new File(uploadPath + File.separator + file.getOriginalFilename());

            file.transferTo(targetFile);

            return "File uploaded successfully: " + targetFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed.";
        }
    }
    @GetMapping
    public ResponseEntity<List<Medication>> getMedications(@PathVariable Long patientId){
        return ResponseEntity.ok(medicationService.getMedicationsForPatients(patientId));
    }

    @PostMapping
    public ResponseEntity<Medication> addMedication(@PathVariable Long patientId,
                                                    @RequestBody MedicationReq medication){
        return ResponseEntity.ok(medicationService.addMedication(patientId, medication));
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long patientId,
                                                 @PathVariable Long medicationId){

        medicationService.deleteMedication(medicationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable Long patientId,
                                                    @PathVariable Long id){

        return ResponseEntity.ok(medicationService.getMedication(id));
    }

}