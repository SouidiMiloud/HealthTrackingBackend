package com.example.helthTracking.security.authentication;

import com.example.helthTracking.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TokenResponse {
    private AppUser user;
    private String token;
}