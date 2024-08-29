package com.example.helthTracking.user;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    @Query("SELECT p FROM AppUser p WHERE p.appUserRole='PATIENT'")
    List<AppUser> findPatients();
}