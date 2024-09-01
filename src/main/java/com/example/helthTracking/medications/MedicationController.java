package com.example.helthTracking.medications;

import com.example.helthTracking.user.AppUser;
import com.example.helthTracking.user.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<Medication>> getMedications(@AuthenticationPrincipal AppUser user, @PathVariable Long patientId){
        return ResponseEntity.ok(medicationService.getMedicationsForPatients(user, patientId));
    }

    @PostMapping
    public ResponseEntity<Medication> addMedication(@AuthenticationPrincipal AppUser user, @PathVariable Long patientId,
                                                    @RequestBody MedicationReq medication){
        if(user.getAppUserRole() == AppUserRole.DOCTOR)
            return ResponseEntity.ok(medicationService.addMedication(patientId, medication));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@AuthenticationPrincipal AppUser user, @PathVariable Long patientId,
                                                 @PathVariable Long medicationId){

        if(user.getAppUserRole() == AppUserRole.DOCTOR) {
            medicationService.deleteMedication(medicationId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable Long patientId,
                                                    @PathVariable Long id){

        return ResponseEntity.ok(medicationService.getMedication(id));
    }

}