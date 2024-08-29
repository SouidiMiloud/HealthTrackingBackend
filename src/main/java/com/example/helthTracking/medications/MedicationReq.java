package com.example.helthTracking.medications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationReq {

    private String name;
    private String dosage;
    private String form;
    private String frequency;
    private String manufacturer;
    private Double price;
    private String expirationDate;
    private String sideEffects;
    private String imagePath;
}