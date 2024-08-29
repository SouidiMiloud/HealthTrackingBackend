package com.example.helthTracking.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AppUserController {
    private AppUserService userService;

    @GetMapping("/patients")
    public ResponseEntity<List<AppUser>> getPatients(){
        return ResponseEntity.ok(userService.getPatients());
    }
}