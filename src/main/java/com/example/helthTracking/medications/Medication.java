package com.example.helthTracking.medications;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
public class Medication {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long patientId;
    private String name;
    private String dosage;
    private MedicationForm form;
    private String frequency;
    private String manufacturer;
    private Double price;
    private LocalDate expirationDate;
    private String sideEffects;
    private String imagePath;


    public Medication(Long patientId, String name, String dosage, MedicationForm form,
                      String frequency, String manufacturer, Double price,
                      LocalDate expirationDate, String sideEffects, String imagePath) {

        this.patientId = patientId;
        this.name = name;
        this.dosage = dosage;
        this.form = form;
        this.frequency = frequency;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expirationDate = expirationDate;
        this.sideEffects = sideEffects;
        this.imagePath = imagePath;
    }
}