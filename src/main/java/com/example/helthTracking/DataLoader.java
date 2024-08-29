package com.example.helthTracking;

import com.example.helthTracking.user.AppUser;
import com.example.helthTracking.user.AppUserRepo;
import com.example.helthTracking.user.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private AppUserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String email = "doctor@gmail.com";
        if(userRepo.findByUsername(email).isEmpty()){
            String password = passwordEncoder.encode("doctor");
            AppUser doctor = new AppUser("doctor", "doctor", email, password, AppUserRole.DOCTOR);
            userRepo.save(doctor);
        }
    }
}