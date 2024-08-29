package com.example.helthTracking.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepo userRepo;


    public List<AppUser> getPatients(){
        return userRepo.findPatients();
    }
}